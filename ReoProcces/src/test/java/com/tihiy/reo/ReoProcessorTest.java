package com.tihiy.reo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReoProcessorTest {
    @Test
    public void radiusCalc(){

        ElectrodeSystem eSystem = new ElectrodeSystem(0.06, 0.03, 0, 0.01);
        BodyGeometry bodyGeometry = new BodyGeometry(0.050, 0.020);
        SphereModel sphereModel = new SphereModel();
        sphereModel.setRo(5, 1.5);

        ElectrodeSystem roSystem = new ElectrodeSystem(0.06, 0.03, 0, 0.01);
        OneLayerModel oneLayerModel = new OneLayerModel();

        ExpMeasurement<Double> mainImp =  new ExpMeasurement<Double>(sphereModel, eSystem, bodyGeometry, getListData());
        ExpMeasurement<Double> roImp = new ExpMeasurement<Double>(oneLayerModel, eSystem, bodyGeometry, getListRo());
        List<Double> listRadius = ReoPostProcessor.getRadiusWithRo1(mainImp, roImp);

    }

    private static List<Double> getListData(){
        List<Double> list  =  new ArrayList<>();
        for(int i = 0; i < 100; i++){
            list.add((double) i);
        }
        return list;
    }

    private static List<Double> getListRo(){
        List<Double> list  =  new ArrayList<>();
        for(int i = 0; i < 100; i++){
            list.add((double) i);
        }
        return list;
    }
}
