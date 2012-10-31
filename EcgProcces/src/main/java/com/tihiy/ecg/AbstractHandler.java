package com.tihiy.ecg;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHandler {
    protected List<Float> startData;
    protected List<Float> finishData = new ArrayList<Float>();

    protected AbstractHandler() {
    }

    public List<Float> getData(){
        return finishData;
    }

    public void setData(List<Float> data){
        startData = data;
    }

    public void toDoOperation(){}
}
