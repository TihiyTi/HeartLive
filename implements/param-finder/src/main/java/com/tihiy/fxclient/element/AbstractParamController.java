package com.tihiy.fxclient.element;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParamController {
    protected List<DoubleProperty> listOfProp = new ArrayList<>();
    protected List<TextField> listOfField = new ArrayList<>();


    public abstract void setDefaultValue(double... n);

    protected void bindListsTextFieldDoubleProperty(List<TextField> fields, List<DoubleProperty> properties){
        for (int i = 0; i < fields.size(); i++) {
            bindStringDouble(fields.get(i), properties.get(i));
        }
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
