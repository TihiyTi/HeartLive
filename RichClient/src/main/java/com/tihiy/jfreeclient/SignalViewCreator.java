package com.tihiy.jfreeclient;

import com.tihiy.WindowUtils;

import javax.swing.*;
import java.awt.*;
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

    public static void createSignalView(List<List<Double>> listOfSignal, List<List<Double>> listOfArgs){
        JFrame frame = new JFrame("Signal");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,500));

        List<String> listOfNames = new ArrayList<>();
        int i = 0;
        for (List<Double> ignored : listOfSignal) {
            listOfNames.add(""+(++i));
        }

        frame.getContentPane().add(new SignalJFreePanel(listOfSignal, listOfArgs, listOfNames));
//        frame.setVisible(true);
//        frame.setSize(WindowUtils.getDimensionFromPercent(50, 70)); // определяет размеры окна
//        frame.setResizable(false);
        WindowUtils.centerOnScreenAndSetVisible(frame);
    }
}
