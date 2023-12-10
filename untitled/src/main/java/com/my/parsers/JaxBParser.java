package com.my.parsers;



import JaxbClasses.UsersEvents;
import jakarta.xml.bind.*;
import jakarta.xml.bind.util.ValidationEventCollector;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;


public class JaxBParser {
    public static UsersEvents xmlToObj(final String xmlFileName, final String xsdFileName, Class<?> objectFactory) throws JAXBException, SAXException {
    JAXBContext jc = JAXBContext.newInstance(objectFactory);
    Unmarshaller unmarshaller = jc.createUnmarshaller();
    SchemaFactory sf =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    if(!XMLValidator.validateXMLAgainstXSD(xmlFileName,xsdFileName)){
        throw new RuntimeException("not validated");
    }
    if (xsdFileName != null) {
        Schema schema = null;
        if ("".equals(xsdFileName)) {
            schema = sf.newSchema();
        } else {

            schema = sf.newSchema(new File(xsdFileName));
        }
        unmarshaller.setSchema(schema);
    }
    // do unmarshal
    UsersEvents event = (UsersEvents) unmarshaller.unmarshal(new File(xmlFileName));
    return event; // <-- filled container
}

    public static void objToXml(UsersEvents obj,String pathxsd, String filename,String path) {

            try {

                JAXBContext jaxbContext = JAXBContext.newInstance(UsersEvents.class);

                Schema schema = getSchema(pathxsd);

                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setSchema(schema);

                File outputFile = new File(path+filename+".xml");

                ValidationEventCollector eventCollector = new ValidationEventCollector();
                marshaller.setEventHandler(eventCollector);

                marshaller.marshal(obj, outputFile);

                // Перевірка наявності помилок валідації
                if (eventCollector.hasEvents()) {
                    System.out.println("Помилки валідації:");
                    for (ValidationEvent event : eventCollector.getEvents()) {
                        System.out.println(event.getMessage());
                    }
                } else {
                    System.out.println("XML-документ збережено в файл: " + outputFile.getAbsolutePath());
                }

            } catch (JAXBException e) {
                System.out.println(e.getMessage());
            } catch (SAXException e) {
                System.out.println(e.getMessage());
            }

    }
    private static Schema getSchema(String schemaPath) throws SAXException {
        return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new StreamSource(new File(schemaPath)));
    }
    public static void main(String[] args)
    {
        String XML_FILE_PATH="./src/main/resources/XSDFiles/UsersEvents.xml";
        String XSD_FILE_PATH="./src/main/resources/XSDFiles/classes.xsd";
        UsersEvents events = null;
        try {
            events = xmlToObj(XML_FILE_PATH, XSD_FILE_PATH, UsersEvents.class);
            System.out.println("Here is the event: \n" + events.toString());
            events.getUserEvent().get(0).getEvent().setPinned(0);

            objToXml(events,XSD_FILE_PATH,"usersEvents","./src/main/resources/XSDFiles/generatedByJaxb/");
            events = xmlToObj("./src/main/resources/XSDFiles/generatedByJaxb/usersEvents.xml", XSD_FILE_PATH, UsersEvents.class);
            System.out.println("Here is the event: \n" + events.toString());
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        }
    }

}
