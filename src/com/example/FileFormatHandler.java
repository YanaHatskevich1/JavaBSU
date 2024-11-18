package com.example;

import java.io.*;
import java.util.Scanner;

public class FileFormatHandler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Обработать файл");
            System.out.println("2. Выйти");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    selectFileType();
                    break;
                case "2":
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите 1 или 2.");
            }
        }
    }

    // Метод выбора типа файла
    private static void selectFileType() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите формат файла для обработки (введите 'txt' или 'json'):");
        String fileType = scanner.nextLine().trim().toLowerCase();

        try {
            switch (fileType) {
                case "txt":
                    selectProcessingMethod("input.txt", "output.txt");
                    break;
                case "json":
                    JsonFileHandler.processJsonFile("input.json", "output.json");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, введите 'txt' или 'json'.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод выбора способа обработки
    private static void selectProcessingMethod(String inputFilePath, String outputFilePath) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите метод обработки:");
        System.out.println("1. Без регулярных выражений");
        System.out.println("2. С использованием регулярных выражений");
        System.out.println("3. С использованием библиотеки");

        String methodChoice = scanner.nextLine();

        switch (methodChoice) {
            case "1":
                processTextFile(inputFilePath, outputFilePath, "withoutRegex");
                break;
            case "2":
                processTextFile(inputFilePath, outputFilePath, "withRegex");
                break;
            case "3":
                processTextFile(inputFilePath, outputFilePath, "withLibrary");
                break;
            default:
                System.out.println("Неверный выбор. Пожалуйста, выберите 1, 2 или 3.");
        }
    }

    // Обработка текстового файла с выбором метода
    private static void processTextFile(String inputFilePath, String outputFilePath, String method) throws IOException {
        StringBuilder content = new StringBuilder();

        System.out.println("Начало обработки текстового файла...");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                switch (method) {
                    case "withoutRegex":
                        content.append(FileHandler.processWithoutRegex(line)).append("\n");
                        break;
                    case "withRegex":
                        content.append(FileHandler.processWithRegex(line)).append("\n");
                        break;
                    case "withLibrary":
                        content.append(FileHandler.processWithLibrary(line)).append("\n");
                        break;
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(content.toString());
            System.out.println("Результаты записаны в " + outputFilePath);
        }
    }
}
