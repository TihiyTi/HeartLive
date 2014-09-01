package utils;

import com.tihiy.jfreeclient.SignalJFreePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static settings.SistolaInterval.*;

public class ShortSignalCreator {

    @SafeVarargs
    public static void createAndViewShortSignal(String signalName, List<Double>... l){
        for (List<Double> list : l) {
            JFrame frame = new JFrame(signalName);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(getSignalPanel(list));
            frame.setVisible(true);
        }
    }
    @SafeVarargs
    public static void createAndViewShortMultiSignal(String[] signalName, List<Double>... l){
        JFrame frame = new JFrame("Short Signal");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();
        for(int i = 0; i < l.length; i++){
            tabbedPane.addTab(signalName[i], getSignalPanel(l[i]));
        }
        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);
    }

    @SafeVarargs
    public static void createAndViewShortSignal(List<Double>... l){
        createAndViewShortSignal("",l);
    }

    private static SignalJFreePanel getSignalPanel(List<Double> list){
        List<List<Double>> miniLists = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for(int i = 1; i <= getNumOfInterval(); i++){
            miniLists.add(list.subList(getPoint(i, BEGIN), getPoint(i, END)));
            names.add(""+i);
        }
        return new SignalJFreePanel(miniLists, names);
    }
}
