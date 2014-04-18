package com.tihiy.comm;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSignalReader {
    public String readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        System.out.println(path);
        String content = new String(Files.readAllBytes(path));
        return content;
    }
    public String readFile(File file) throws IOException {
        Path path = Paths.get(file.toURI());
        System.out.println(path);
        String content = new String(Files.readAllBytes(path));
        return content;
    }

//    public List<String> readFileToList(File file) throws IOException {
//        FileChannel fci = new FileInputStream(file).getChannel();
//        long size = fci.size();
//        StringBuffer buffer = fci.
//        MappedByteBuffer byteBuffer = fci.map(FileChannel.MapMode.READ_ONLY, 0, size);
//
//
//        return list;
//    }
}
