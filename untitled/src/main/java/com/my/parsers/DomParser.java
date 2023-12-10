package com.my.parsers;

import JaxbClasses.*;
import jakarta.xml.bind.annotation.XmlElement;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    // Метод для демаршалізації з валідацією
    public static UsersEvents unmarshalWithValidation(File xmlFile, File xsdFile) {
        try {
            // Створюємо фабрику для побудови DOM-аналізатора
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setValidating(true);

            // Створюємо схему для валідації
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(xsdFile);

            // Встановлюємо схему в фабрику
            dbf.setSchema(schema);

            // Створюємо DOM-аналізатор
            DocumentBuilder db = dbf.newDocumentBuilder();

            // Парсимо XML-файл та отримуємо DOM-документ
            Document document = db.parse(xmlFile);

            // Реалізуйте код для конвертації DOM-документа у ваш Java-об'єкт (YourClass)
            UsersEvents list=new UsersEvents();
           list.setUserEvent(convertDocumentToUserEvents(document));

            return list; // Повертаємо об'єкт вашого класу

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String marshalWithValidation(UsersEvents list, File outputfile) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // Create a new Document
            Document document = documentBuilder.newDocument();

            // Create the root element
            Element usersEventsElement = document.createElement("x:UsersEvents");
            usersEventsElement.setAttribute("xmlns:x", "http://it.nure.ua/matsuytskij/xml/");
            usersEventsElement.setAttribute("xmlns:even", "http://it.nure.ua/matsuytskij/xml/event");
            usersEventsElement.setAttribute("xmlns:plac", "http://it.nure.ua/matsuytskij/xml/place");
            usersEventsElement.setAttribute("xmlns:user", "http://it.nure.ua/matsuytskij/xml/user");
            usersEventsElement.setAttribute("xmlns:role", "http://it.nure.ua/matsuytskij/xml/role");
            document.appendChild(usersEventsElement);


            for(UsersEvents.UserEvent e:list.getUserEvent()) {
                Element userEvent = document.createElement("x:UserEvent");
                usersEventsElement.appendChild(userEvent);
                UsersEvents.UserEvent yourObject = e;

                Element event = document.createElement("x:event");
                userEvent.appendChild(event);
                marshalforEvent(event,e,document);

                Element user = document.createElement("x:user");
                userEvent.appendChild(user);
                marshalforuser(user,e,document);
                // Create elements based on the object properties and add them to the root
                addNewElement(document,"x:presence",e.getPresence()+"",userEvent);
            }

            // Transform the Document to XML and save it to the specified file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputfile);
            transformer.transform(source, result);
            System.out.println("XML file created successfully at: " + outputfile.getPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void addNewElement(Document document,String nameElement,String value,Element fatherElement){
        Element element = document.createElement(nameElement);
        element.appendChild(document.createTextNode(value));
        fatherElement.appendChild(element);
    }

    private static void marshalforuser(Element user, UsersEvents.UserEvent userEvent, Document document){
        User tmp=userEvent.getUser();
        addNewElement(document,"user:id",tmp.getId()+"",user);
        addNewElement(document,"user:surname",tmp.getSurname()+"",user);
        addNewElement(document,"user:firstname",tmp.getFirstname()+"",user);
        addNewElement(document,"user:password",tmp.getPassword()+"",user);
        addNewElement(document,"user:email",tmp.getEmail()+"",user);

        Element role = document.createElement("user:role");
        addNewElement(document,"role:name",tmp.getRole().getName()+"",role);
        addNewElement(document,"role:idRole",tmp.getRole().getIdRole()+"",role);
        user.appendChild(role);

    }
    private static void marshalforEvent(Element event, UsersEvents.UserEvent userEvent, Document document){
        Event tmp=userEvent.getEvent();
        event.setAttribute("type", tmp.getType());
        if(!tmp.getOrganizer().equals(""))
            event.setAttribute("organizer",tmp.getOrganizer());

        addNewElement(document,"even:id_event",tmp.getId_event()+"",event);
        addNewElement(document,"even:name",tmp.getName()+"",event);
        addNewElement(document,"even:date",tmp.getDate()+"",event);
        Element place = document.createElement("even:place");
        if(tmp.getPlace().getOnline()==null){
            addNewElement(document,"plac:offline",tmp.getPlace().getOffline()+"",place);
        }else{
            addNewElement(document,"plac:online",tmp.getPlace().getOnline()+"",place);
        }
        event.appendChild(place);
        addNewElement(document,"even:description",tmp.getDescription()+"",event);
        addNewElement(document,"even:pinned",tmp.getPinned()+"",event);
        addNewElement(document,"even:time",tmp.getTime()+"",event);
    }
    private static List<UsersEvents.UserEvent> convertDocumentToUserEvents(Document document) {
        List<UsersEvents.UserEvent> userEvents = new ArrayList<>();
        //document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        NodeList userEventList = root.getElementsByTagNameNS("http://it.nure.ua/matsuytskij/xml/","UserEvent");

        for (int i = 0; i < userEventList.getLength(); i++) {
            Element userEventElement = (Element) userEventList.item(i);
            userEvents.add(convertElementToUserEvent(userEventElement));
        }
        return userEvents;
    }

    // Метод для конвертації елемента DOM у об'єкт UserEvent
    private static UsersEvents.UserEvent convertElementToUserEvent(Element userEventElement) {
        UsersEvents.UserEvent userEvent = new UsersEvents.UserEvent();

        Node eventNode= userEventElement.getElementsByTagNameNS("http://it.nure.ua/matsuytskij/xml/","event").item(0);
        Element ele= (Element) eventNode;
        Event event = convertElementToEvent(ele);
        userEvent.setEvent(event);

        Node userNode= userEventElement.getElementsByTagNameNS("http://it.nure.ua/matsuytskij/xml/","user").item(0);
        Element elem= (Element) userNode;
        User user = convertElementToUser(elem);
        userEvent.setUser(user);

        Node presense= userEventElement.getElementsByTagNameNS("http://it.nure.ua/matsuytskij/xml/","presence").item(0);
        userEvent.setPresence(Integer.valueOf(presense.getTextContent()));

        return userEvent;
    }

    // Метод для конвертації елемента DOM у об'єкт Event
    private static Event convertElementToEvent(Element eventElement) {
        Event event = new Event();
        event.setType(eventElement.getAttribute("type"));
        event.setOrganizer(eventElement.getAttribute("organizer"));
        NodeList list=eventElement.getChildNodes();
        for(int i=0;i<list.getLength();i++) {
            if (eventElement.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {

                String name=eventElement.getChildNodes().item(i).getLocalName();
                String value=eventElement.getChildNodes().item(i).getTextContent();

                if(name.equals("id_event")){
                   event.setId_event(BigInteger.valueOf(Integer.valueOf(value)));
                }else if(name.equals("name")){
                    event.setName(value);
                }else if(name.equals("date")){
                    event.setDate(value);
                }else if(name.equals("place")){
                    Place place=new Place();
                    if(value.equals("online")){
                        place.setOnline(value);
                    }else{
                        place.setOffline(value);
                    }
                    event.setPlace(place);
                }else if(name.equals("description")){
                    event.setDescription(value);
                }else if(name.equals("pinned")){
                    event.setPinned(Integer.parseInt(value));
                }else if(name.equals("time")){
                    event.setTime(value);
                }

            }
        }
        return event;
    }

    // Метод для конвертації елемента DOM у об'єкт User
    private static User convertElementToUser(Element userElement) {
        User user = new User();
        NodeList list=userElement.getChildNodes();
        for(int i=0;i<list.getLength();i++) {
            if (userElement.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {

                String name=userElement.getChildNodes().item(i).getLocalName();
                String value=userElement.getChildNodes().item(i).getTextContent();
                if(name.equals("id")){
                    user.setId(Integer.valueOf(value));
                }else if(name.equals("surname")){
                    user.setSurname(value);
                }else if(name.equals("firstname")){
                    user.setFirstname(value);
                }else if(name.equals("email")){
                    user.setEmail(value);
                }else if(name.equals("role")){
                    Role role=new Role();
                    Node roleNode=userElement.getChildNodes().item(i);
                    NodeList objs=roleNode.getChildNodes();
                    for(int k=0;k<objs.getLength();k++) {
                    if (objs.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        if(objs.item(k).getLocalName().equals("idRole")){
                            role.setIdRole(BigInteger.valueOf(Integer.valueOf(objs.item(k).getTextContent())));
                        }else if(objs.item(k).getLocalName().equals("name")){
                            role.setName(objs.item(k).getTextContent());
                        }
                    }
                    user.setRole(role);
                }
                }else if(name.equals("password")){
                    user.setPassword(value);
                }

            }
        }

        // Додайте інші поля User за потребою

        return user;
    }



    public static void main(String[] args) {
        String XML_FILE_PATH="./src/main/resources/XSDFiles/UsersEvents.xml";
        String XSD_FILE_PATH="./src/main/resources/XSDFiles/classes.xsd";
        File xmlFile = new File(XML_FILE_PATH);
        File xsdFile = new File(XSD_FILE_PATH);
        File outputXmlFile = new File("./src/main/resources/XSDFiles/generatedByDom/usersEvents.xml");

        // Демаршалізація з валідацією
        UsersEvents list = unmarshalWithValidation(xmlFile, xsdFile);

        System.out.println(list.getUserEvent());
        // Ваш код для роботи з DOM-документом, наприклад, модифікація об'єкта Document
        marshalWithValidation(list,outputXmlFile);
        // Маршалізація з валідацією

    }
}
