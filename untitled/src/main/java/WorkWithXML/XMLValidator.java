package WorkWithXML;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
public class XMLValidator {

    public static void main(String[] args) {
        String xmlFilePath = "./XSDFiles/event.xml";
        String xsdFilePath = "./XSDFiles/classes.xsd";

        if (validateXMLAgainstXSD(xmlFilePath, xsdFilePath)) {
            System.out.println("Validation successful!");
        } else {
            System.out.println("Validation failed!");
        }
    }

    public static boolean validateXMLAgainstXSD(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(xmlFilePath));
            validator.validate(source);
            return true; // Якщо валідація успішна
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false; // Якщо валідація не вдалася
        }
    }
}
