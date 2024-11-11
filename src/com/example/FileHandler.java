package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {
    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        StringBuilder content = new StringBuilder();

        // Чтение из файла и обработка данных
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Выберите метод обработки (оптимизированная версия)
                content.append(processLine(line)).append("\n");
            }
            System.out.println("Файл прочитан: \n" + content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Запись в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(content.toString());
            System.out.println("Данные записаны в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод обработки строки: выбирает подходящий способ обработки
    private static String processLine(String line) {
        // Определите, использовать ли регулярные выражения
        if (isSimpleExpression(line)) {
            return calculateWithoutRegex(line);
        } else {
            return calculateWithRegex(line);
        }
    }

    // Проверка, является ли строка простым арифметическим выражением
    private static boolean isSimpleExpression(String line) {
        return line.matches("\\d+\\s*[+\\-*/]\\s*\\d+");
    }

    // Метод для вычисления без использования регулярных выражений
    private static String calculateWithoutRegex(String line) {
        String[] parts = line.trim().split("\\s+");
        try {
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[2]);
            String operator = parts[1];
            return String.valueOf(performCalculation(num1, num2, operator));
        } catch (Exception e) {
            return "Ошибка: Некорректное выражение";
        }
    }

    // Метод для вычисления с использованием регулярных выражений
    private static String calculateWithRegex(String line) {
        Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            try {
                int num1 = Integer.parseInt(matcher.group(1));
                int num2 = Integer.parseInt(matcher.group(3));
                String operator = matcher.group(2);
                return String.valueOf(performCalculation(num1, num2, operator));
            } catch (Exception e) {
                return "Ошибка: Некорректное выражение";
            }
        }
        return line; // Если выражение не подходит, вернуть исходную строку
    }

    // Общий метод для выполнения арифметических операций
    private static int performCalculation(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    throw new ArithmeticException("Деление на ноль");
                }
            default:
                throw new IllegalArgumentException("Некорректный оператор");
        }
    }
}
