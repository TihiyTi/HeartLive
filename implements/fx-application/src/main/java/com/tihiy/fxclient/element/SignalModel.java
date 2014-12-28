package com.tihiy.fxclient.element;

import com.tihiy.fxclient.element.interfaces.SignalModelInterface;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
public class SignalModel implements SignalModelInterface {
    StringProperty signalName = new SimpleStringProperty();
    ListProperty<Double> list = new SimpleListProperty<>();
    IntegerProperty sampleFreq = new SimpleIntegerProperty();

    public void setList(List<Double> list) {
        ObservableList<Double> observableList = FXCollections.observableList(list);
        this.list.setValue(observableList);
    }

    public void setSignalName(String signalName) {
        this.signalName.set(signalName);
    }

    public void setSampleFreq(int sampleFreq) {
        this.sampleFreq.set(sampleFreq);
    }

    @Override
    public ListProperty<Double> getSignal() {
        return list;
    }

    @Override
    public StringProperty getName() {
        return signalName;
    }

    @Override
    public IntegerProperty getSampleFreq() {
        return sampleFreq;
    }
}
