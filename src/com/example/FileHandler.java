package com.example;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {

    private static final Pattern ARITHMETIC_PATTERN = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите тип файла для обработки (введите 'txt' или 'json'):");
        String fileType = scanner.nextLine().trim().toLowerCase();

        try {
            if ("txt".equals(fileType)) {
                processTextFile("input.txt", "output.txt");
            } else if ("json".equals(fileType)) {
                JsonFileHandler.processJsonFile("input.json", "output.json");
            } else {
                System.out.println("Неверный выбор. Пожалуйста, введите 'txt' или 'json'.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Обработка текстового файла
    public static void processTextFile(String inputFilePath, String outputFilePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append("Без регулярных выражений: ").append(processWithoutRegex(line)).append("\n");
                content.append("С регулярными выражениями: ").append(processWithRegex(line)).append("\n");
                content.append("С использованием библиотеки: ").append(processWithLibrary(line)).append("\n");
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(content.toString());
            System.out.println("Данные записаны в output.txt.");
        }
    }

    // Метод 1: Обработка без регулярных выражений
    public static String processWithoutRegex(String line) {
        try {
            String[] tokens = line.split(" ");
            double num1 = Double.parseDouble(tokens[0]);
            String operator = tokens[1];
            double num2 = Double.parseDouble(tokens[2]);

            switch (operator) {
                case "+":
                    return String.valueOf(num1 + num2);
                case "-":
                    return String.valueOf(num1 - num2);
                case "*":
                    return String.valueOf(num1 * num2);
                case "/":
                    if (num2 != 0) {
                        return String.valueOf(num1 / num2);
                    } else {
                        return "Ошибка: Деление на ноль";
                    }
                default:
                    return line;
            }
        } catch (Exception e) {
            return "Ошибка обработки строки: " + line;
        }
    }

    // Метод 2: Обработка с использованием регулярных выражений
    public static String processWithRegex(String line) {
        Matcher matcher = ARITHMETIC_PATTERN.matcher(line);

        if (matcher.matches()) {
            try {
                double num1 = Double.parseDouble(matcher.group(1));
                String operator = matcher.group(2);
                double num2 = Double.parseDouble(matcher.group(3));

                switch (operator) {
                    case "+":
                        return String.valueOf(num1 + num2);
                    case "-":
                        return String.valueOf(num1 - num2);
                    case "*":
                        return String.valueOf(num1 * num2);
                    case "/":
                        if (num2 != 0) {
                            return String.valueOf(num1 / num2);
                        } else {
                            return "Ошибка: Деление на ноль";
                        }
                    default:
                        return line;
                }
            } catch (NumberFormatException e) {
                return "Ошибка обработки чисел: " + line;
            }
        }
        return "Неверное выражение: " + line;
    }

    // Метод 3: Обработка с использованием библиотеки (Java вычисления)
    public static String processWithLibrary(String line) {
        return processWithRegex(line); // Используем регулярные выражения для парсинга и вычислений
    }
}
