package com.tihiy.rclint.models;

import com.tihiy.rclint.mvcAbstract.AbstractModel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Model for simple signal with 1 channel
//
public class SignalModel extends AbstractModel {
    public List<Double> getList() {
        return list;
    }

    private List<Double> list = getTestList();

    public void setList(List<Double> list) {
        firePropertyChange("list", this.list, list);
        this.list = list;
    }

    private List<Double> getTestList(){
        List<Double> value = new ArrayList<Double>();
        for(int i = 0; i < 300; i++ ){
            value.add((double) Math.random() * 100);
        }
        return value;
    }

    public void randomList(){
        List<Double> oldList = this.list;
        list = getTestList();
        firePropertyChange("list", oldList, list);
    }

    @Override
    protected void applyCommand(String command) {
        try {

            Method method = this.getClass().
                    getMethod(command);
            method.invoke(this);

        } catch (Exception ex) {
            //  Handle exception
        }
    }

    // todo method getSignalLink and getSignalValue
}
