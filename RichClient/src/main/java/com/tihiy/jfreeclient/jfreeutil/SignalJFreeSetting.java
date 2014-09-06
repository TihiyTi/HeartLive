package com.tihiy.jfreeclient.jfreeutil;

import java.util.List;

public class SignalJFreeSetting {
    private List<Double> signalData;
    private List<Double> signalArgs;

    private String name;

    public SignalJFreeSetting() {

    }

    public SignalJFreeSetting(List<Double> list, List<Double> args){
        signalData = list;
        signalArgs = args;
    }

    public List<Double> getSignalData() {
        return signalData;
    }

    public List<Double> getSignalArgs() {
        return signalArgs;
    }

    public String getName() {
        return name;
    }

    public void setSignalData(List<Double> signalData) {
        this.signalData = signalData;
    }

    public void setSignalArgs(List<Double> signalArgs) {
        this.signalArgs = signalArgs;
    }

    public void setName(String name) {
        this.name = name;
    }

}
