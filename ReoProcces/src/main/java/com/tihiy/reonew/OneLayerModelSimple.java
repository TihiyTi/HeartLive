package com.tihiy.reonew;

import java.util.ArrayList;
import java.util.List;

public class OneLayerModelSimple {
    private double a;
    private double b;

    public static boolean MILLI_OMH = true;
    public static boolean OMH = false;


    public OneLayerModelSimple(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public List<Double> getDeltaRoList(List<Double> impedance){
        return getDeltaRoList(impedance, MILLI_OMH);
    }
    public List<Double> getDeltaRoList(List<Double> impedance, boolean isMilliOmh){
        List<Double> list =  new ArrayList<>();
        for(Double d: impedance){
            double value = d*Math.PI/(2*b)*(a*a - b*b);
            if(isMilliOmh){
                list.add(-value/1000);
            }else{
                list.add(-value);
            }
        }
        return list;
    }
    public double getDeltaRo(double impedance){
        double value = impedance*Math.PI/(2*b)*(a*a - b*b);
        return -value/1000;
    }
}
