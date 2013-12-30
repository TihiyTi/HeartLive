package com.tihiy.rclint.models;

import com.tihiy.rclint.mvcAbstract.AbstractModel;

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
        firePropertyChange(signalName, new double[0], newArray);
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
                setArray(array);
            }
        },0L, 40L, TimeUnit.MILLISECONDS);
    }
}
