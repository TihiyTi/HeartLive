package com.tihiy.fxclient.element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ParamController2 extends AbstractParamController implements Initializable{
    public static int RO1 = 0;
    public static int R = 1;
    public static int H = 2;
    public static int Y = 3;

    public TextField ro1;
    public TextField r;
    public TextField h;
    public TextField y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listOfField.addAll(Arrays.asList(ro1, r, h, y));
        listOfField.forEach(e -> listOfProp.add(new SimpleDoubleProperty()));
        bindListsTextFieldDoubleProperty(listOfField,listOfProp);
    }

    @Override
    public void setDefaultValue(double... n) {
        for (int i = 0; i < listOfProp.size(); i++) {
            listOfProp.get(i).setValue(n[i]);
        }
    }

    public Double get(int param){
        return listOfProp.get(param).getValue();
    }

}
