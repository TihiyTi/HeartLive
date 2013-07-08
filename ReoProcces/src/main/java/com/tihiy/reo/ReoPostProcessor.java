package com.tihiy.reo;

import java.util.ArrayList;
import java.util.List;

public class ReoPostProcessor {

    private MatrixFromRoRToZ matrix;

    public ReoPostProcessor() {
    }

    public List<Double> getRadiusWithRo1(ExpMeasurement<Double> mainImpedance, ExpMeasurement<Double> roImpedance){
        List<Double> list;
        list = ((OneLayerModel)roImpedance.getModel()).getRoDelta(roImpedance.getData());

        matrix = new MatrixFromRoRToZ(0.04, 5, 0.0001, 0.001);
        matrix.fillMatrix(mainImpedance);

//        double impedance = matrix.impZmatrix(0.000339, 0.0032, mainImpedance);
//        double drad = matrix.getRad(0.000339, impedance);
//        System.out.println("Imp = " + impedance);
//        System.out.println("0.0032 = " + drad);
//        impedance = matrix.impZmatrix(0.009274, 0.00324, mainImpedance);
//        drad = matrix.getRad(0.009274, impedance);
//        System.out.println("Imp = " + impedance);
//        System.out.println("0.00324 = " + drad);
//        impedance = matrix.impZmatrix(0.009104, 0.00382, mainImpedance);
//        drad = matrix.getRad(0.009104, impedance);
//        System.out.println("Imp = " + impedance);
//        System.out.println("0.00382 = " + drad);



        List<Double> listOfRo = ((OneLayerModel)roImpedance.model).getRoDelta(roImpedance.data);
        List<Double> listOfdRad = new ArrayList<>();
        for(int i = 0; i < listOfRo.size(); i++){
            double dRadius = matrix.getRad(listOfRo.get(i), -mainImpedance.data.get(i)/1000);
            listOfdRad.add(dRadius);
//            System.out.println(""+i+" Ro= "+listOfRo.get(i)+ " Imp = "+mainImpedance.data.get(i)/1000+ " rad "+dRadius);
            System.out.println(""+i+"  "+dRadius);
        }

        return listOfdRad;
    }
}
