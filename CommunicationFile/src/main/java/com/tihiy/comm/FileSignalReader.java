package com.tihiy.comm;

import com.tihiy.comm.parse.Reo32Parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

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

    public Map<Reo32Parser.Signal, List<Double>> readFileToMap(File file){
        Reo32Parser parser = new Reo32Parser();
        String string = null;
        try {
            string = readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser.parse(string);
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
