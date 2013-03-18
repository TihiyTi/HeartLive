package com.tihiy.rclint.models;

import com.tihiy.rclint.mvcAbstract.AbstractModel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 14.03.13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
public class SignalModel extends AbstractModel {
    public List<Float> getList() {
        return list;
    }

    private List<Float> list = getTestList();

    public void setList(List<Float> list) {
        firePropertyChange("list", this.list, list);
        this.list = list;
    }

    private List<Float> getTestList(){
        List<Float> value = new ArrayList<>();
        for(int i = 0; i < 300; i++ ){
            value.add((float) Math.random() * 100);
        }
        return value;
    }

    public void randomList(){
        List<Float> oldList = this.list;
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
