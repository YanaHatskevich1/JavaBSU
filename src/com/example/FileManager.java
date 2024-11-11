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

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    ZipArchiver.main(null); // Запуск архивации
                    break;
                case 2:
                    ZipExtractor.main(null); // Запуск распаковки
                    break;
                case 3:
                    FileEncryptor.main(null); // Запуск шифрования
                    break;
                case 4:
                    FileEncryptor.main(null); // Запуск дешифрования
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
