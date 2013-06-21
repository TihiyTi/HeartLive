package com.tihiy.reo;

import java.util.ArrayList;
import java.util.List;

public class ReoPostProcessor {

    public static List<Double> getRadiusWithRo1(ExpMeasurement<Double> mainImpedance, ExpMeasurement<Double> roImpedance){
        List<Double> list;
        list = ((OneLayerModel)roImpedance.getModel()).getRoDelta(roImpedance.getData());

        MatrixFromRoRToZ matrix = new MatrixFromRoRToZ(0.04, 5, 0.0001, 0.001);

        return list;
    }
}
