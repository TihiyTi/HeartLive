package com.tihiy.fxclient.element;

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

    private DoubleProperty ro1BeginDouble = new SimpleDoubleProperty();
    private DoubleProperty ro1EndDouble = new SimpleDoubleProperty();
    private DoubleProperty rBeginDouble = new SimpleDoubleProperty();
    private DoubleProperty rEndDouble = new SimpleDoubleProperty();
    private DoubleProperty hBeginDouble = new SimpleDoubleProperty();
    private DoubleProperty hEndDouble = new SimpleDoubleProperty();
    private DoubleProperty yBeginDouble = new SimpleDoubleProperty();
    private DoubleProperty yEndDouble = new SimpleDoubleProperty();

    public TextField ro1begin;
    public TextField ro1end;
    public TextField rBegin;
    public TextField rEnd;
    public TextField hBegin;
    public TextField hEnd;
    public TextField yBegin;
    public TextField yEnd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.addAll(Arrays.asList(ro1BeginDouble, ro1EndDouble,
                rBeginDouble, rEndDouble, hBeginDouble, hEndDouble, yBeginDouble, yEndDouble));
        bindStringDouble(ro1begin, ro1BeginDouble);
        bindStringDouble(ro1end, ro1EndDouble);
        bindStringDouble(rBegin, rBeginDouble);
        bindStringDouble(rEnd, rEndDouble);
        bindStringDouble(hBegin, hBeginDouble);
        bindStringDouble(hEnd, hEndDouble);
        bindStringDouble(yBegin, yBeginDouble);
        bindStringDouble(yEnd, yEndDouble);
    }

    public void setDefaultValue(double ro1B, double ro1E, int rB, int rE, int hB, int hE, int yB, int yE){
        ro1BeginDouble.setValue(ro1B);
        ro1EndDouble.setValue(ro1E);
        rBeginDouble.setValue(rB);
        rEndDouble.setValue(rE);
        hBeginDouble.setValue(hB);
        hEndDouble.setValue(hE);
        yBeginDouble.setValue(yB);
        yEndDouble.setValue(yE);
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
