package com.tihiy.ecg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 28.09.12
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractHandler {
    protected List<Float> startData;
    protected List<Float> finishData = new ArrayList<Float>();

    public List<Float> getData(){
        return finishData;
    }

    public void setData(List<Float> data){
        startData = data;
    }

    public void toDoOperation(){}
}
