package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchiver {
    public static void main(String[] args) {
        String sourceFile = "output.txt"; // Файл, который нужно архивировать
        String zipFile = "output.zip";    // Имя ZIP-архива

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zipOut = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(sourceFile)) {

            ZipEntry zipEntry = new ZipEntry(sourceFile);
            zipOut.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zipOut.write(buffer, 0, length);
            }

            System.out.println("Файл успешно архивирован в: " + zipFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
