package com.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FileEncryptor {
    private static final String ALGORITHM = "AES";

    public static void main(String[] args) {
        String inputFile = "output.txt";
        String encryptedFile = "output_encrypted.txt";
        String decryptedFile = "output_decrypted.txt";

        try {
            // Генерация секретного ключа
            SecretKey secretKey = generateKey();

            // Шифрование файла
            encryptFile(secretKey, inputFile, encryptedFile);
            System.out.println("Файл успешно зашифрован: " + encryptedFile);

            // Дешифрование файла
            decryptFile(secretKey, encryptedFile, decryptedFile);
            System.out.println("Файл успешно расшифрован: " + decryptedFile);

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

    // Шифрование файла
    private static void encryptFile(SecretKey key, String inputFile, String outputFile) throws Exception {
        processFile(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    // Дешифрование файла
    private static void decryptFile(SecretKey key, String inputFile, String outputFile) throws Exception {
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
