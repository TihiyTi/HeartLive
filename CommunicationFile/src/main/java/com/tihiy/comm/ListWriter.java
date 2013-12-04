package com.tihiy.comm;

import java.io.*;
import java.util.List;

public class ListWriter <T>{
    private File fileDirectory;

    public ListWriter(){}

    public ListWriter(File fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public void writeListToFile(List<T> data, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        File targetFile = new File(fileDirectory, fileName + ".txt");
        writeListToFile(data, targetFile);
    }

    public void writeListToFile(List<T> data, File targetFile) throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
        try {
            for(T element: data){
                bufferedWriter.append(element.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException ex) {
            // handle exception
        } finally { // закрытие ресурсов обязательно в finally
            // Оба вызова обязательно в отдельных try-catch
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                // log here
            }

            try {
                fileOutputStream.close();
            } catch (IOException ex) {
                // log here
            }
        }
    }

    public void writeListToFile(List<T> data, File targetFile, String comment) throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
        try {
            if(comment!=null){
                bufferedWriter.append(comment);
                bufferedWriter.newLine();
            }
            for(T element: data){
                bufferedWriter.append(element.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException ex) {
            // handle exception
        } finally { // закрытие ресурсов обязательно в finally
            // Оба вызова обязательно в отдельных try-catch
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                // log here
            }

            try {
                fileOutputStream.close();
            } catch (IOException ex) {
                // log here
            }
        }
    }

}
