package com.example;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

public class YAMLFileHandler {

    public static void processFile(String inputFilePath, String outputFilePath, int method) {
        try (InputStream inputStream = new FileInputStream(inputFilePath);
             FileWriter writer = new FileWriter(outputFilePath)) {

            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);

            processYamlData(data, method);
            yaml.dump(data, writer);

            System.out.println("Обработка YAML завершена. Результаты записаны в " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processYamlData(Map<String, Object> data, int method) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof String) {
                entry.setValue(FileHandler.processLine((String) entry.getValue(), method));
            } else if (entry.getValue() instanceof Map) {
                processYamlData((Map<String, Object>) entry.getValue(), method);
            } else if (entry.getValue() instanceof List) {
                processYamlList((List<Object>) entry.getValue(), method);
            }
        }
    }

    private static void processYamlList(List<Object> list, int method) {
        for (int i = 0; i < list.size(); i++) {
            Object value = list.get(i);
            if (value instanceof String) {
                list.set(i, FileHandler.processLine((String) value, method));
            } else if (value instanceof Map) {
                processYamlData((Map<String, Object>) value, method);
            }
        }
    }
}
