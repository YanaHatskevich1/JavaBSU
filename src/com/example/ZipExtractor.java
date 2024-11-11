package com.example;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractor {
    public static void main(String[] args) {
        String zipFile = "output.zip";  // Имя ZIP-архива
        String destFile = "output_unzipped.txt"; // Имя распакованного файла

        try (FileInputStream fis = new FileInputStream(zipFile);
             ZipInputStream zipIn = new ZipInputStream(fis);
             FileOutputStream fos = new FileOutputStream(destFile)) {

            ZipEntry zipEntry = zipIn.getNextEntry();
            if (zipEntry != null) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = zipIn.read(buffer)) >= 0) {
                    fos.write(buffer, 0, length);
                }
                zipIn.closeEntry();
                System.out.println("Файл успешно распакован в: " + destFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
