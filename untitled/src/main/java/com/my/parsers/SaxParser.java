package com.my.parsers;

import JaxbClasses.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SaxParser {

    public static void main(String[] args) {
        String XML_FILE_PATH="./src/main/resources/XSDFiles/UsersEvents.xml";
        String XSD_FILE_PATH="./src/main/resources/XSDFiles/classes.xsd";

        SaxParser parser = new SaxParser();
        if (parser.validateXmlAgainstXsd(XML_FILE_PATH, XSD_FILE_PATH)) {

            UserHandler handler = parser.parseXml(XML_FILE_PATH);
            List<UsersEvents.UserEvent> userEvents = handler.getUserEvents();
            for (UsersEvents.UserEvent userEvent : userEvents) {
               System.out.println(userEvent);
            }
        } else {
            System.out.println("Validation failed");
        }
    }
    private static class CustomErrorHandler extends DefaultHandler {
        private boolean hasErrors = false;

        @Override
        public void error(SAXParseException e) throws SAXException {
            hasErrors = true;
            System.out.println("Error: " + e.getMessage());
        }

        @Override
        public void fatalError(SAXParseException e) throws SAXException {
            hasErrors = true;
            System.out.println("Fatal Error: " + e.getMessage());
        }

        @Override
        public void warning(SAXParseException e) throws SAXException {
            System.out.println("Warning: " + e.getMessage());
        }

        public boolean hasErrors() {
            return hasErrors;
        }
    }
    private boolean validateXmlAgainstXsd(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdFilePath));

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setSchema(schema);

            SAXParser parser = factory.newSAXParser();
            CustomErrorHandler errorHandler = new CustomErrorHandler();
            parser.parse(new File(xmlFilePath), errorHandler);
            return !errorHandler.hasErrors();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private UserHandler parseXml(String xmlFilePath) {
        try {
            UserHandler handler = new UserHandler();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            SAXParser parser = factory.newSAXParser();

            parser.parse(new File(xmlFilePath), handler);
            return handler;
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}



class UserHandler extends DefaultHandler {
    private List<UsersEvents.UserEvent> userEvents;
    private UsersEvents.UserEvent currentUserEvent;
    private StringBuilder data;

    @Override
    public void startDocument() throws SAXException {
        userEvents = new ArrayList<>();
    }

    public List<UsersEvents.UserEvent> getUserEvents() {
        return userEvents;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if ("x:UserEvent".equals(qName)) {
            currentUserEvent = new UsersEvents.UserEvent();
        } else if ("x:event".equals(qName)) {
            currentUserEvent.setEvent(new Event());
        } else if ("even:place".equals(qName)) {
            currentUserEvent.getEvent().setPlace(new Place());
        } else if ("x:user".equals(qName)) {
            currentUserEvent.setUser(new User());
        } else if ("user:role".equals(qName)) {
            currentUserEvent.getUser().setRole(new Role());
        }
        if ("x:event".equals(qName)&&attributes.getLength() > 0) {
            for (int i = 0; i < attributes.getLength(); i++) {
                String attributeName = attributes.getQName(i);
                String attributeValue = attributes.getValue(i);
                if(attributeName.equals("type")){
                    currentUserEvent.getEvent().setType(attributeValue);
                }
                if(attributeName.equals("organizer")){
                    currentUserEvent.getEvent().setOrganizer(attributeValue);
                }
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (data == null) {
            data = new StringBuilder();
        } else {
            data.append(ch, start, length);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //System.out.println(qName+" "+ data.toString().trim());
        switch (qName) {
            case "x:presence":
                currentUserEvent.setPresence(Integer.parseInt(data.toString().trim()));
                data.setLength(0);
                break;
            case "even:id_event":
                currentUserEvent.getEvent().setId_event(BigInteger.valueOf(Integer.valueOf(data.toString().trim())));
                data.setLength(0);
                break;
            case "even:name":
                currentUserEvent.getEvent().setName(data.toString().trim());
                data.setLength(0);
                break;
            case "even:date":
                currentUserEvent.getEvent().setDate(data.toString().trim());
                data.setLength(0);
                break;
            case "plac:online":
                currentUserEvent.getEvent().getPlace().setOnline(data.toString().trim());
                data.setLength(0);
                break;
            case "plac:offline":
                currentUserEvent.getEvent().getPlace().setOffline(data.toString().trim());
                data.setLength(0);
                break;
            case "even:description":
                currentUserEvent.getEvent().setDescription(data.toString().trim());
                data.setLength(0);
                break;
            case "even:pinned":
                currentUserEvent.getEvent().setPinned(Integer.parseInt(data.toString().trim()));
                data.setLength(0);
                break;
            case "even:time":
                currentUserEvent.getEvent().setTime(data.toString().trim());
                data.setLength(0);
                break;
            case "user:id":
                currentUserEvent.getUser().setId(Integer.parseInt(data.toString().trim()));
                data.setLength(0);
                break;
            case "user:surname":
                currentUserEvent.getUser().setSurname(data.toString().trim());
                data.setLength(0);
                break;
            case "user:firstname":
                currentUserEvent.getUser().setFirstname(data.toString().trim());
                data.setLength(0);
                break;
            case "user:password":
                currentUserEvent.getUser().setPassword(data.toString().trim());
                data.setLength(0);
                break;
            case "user:email":
                currentUserEvent.getUser().setEmail(data.toString().trim());
                data.setLength(0);
                break;
            case "role:name":
                currentUserEvent.getUser().getRole().setName(data.toString().trim());
                data.setLength(0);
                break;
            case "role:idRole":
                currentUserEvent.getUser().getRole().setIdRole(BigInteger.valueOf(Integer.parseInt(data.toString().trim())));
                data.setLength(0);
                break;
            case "x:UserEvent":
                userEvents.add(currentUserEvent);
                data.setLength(0);
                break;

        }

    }
}


