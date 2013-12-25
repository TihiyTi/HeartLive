package com.tihiy.rclint.models;

import com.tihiy.rclint.mvcAbstract.AbstractModel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SignalModelLite extends AbstractModel{
    private double[] signalArray;
    private String signalName;

    public SignalModelLite(String signalName){
        this.signalName = signalName;
        autoFillingArray();
    }

    public void setList(double[] array){
        firePropertyChange(signalName, new double[0], array);
        signalArray = array;
    }

    public double[] getList(){
        return signalArray;
    }

    public void autoFillingArray(){
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                double[] array = new double[1000];
                for(int i = 0; i< 1000; i++){
                    array[i] = Math.random()*400;
                }
                setList(array);
            }
        },0L, 40L, TimeUnit.MILLISECONDS);
    }
}
