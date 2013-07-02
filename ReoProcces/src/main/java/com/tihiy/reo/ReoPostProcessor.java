package com.tihiy.reo;

import java.util.List;

public class ReoPostProcessor {

    private ReoPostProcessor() {
    }

    public static List<Double> getRadiusWithRo1(ExpMeasurement<Double> mainImpedance, ExpMeasurement<Double> roImpedance){
        List<Double> list;
        list = ((OneLayerModel)roImpedance.getModel()).getRoDelta(roImpedance.getData());

        MatrixFromRoRToZ matrix = new MatrixFromRoRToZ(0.044, 5, 0.0001, 0.001);
        matrix.fillMatrix(mainImpedance);

        List<Double> listOfRo = ((OneLayerModel)roImpedance.model).getRoDelta(roImpedance.data);
        for(int i = 0; i < listOfRo.size(); i++){
            double dRadius = matrix.getRad(listOfRo.get(i), mainImpedance.data.get(i)/1000);
            list.add(dRadius);
            System.out.println(""+i+"  "+dRadius);
        }

        return list;
    }
}
