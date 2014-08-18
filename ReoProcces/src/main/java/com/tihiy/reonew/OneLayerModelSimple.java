package com.tihiy.reonew;

import java.util.ArrayList;
import java.util.List;

public class OneLayerModelSimple {
    private double a;
    private double b;

    public OneLayerModelSimple(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public List<Double> getDeltaRoList(List<Double> impedance){
        List<Double> list =  new ArrayList<>();
        for(Double d: impedance){
            double value = d*Math.PI/(2*b)*(a*a - b*b);
            list.add(-value/1000);
        }
        return list;
    }
    public double getDeltaRo(double impedance){
        double value = impedance*Math.PI/(2*b)*(a*a - b*b);
        return -value/1000;
    }
}
