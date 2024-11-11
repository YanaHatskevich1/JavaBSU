package com.example;

import java.io.*;
import java.util.Scanner;

public class FileFormatHandler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Обработать арифметические операции в текстовом файле");
            System.out.println("2. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистить буфер

            if (choice == 1) {
                processArithmeticOperations();
            } else if (choice == 2) {
                System.out.println("Выход из программы.");
                break;
            } else {
                System.out.println("Некорректный выбор. Пожалуйста, попробуйте снова.");
            }
        }

        scanner.close();
    }

    // Метод для обработки арифметических операций
    private static void processArithmeticOperations() {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";
        StringBuilder content = new StringBuilder();

        // Чтение и обработка файла
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(processLine(line)).append("\n");
            }
            System.out.println("Файл обработан.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Запись результата в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(content.toString());
            System.out.println("Результаты записаны в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод обработки строки (можно оптимизировать, если нужно)
    private static String processLine(String line) {
        // Пример простого обработчика, можно расширить
        try {
            String[] parts = line.split(" ");
            int num1 = Integer.parseInt(parts[0]);
            String operator = parts[1];
            int num2 = Integer.parseInt(parts[2]);
            int result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        return "Ошибка: деление на ноль";
                    }
                    break;
                default:
                    return "Ошибка: неизвестный оператор";
            }
            return String.valueOf(result);
        } catch (Exception e) {
            return "Ошибка: некорректное выражение";
        }
    }
}
