package com.tihiy.fxclient;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ParamController implements Initializable{
    private List<DoubleProperty> list = new ArrayList<>();
    private DoubleProperty aDouble = new SimpleDoubleProperty();
    private DoubleProperty bDouble = new SimpleDoubleProperty();
    private DoubleProperty ro1Double = new SimpleDoubleProperty();
    private DoubleProperty ro2Double = new SimpleDoubleProperty();
    private DoubleProperty rDouble = new SimpleDoubleProperty();
    private DoubleProperty hDouble = new SimpleDoubleProperty();

    public TextField a;
    public TextField b;
    public TextField ro1;
    public TextField ro2;
    public TextField r;
    public TextField h;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.addAll(Arrays.asList(aDouble, bDouble, ro1Double, ro2Double));
        bindStringDouble(a, aDouble);
        bindStringDouble(b, bDouble);
        bindStringDouble(ro1, ro1Double);
        bindStringDouble(ro2, ro2Double);
        bindStringDouble(r, rDouble);
        bindStringDouble(h, hDouble);
    }

    public List<DoubleProperty> getListOfProperties(){
        return list;
    }

    private void bindStringDouble(TextField string, DoubleProperty ddouble){
        string.textProperty().bindBidirectional(ddouble, new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.valueOf(object.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                return Double.valueOf(string);
            }
        });
    }
}
