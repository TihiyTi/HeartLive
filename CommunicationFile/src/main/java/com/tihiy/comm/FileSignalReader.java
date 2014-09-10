package com.tihiy.comm;

import com.tihiy.comm.parse.Reo32Parser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileSignalReader {
    private boolean useNewReader = false;
    public String readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        System.out.println(path);
        return new String(Files.readAllBytes(path));
    }
    public String readFile(File file) throws IOException {
        Path path = Paths.get(file.toURI());
        System.out.println(path);
        return new String(Files.readAllBytes(path));
    }

    public Scanner readFileNew(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return new Scanner(reader);
    }

    public Map<Reo32Parser.Signal, List<Double>> readFileToMap(File file){
        if(useNewReader){
//            return readFileToMapNew(file);
            return null;
        }else{
            return readFileToMapOld(file);
        }
    }

    public Map<String, List<Double>> readFileToMapNew(File file){
        Reo32Parser parser = new Reo32Parser();
        Scanner scanner = null;
        try {
            scanner = readFileNew(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser.parse(scanner);
    }

    private Map<Reo32Parser.Signal, List<Double>> readFileToMapOld(File file){
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
