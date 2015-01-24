package com.tihiy.fxclient.element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabParamModel implements TabParamModelInterface{
    public static int RO1B = 0;
    public static int RO1E = 1;
    public static int RB = 2;
    public static int RE = 3;
    public static int HB = 4;
    public static int HE = 5;
    public static int YB = 6;
    public static int YE = 7;
    private List<List<DoubleProperty>> lists =  new ArrayList<>();

    public TabParamModel(){
        packProperties();
    }

    @Override
    public List<List<DoubleProperty>> getAllProperties() {
        return lists;
    }

    private void packProperties(){
        for(int i = 0; i < 5; i++){
            List<DoubleProperty> list = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                list.add(new SimpleDoubleProperty());
            }
            lists.add(list);
        }
    }

    public Double get(int channel, int param) {
        return lists.get(channel).get(param).getValue();
    }
}
