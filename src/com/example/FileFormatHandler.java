package com.example;

import java.util.Scanner;

public class FileFormatHandler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите формат файла:");
        System.out.println("1 - TXT");
        System.out.println("2 - JSON");
        System.out.println("3 - XML");
        System.out.println("4 - YAML");
        System.out.print("Введите номер формата (1, 2, 3, 4): ");
        int formatChoice = scanner.nextInt();

        // Автоматическое формирование имен файлов
        String inputFile = "";
        String outputFile = "";

        switch (formatChoice) {
            case 1:
                inputFile = "input.txt";
                outputFile = "output.txt";
                break;
            case 2:
                inputFile = "input.json";
                outputFile = "output.json";
                break;
            case 3:
                inputFile = "input.xml";
                outputFile = "output.xml";
                break;
            case 4:
                inputFile = "input.yaml";
                outputFile = "output.yaml";
                break;
            default:
                System.out.println("Неверный формат файла! Завершение программы.");
                return;
        }

        System.out.println("Выберите способ обработки:");
        System.out.println("1 - Без регулярных выражений");
        System.out.println("2 - С использованием регулярных выражений");
        System.out.println("3 - С использованием библиотеки Exp4j");
        System.out.print("Введите номер способа (1, 2, 3): ");
        int methodChoice = scanner.nextInt();

        // Обработка файла
        switch (formatChoice) {
            case 1:
                FileHandler.processFile(inputFile, outputFile, methodChoice);
                break;
            case 2:
                JsonFileHandler.processFile(inputFile, outputFile, methodChoice);
                break;
            case 3:
                XMLFileHandler.processFile(inputFile, outputFile, methodChoice);
                break;
            case 4:
                YAMLFileHandler.processFile(inputFile, outputFile, methodChoice);
                break;
        }

        System.out.println("Обработка завершена. Результаты записаны в " + outputFile);
        scanner.close();
    }
}
