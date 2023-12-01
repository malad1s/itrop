package WorkWithXML;
import java.io.File;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
public class XSLTransform {
    public static void main(String[] args) throws TransformerException {

        try {
            // Вхідний XML-файл
            Source xmlSource = new StreamSource(new File("./XSDFiles/events.xml"));

            // XSLT-файл
            Source xsltSource = new StreamSource(new File("./XSDFiles/events.xsl"));

            // Результат трансформації
            Result result = new StreamResult(new File("output.html"));

            // Створення об'єкта Transformer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(xsltSource);

            // Запуск трансформації
            transformer.transform(xmlSource, result);

            System.out.println("Transformation complete!");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
