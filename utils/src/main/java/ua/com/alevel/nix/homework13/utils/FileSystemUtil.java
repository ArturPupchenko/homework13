package ua.com.alevel.nix.homework13.utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileSystemUtil {

//    1. Написать программу, которая читает файл построчно и выводит только те строки, которые содержат указанную подстроку.


    //    \p{P} is "Any punctuation character"
    //    \p{Z} is "Any whitespace character"
    public static List<String> substractLinesWithSubstringFromFile(String path, String substring) {
        String lineFromFile;
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while ((lineFromFile = bufferedReader.readLine()) != null) {
                String[] lineAsStringsArray = lineFromFile.replaceAll("\\p{P}", " ").split(" ");
                for (String elem : lineAsStringsArray) {
                    if (elem.contains(substring))
                        result.append(elem).append(" ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(result.toString().split("\n"));
    }


//    2. Написать программу, которая рекурсивно копирует содержимое каталога в указанное место.


    public static void CopyWholeCatalog(String pathFrom, String pathTo) throws IOException {
        copyCatalog(pathFrom, pathTo);
    }

    public static void copyCatalog(String root, String pathTo) throws IOException {
        File file = new File(root);
        copyCatalog(file, pathTo + File.separator + file.getName());
    }

    private static void copyCatalog(File currentFile, String pathTo) throws IOException {
        String path;
        File copyDir = new File(pathTo);
        File copyFile;

        if (!copyDir.mkdir())
            throw new IOException("Не удалось создать каталог " + copyDir.getAbsolutePath() + ".");

        if (currentFile.isDirectory()) {
            File[] filesList = currentFile.listFiles();

            if (filesList == null)
                return;

            for (File file : filesList) {
                path = pathTo + File.separator + file.getName();
                if (file.isFile()) {
                    copyFile = new File(path);
                    try {
                        if (!copyFile.createNewFile())
                            throw new IOException("Не удалось создать файл " + copyFile.getAbsolutePath() + " .");

                        if (file.length() != 0)
                            copyTextFromFile(file, copyFile);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    copyCatalog(file, path);
            }
        }
    }

    private static void copyTextFromFile(File from, File to) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(from)); PrintStream out = new PrintStream(new FileOutputStream(to))) {
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


