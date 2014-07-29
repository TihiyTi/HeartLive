package com.tihiy.rclint.models;

import com.tihiy.rclint.mvcAbstract.AddOnModelInterface;
import com.tihiy.rclint.mvcAbstract.AbstractModel;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SignalModelLite extends AbstractModel{
    protected double[] signalArray;
    protected String signalName;

    public SignalModelLite(String signalName){
        this.signalName = signalName;
    }

    public void setArray(double[] array){
        signalArray = array;
        double[] newArray = new double[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        int i = 0;
        if(addOns!=null){
            for(AddOnModelInterface addOn: addOns){
                addOn.prepareState(signalArray);
                fireIndexedPropertyChange(signalName, i, new Object(), addOn.getState());
                i++;
            }
        }
        firePropertyChange(signalName, new double[0], newArray);
    }
    public void setArray(List<? extends Number> list){
        double[] newArray = new double[list.size()];
        for(int i = 0; i < list.size(); i++){
            newArray[i] = list.get(i).doubleValue();
        }
        setArray(newArray);
    }

    public double[] getList(){
        return signalArray;
    }

    public void autoFillingArray(){
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                double[] array = new double[1000];
                double phase = 2*Math.PI*Math.random();
                for(int i = 0; i< 1000; i++){
                    array[i] = 100*(Math.sin(2*Math.PI*i/200 + phase)+1);
                }
                setArray(array);
            }
        },0L, 50L, TimeUnit.MILLISECONDS);
    }
}
