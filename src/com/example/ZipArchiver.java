package com.example;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchiver {

    public static void archiveFile(String sourceFilePath, String zipFilePath) {
        File sourceFile = new File(sourceFilePath);

        // Проверка на существование файла
        if (!sourceFile.exists()) {
            System.out.println("Файл " + sourceFilePath + " не существует.");
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOut = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(sourceFile)) {

            // Добавляем только имя файла в архив
            String fileName = sourceFile.getName();
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zipOut.write(buffer, 0, length);
            }

            zipOut.closeEntry();
            System.out.println("Файл успешно архивирован в: " + zipFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sourceFile = "example.txt"; // Исходный файл
        String zipFile = "archive.zip";    // Архив

        archiveFile(sourceFile, zipFile);
    }
}
