package com.tihiy.reo;

import java.util.List;

public class ReoPostProcessor {

    private ReoPostProcessor() {
    }

    public static List<Double> getRadiusWithRo1(ExpMeasurement<Double> mainImpedance, ExpMeasurement<Double> roImpedance){
        List<Double> list;
        list = ((OneLayerModel)roImpedance.getModel()).getRoDelta(roImpedance.getData());

        MatrixFromRoRToZ matrix = new MatrixFromRoRToZ(0.04, 5, 0.0001, 0.001);
        matrix.fillMatrix(mainImpedance);

        List<Double> listOfRo = ((OneLayerModel)roImpedance.model).getRoDelta(roImpedance.data);
        for(int i = 0; i < listOfRo.size(); i++){
            list.add(matrix.getRad(listOfRo.get(i), mainImpedance.data.get(i)));
        }

        return list;
    }
}
