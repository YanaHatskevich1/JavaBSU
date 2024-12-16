package com.example;

import java.util.Scanner;

public class FileManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Архивировать файл");
            System.out.println("2. Распаковать файл");
            System.out.println("3. Зашифровать файл");
            System.out.println("4. Расшифровать файл");
            System.out.println("5. Выйти");

            System.out.print("Введите номер действия: ");
            String choiceInput = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите число от 1 до 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Введите путь к файлу для архивирования: ");
                    String sourceFile = scanner.nextLine();
                    System.out.print("Введите имя ZIP-архива: ");
                    String zipFile = scanner.nextLine();
                    ZipArchiver.main(new String[]{sourceFile, zipFile});
                    break;
                case 2:
                    System.out.print("Введите путь к ZIP-архиву: ");
                    String zipFileToExtract = scanner.nextLine();
                    System.out.print("Введите имя распакованного файла: ");
                    String destFile = scanner.nextLine();
                    ZipExtractor.main(new String[]{zipFileToExtract, destFile});
                    break;
                case 3:
                    System.out.print("Введите путь к файлу для шифрования: ");
                    String fileToEncrypt = scanner.nextLine();
                    System.out.print("Введите имя зашифрованного файла: ");
                    String encryptedFile = scanner.nextLine();
                    FileEncryptor.main(new String[]{"encrypt", fileToEncrypt, encryptedFile});
                    break;
                case 4:
                    System.out.print("Введите путь к зашифрованному файлу: ");
                    String fileToDecrypt = scanner.nextLine();
                    System.out.print("Введите имя расшифрованного файла: ");
                    String decryptedFile = scanner.nextLine();
                    FileEncryptor.main(new String[]{"decrypt", fileToDecrypt, decryptedFile});
                    break;
                case 5:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}
