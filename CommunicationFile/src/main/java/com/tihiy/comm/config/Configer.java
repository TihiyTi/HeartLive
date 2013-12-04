package com.tihiy.comm.config;

import com.tihiy.comm.ListWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Home
 * Date: 09.10.13
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
public class Configer {
    public void saveConfig(Map<String, String> configuration, File file){
        List<String> listOfString = new ArrayList<>();
        Set<String> setOfParameters = configuration.keySet();
        for(String element: setOfParameters){
            listOfString.add(element + " = " + configuration.get(element));
        }
        ListWriter<String> writer = new ListWriter<String>();
        try {
            writer.writeListToFile(listOfString, file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig(File configFile){

    }
}
