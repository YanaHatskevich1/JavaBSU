package com.example;

import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileHandler {

    public static void processJsonFile(String inputFilePath, String outputFilePath) throws IOException {
        JsonElement jsonElement = readJsonFile(inputFilePath);
        JsonElement processedElement = processJsonElement(jsonElement);
        writeJsonFile(outputFilePath, processedElement);
        System.out.println("Данные записаны в output.json.");
    }

    public static JsonElement readJsonFile(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        JsonElement jsonElement = JsonParser.parseReader(reader);
        reader.close();
        return jsonElement;
    }

    public static void writeJsonFile(String filePath, JsonElement element) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(element, writer);
        writer.close();
    }

    public static JsonElement processJsonElement(JsonElement element) {
        if (element.isJsonObject()) {
            JsonObject jsonObject = element.getAsJsonObject();
            for (String key : jsonObject.keySet()) {
                JsonElement value = jsonObject.get(key);
                if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isString()) {
                    String valueStr = value.getAsString();
                    valueStr = FileHandler.processWithRegex(valueStr);
                    jsonObject.addProperty(key, valueStr);
                }
            }
        }
        return element;
    }
}
