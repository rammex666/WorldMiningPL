package fr.rammex.miningWorld.utils;

import fr.rammex.miningWorld.MiningWorld;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {



    public boolean deleteWorld(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return(path.delete());
    }

    public static void saveWorldAsZip(String sourceFolderPath, String zipFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
             System.out.println("Zipping world from: " + sourceFolderPath + " to: " + zipFilePath);

            Path sourcePath = Paths.get(sourceFolderPath);
            Files.walk(sourcePath).forEach(path -> {
                try {
                    String zipEntryName = sourcePath.relativize(path).toString();
                    System.out.println("Adding to zip: " + zipEntryName);
                    if (Files.isDirectory(path)) {
                        if (!zipEntryName.isEmpty()) {
                            System.out.println("Creating directory entry in zip: " + zipEntryName);
                            zos.putNextEntry(new ZipEntry(zipEntryName + "/"));
                            zos.closeEntry();
                        }
                    } else {
                        System.out.println("Adding file to zip: " + zipEntryName);
                        zos.putNextEntry(new ZipEntry(zipEntryName));
                        Files.copy(path, zos);
                        zos.closeEntry();
                    }
                } catch (IOException e) {
                    System.err.println("Error zipping file: " + path + " - " + e.getMessage());
                    throw new UncheckedIOException(e);
                }
            });
        }
    }

}
