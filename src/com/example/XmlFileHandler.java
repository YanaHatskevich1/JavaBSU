package com.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlFileHandler {

    // Метод для чтения XML файла
    public <T> T readXmlFile(String filePath, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Метод для записи XML файла
    public <T> void writeXmlFile(String filePath, T data) {
        try {
            JAXBContext context = JAXBContext.newInstance(data.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(data, new File(filePath));
            System.out.println("XML файл успешно записан.");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
