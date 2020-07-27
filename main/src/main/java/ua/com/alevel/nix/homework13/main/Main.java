package ua.com.alevel.nix.homework13.main;

import ua.com.alevel.nix.homework13.utils.FileSystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {

//    1. Написать программу, которая читает файл построчно и выводит только те строки, которые содержат указанную подстроку.
//
//    2. Написать программу, которая рекурсивно копирует содержимое каталога в указанное место.


    public static void main(String[] args) {

        System.out.println("\n Задание 1\n");
        System.out.println("Написать программу, которая читает файл построчно" +
                "\nи выводит только те строки, которые содержат указанную подстроку.");
        File textFile = new File("text.txt");
        String substring = "eu";
        System.out.println("Прочтем файл \"text.txt\" и отфильтруем его на наличие подстроки \"" + substring + "\":");
        String textFilePath = textFile.getPath();
        List<String> stringsWithSubstring = FileSystemUtil.substractLinesWithSubstringFromFile(textFilePath, substring);
        System.out.println("Строки, содержащие \"" + substring + "\" из файла " + textFilePath + ":");
        stringsWithSubstring.forEach(System.out::println);
        System.out.println("\n---------------------------------------------------------------------");


        System.out.println("\n Задание 2\n");
        System.out.println("Написать программу, которая рекурсивно копирует содержимое каталога в указанное место.");
        String pathFrom = "rootDir";
        File catalogTo = new File("Catalog for newRootDir");
        if (catalogTo.mkdir()) {
            String pathTo = catalogTo.getPath();
            System.out.println("Скопируем каталог " + pathFrom + " вместе с содержимым в каталог " + pathTo + ".");
            try {
                FileSystemUtil.CopyWholeCatalog(pathFrom, pathTo);
                System.out.println("Получилось!");
                System.out.println("\n---------------------------------------------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else System.out.println("Что-то пошло не так!");
    }
}



