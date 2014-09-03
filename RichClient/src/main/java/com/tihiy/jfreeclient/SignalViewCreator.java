package com.tihiy.jfreeclient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignalViewCreator {
    @SafeVarargs
    public static void createSignalView(String[] signalName, List<Double>... l){
        JFrame frame = new JFrame("Short Signal");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<List<Double>> list = Arrays.asList(l);
        List<String> listOfNames = Arrays.asList(signalName);

        frame.getContentPane().add(new SignalJFreePanel(list, listOfNames));
        frame.setVisible(true);
    }
    @SafeVarargs
    public static void createSignalView(List<Double>... l){
        JFrame frame = new JFrame("Short Signal");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        List<List<Double>> list = Arrays.asList(l);
        List<String> listOfNames = new ArrayList<>();
        int i = 0;
        for (List<Double> ignored : list) {
            listOfNames.add(""+(++i));
        }

        frame.getContentPane().add(new SignalJFreePanel(list, listOfNames));
        frame.setVisible(true);
    }

}
