package com.example;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractor {

    public static void extractFiles(String zipFilePath, String destinationFolder) {
        File destDir = new File(destinationFolder);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        try (FileInputStream fis = new FileInputStream(zipFilePath);
             ZipInputStream zipIn = new ZipInputStream(fis)) {

            ZipEntry zipEntry;
            while ((zipEntry = zipIn.getNextEntry()) != null) {
                // Получаем имя файла
                String filePath = destinationFolder + File.separator + zipEntry.getName();

                // Создаем выходной файл
                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zipIn.read(buffer)) >= 0) {
                        fos.write(buffer, 0, length);
                    }
                }

                zipIn.closeEntry();
                System.out.println("Файл " + zipEntry.getName() + " успешно извлечен в " + filePath);
            }

            System.out.println("Извлечение завершено.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String zipFile = "archive.zip";          // Архив
        String destinationFolder = "outputDir";  // Папка для распаковки

        extractFiles(zipFile, destinationFolder);
    }
}
