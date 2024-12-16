package com.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class FileEncryptor {
    private static final String ALGORITHM = "AES";

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Использование: java FileEncryptor <encrypt|decrypt> <inputFile> <outputFile>");
            return;
        }

        String action = args[0];
        String inputFile = args[1];
        String outputFile = args[2];

        try {
            if ("encrypt".equalsIgnoreCase(action)) {
                SecretKey secretKey = generateKey();
                encryptFile(secretKey, inputFile, outputFile);
                System.out.println("Файл успешно зашифрован: " + outputFile);
                // Вывод ключа в шестнадцатеричном формате для последующего использования при дешифровании
                byte[] keyBytes = secretKey.getEncoded();
                StringBuilder keyHex = new StringBuilder();
                for (byte b : keyBytes) {
                    keyHex.append(String.format("%02X", b));
                }
                System.out.println("Секретный ключ (для дешифрования сохраните его): " + keyHex.toString());
            } else if ("decrypt".equalsIgnoreCase(action)) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите секретный ключ (hex): ");
                String keyHex = scanner.nextLine();
                SecretKey secretKey = hexToSecretKey(keyHex);
                decryptFile(secretKey, inputFile, outputFile);
                System.out.println("Файл успешно расшифрован: " + outputFile);
                scanner.close();
            } else {
                System.out.println("Неверное действие. Используйте 'encrypt' или 'decrypt'.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Генерация секретного ключа AES
    private static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128); // Длина ключа 128 бит
        return keyGen.generateKey();
    }

    // Конвертация hex строки в SecretKey
    private static SecretKey hexToSecretKey(String hex) {
        byte[] keyBytes = new byte[hex.length() / 2];
        for(int i = 0; i < keyBytes.length; i++) {
            keyBytes[i] = (byte) Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
        }
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    // Шифрование файла
    public static void encryptFile(SecretKey key, String inputFile, String outputFile) throws Exception {
        processFile(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    // Дешифрование файла
    public static void decryptFile(SecretKey key, String inputFile, String outputFile) throws Exception {
        processFile(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    // Метод для обработки файла (шифрование/дешифрование)
    private static void processFile(int mode, SecretKey key, String inputFile, String outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(mode, key);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    fos.write(output);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                fos.write(outputBytes);
            }
        }
    }
}
