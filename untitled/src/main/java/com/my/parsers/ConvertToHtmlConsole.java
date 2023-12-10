package com.my.parsers;

public class ConvertToHtmlConsole {
    public static void main(String[] args) throws Exception {
        XSLTransformConsole.main(new String[]{
                "./src/main/resources/XSDFiles/events.xml",
                "./src/main/resources/XSDFiles/events_test.xsl",
                "./src/main/resources/XSDFiles/result.html"
        });
    }
}
