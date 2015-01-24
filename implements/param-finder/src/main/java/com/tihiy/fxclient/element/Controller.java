package com.tihiy.fxclient.element;

import com.tihiy.fxclient.MinSKOFinder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

import java.io.PrintStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public TabParamModel  model;
    public TextArea area;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void calculate(ActionEvent actionEvent) {
        List<Double> expList_1 = Arrays.asList(32.4, 33.5, 34.7, 36.2, 36.2, 36., 35.5);
        List<Double> expList_2 = Arrays.asList(28., 30., 31.3, 33., 34.6, 35.3, 35., 34.6, 34.9, 33.7, 36., 36.6);
        List<Double> expList_3 = Arrays.asList(32.3, 30.8, 31.4, 31.3, 32.4, 31.8, 33.7, 34.6, 35.5, 34.8, 35.3, 34.7, 35., 35.5, 34.3);
        List<Double> expList_4 = Arrays.asList(32.4, 30.7, 32.5, 31.6, 31.9, 33.4, 35.4, 36.9, 37.6, 38.6, 41., 43.2);
        List<Double> expList_5 = Arrays.asList(37., 39.5, 41.3, 43., 45., 45., 45.3, 46., 44.7, 42.8, 42.3, 41.7);
        String s = " Channel 1 \r\n";
        s = s.concat(channelCalc(expList_1, 0));
        s = s.concat("\n Channel 2 \r\n");
        s = s.concat(channelCalc(expList_2, 1));
        s = s.concat("\n Channel 3 \r\n");
        s = s.concat(channelCalc(expList_3, 2));
        s = s.concat("\n Channel 4 \r\n");
        s = s.concat(channelCalc(expList_4, 3));
        s = s.concat("\n Channel 5 \r\n");
        s = s.concat(channelCalc(expList_5, 4));
        area.textProperty().setValue(s);
    }

    public void setModel(TabParamModel model) {
        this.model = model;
    }

    private String channelCalc(List<Double> list, int channel){
        MinSKOFinder finder = new MinSKOFinder();
        finder.setHInterval(model.get(channel, TabParamModel.HB).intValue(), model.get(channel, TabParamModel.HE).intValue());
        finder.setRInterval(model.get(channel, TabParamModel.RB).intValue(), model.get(channel, TabParamModel.RE).intValue());
        finder.setYInterval(model.get(channel, TabParamModel.YB).intValue(), model.get(channel, TabParamModel.YE).intValue());
        finder.setRoInterval(model.get(channel, TabParamModel.RO1B).intValue(), model.get(channel, TabParamModel.RO1E).intValue());
        finder.getParamWithMinSKO(list);
        finder.getSignalInFrame("Channel "+ (channel+1), list, finder.minModelList);
        return finder.returnResult();
    }
}
