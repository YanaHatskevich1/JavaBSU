package com.example;

import com.google.gson.*;

import java.io.*;

public class JsonFileHandler {

    public static void processFile(String inputFilePath, String outputFilePath, int method) {
        try (Reader reader = new FileReader(inputFilePath);
             Writer writer = new FileWriter(outputFilePath)) {

            // Создаем парсер и читаем JSON файл
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement root = JsonParser.parseReader(reader);

            // Обрабатываем JSON элементы
            processJsonElement(root, method);

            // Записываем обновленный JSON в файл
            gson.toJson(root, writer);
            System.out.println("Обработка JSON завершена. Результаты записаны в " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Рекурсивно обрабатывает JsonElement и обновляет его значения.
     */
    private static void processJsonElement(JsonElement element, int method) {
        if (element.isJsonObject()) {
            // Обрабатываем JsonObject
            JsonObject obj = element.getAsJsonObject();
            obj.entrySet().forEach(entry -> {
                JsonElement value = entry.getValue();
                if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isString()) {
                    // Обновляем строковое значение с вычисленным результатом
                    String processedValue = FileHandler.processLine(value.getAsString(), method);
                    entry.setValue(new JsonPrimitive(processedValue));
                } else {
                    // Рекурсивно обрабатываем дочерние элементы
                    processJsonElement(value, method);
                }
            });
        } else if (element.isJsonArray()) {
            // Обрабатываем JsonArray
            JsonArray array = element.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonElement item = array.get(i);
                if (item.isJsonPrimitive() && item.getAsJsonPrimitive().isString()) {
                    // Обновляем строковое значение в массиве
                    String processedValue = FileHandler.processLine(item.getAsString(), method);
                    array.set(i, new JsonPrimitive(processedValue));
                } else {
                    // Рекурсивно обрабатываем дочерние элементы
                    processJsonElement(item, method);
                }
            }
        }
    }
}
