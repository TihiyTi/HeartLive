package com.tihiy.fxclient;

import java.util.ArrayList;
import java.util.List;

public class Derivative {
    public static List<Double> derivativeSimple(List<Double> list, double period){
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < list.size() - 2; i++) {
            result.add((list.get(i+2)-list.get(i))/period);
        }
        return result;
    }
}
