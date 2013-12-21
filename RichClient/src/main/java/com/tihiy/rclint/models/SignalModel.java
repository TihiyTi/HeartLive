package com.tihiy.rclint.models;

import com.tihiy.rclint.mvcAbstract.AbstractModel;
import java.util.ArrayList;
import java.util.List;

// Model for simple signal with 1 channel
//
public class SignalModel extends AbstractModel {
    private String signalName;

    public SignalModel(){}
    public SignalModel(String signalName) {
        this.signalName = signalName;
    }

    public void setList(List<Double> list) {
        firePropertyChange(signalName, this.list, list);
        this.list = list;
    }

    public List<Double> getList() {
        return list;
    }

//    public void setSignalName(String name){
//        signalName = name;
//    }

    private List<Double> list = getTestList();
    private List<Double> getTestList(){
        List<Double> value = new ArrayList<>();
        for(int i = 0; i < 300; i++ ){
            value.add(Math.random() * 100);
        }
        return value;
    }
}
