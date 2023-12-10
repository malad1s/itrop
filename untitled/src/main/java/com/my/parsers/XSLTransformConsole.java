package com.my.parsers;


import java.io.File;
import java.io.FileOutputStream;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import javax.xml.transform.*;

public class XSLTransformConsole {
    public static void main(String[] args) throws TransformerException {
        if (args.length != 3) {
            System.out.println("Usage: ConsoleApp <input-xml-path> <xslt-path> <output-html-path>");
            return;
        }
        String xmlFilePath = args[0];
        String xsltFilePath = args[1];
        String htmlFilePath = args[2];
        System.out.println(xmlFilePath);
        System.out.println(xsltFilePath);
        System.out.println(htmlFilePath);
        try {
            transformXmlToHtml(xmlFilePath, xsltFilePath, htmlFilePath);
            System.out.println("Transformation completed successfully. Output HTML file: " + htmlFilePath);
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
            System.out.println("Transformation complete!");
    }
    private static void transformXmlToHtml(String xmlFilePath, String xsltFilePath, String htmlFilePath) throws Exception {
        try {
            TransformerFactory transformerFactory =TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltFilePath));
            transformer.transform(new StreamSource(xmlFilePath),new StreamResult(new FileOutputStream(htmlFilePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
