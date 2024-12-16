package com.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XMLFileHandler {

    public static void processFile(String inputFilePath, String outputFilePath, int method) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(inputFilePath));

            processNode(document.getDocumentElement(), method);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File(outputFilePath)));

            System.out.println("Обработка XML завершена. Результаты записаны в " + outputFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processNode(Node node, int method) {
        if (node.getNodeType() == Node.TEXT_NODE) {
            node.setTextContent(FileHandler.processLine(node.getTextContent(), method));
        } else if (node.hasChildNodes()) {
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                processNode(children.item(i), method);
            }
        }
    }
}
