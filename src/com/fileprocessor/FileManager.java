package com.fileprocessor;

import java.io.*;

public class FileManager {

    // Метод для чтения файла и возвращения содержимого в виде строки
    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");  // Добавляем перенос строки для сохранения форматирования
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return null;  // В случае ошибки возвращаем null
        }
        return content.toString();
    }

    // Метод для записи строки в файл
    public static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    // Главный метод для управления потоком данных
    public static void main(String[] args) {
        String inputFilePath = "input.txt";  
        String outputFilePath = "output.txt";

        // Чтение данных из файла
        String content = readFile(inputFilePath);
        if (content != null) {
            // Обработка данных (пример: простое копирование)
            String processedContent = content; // Здесь можно добавить логику обработки

            // Запись обработанных данных в другой файл
            writeFile(outputFilePath, processedContent);
        } else {
            System.out.println("Чтение файла не удалось.");
        }
    }
}
