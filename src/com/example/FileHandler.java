package com.example;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {

    public static void processFile(String inputFilePath, String outputFilePath, int method) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String processedLine = processLine(line, method);
                writer.write(processedLine);
                writer.newLine();
            }

            System.out.println("Обработка TXT завершена. Результаты записаны в " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String processLine(String line, int method) {
        switch (method) {
            case 1: return processWithoutRegex(line);
            case 2: return processWithRegex(line);
            case 3: return processWithLibrary(line);
            default: throw new IllegalArgumentException("Неверный метод!");
        }
    }

    private static String processWithoutRegex(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length == 3) {
            try {
                double left = Double.parseDouble(tokens[0]);
                double right = Double.parseDouble(tokens[2]);
                return String.valueOf(compute(left, tokens[1], right));
            } catch (NumberFormatException ignored) {}
        }
        return line;
    }

    private static String processWithRegex(String line) {
        String regex = "(\\d+)\\s*([+\\-*/])\\s*(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            double left = Double.parseDouble(matcher.group(1));
            String operator = matcher.group(2);
            double right = Double.parseDouble(matcher.group(3));
            double computedValue = compute(left, operator, right);
            matcher.appendReplacement(result, String.valueOf(computedValue));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    private static String processWithLibrary(String line) {
        String regex = "(\\d+\\s*[+\\-*/]\\s*\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String expression = matcher.group(0).replaceAll("\\s+", "");
            double evaluated = new net.objecthunter.exp4j.ExpressionBuilder(expression)
                    .build()
                    .evaluate();
            matcher.appendReplacement(result, String.valueOf(evaluated));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    private static double compute(double left, String operator, double right) {
        switch (operator) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/": return right != 0 ? left / right : 0;
            default: throw new IllegalArgumentException("Неизвестный оператор");
        }
    }
}
