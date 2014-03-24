package com.tihiy.rclint;

import com.google.common.io.Closeables;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Прочтение текстового файла. Возвращает список List<double>
 *
 */
//TODO вынести функционал в Communication !!!! удалить из этого package
public class ReadingFiles {
  static List<Double> readFile(String fileName) throws IOException {
    List<Double> xList = new ArrayList<>(); // создание объекта списка xList
    BufferedReader reader = null; // буффер для прочтения
    try {
      reader = new BufferedReader(new FileReader(new File(fileName))); // загрузка файла в буфер
      Scanner scanner = new Scanner(reader); // создание переменной для чтения строк из файла
      while (scanner.hasNextLine()) {
        xList.add(Double.valueOf(scanner.nextLine().trim())); // запись строки в i-ую ячейку списка
      }
    }
    finally {
      Closeables.close(reader, true);
    }
    return xList; // возвращает список данных, записанных из файла
  }

    public static List<Double> readFile(File file) throws IOException {
        List<Double> xList = new ArrayList<>(); // создание объекта списка xList
        BufferedReader reader = null; // буффер для прочтения
        try {
            reader = new BufferedReader(new FileReader(file)); // загрузка файла в буфер
            Scanner scanner = new Scanner(reader); // создание переменной для чтения строк из файла
            while (scanner.hasNextLine()) {
                xList.add(Double.valueOf(scanner.nextLine().trim())); // запись строки в i-ую ячейку списка
            }
        }
        finally {
            Closeables.close(reader, true);
        }
        return xList; // возвращает список данных, записанных из файла
    }
}

