package com.tihiy.reo;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class ReoProcessorTest extends TestCase{
    public void testRadiusCalc() throws IOException {

        List<Double> listData = ReadingFiles.readFile(getClass().getResource("Imp2.txt").getFile());
        List<Double> listRo = ReadingFiles.readFile(getClass().getResource("ImpRo.txt").getFile());

        assertReadFileTest(listData, listRo);

        ElectrodeSystem eSystem = new ElectrodeSystem(0.06, 0.03, 0, 0);
        BodyGeometry bodyGeometry = new BodyGeometry(0.04, 0.020);
        SphereModel sphereModel = new SphereModel();
        sphereModel.setRo(5, 1.35);

        ElectrodeSystem roSystem = new ElectrodeSystem(0.06, 0.03, 0, 0);
        OneLayerModel oneLayerModel = new OneLayerModel();

        ExpMeasurement<Double> mainImp =  new ExpMeasurement<>(sphereModel, eSystem, bodyGeometry, listData);
        ExpMeasurement<Double> roImp = new ExpMeasurement<>(oneLayerModel, roSystem, bodyGeometry, listRo);

        List<Double> listOfRo = ((OneLayerModel)roImp.getModel()).getRoDelta(roImp.getData());
        assertCalcRo(listOfRo);

        ReoPostProcessor processor = new ReoPostProcessor();
        processor.setUseBaseImpedance(false);
        List<Double> listRadius = processor.getRadiusWithRo1(mainImp, roImp);
        assertRadius(listRadius);
    }

//    public static void testExpCelc() throws  IOException {
//
//        List<Double> listData = ReadingFiles.readFile("C:\\Users\\Home\\Documents\\My Box Files\\Asp\\RoChange\\Exp08_Prcg.txt");
//        List<Double> listRo = ReadingFiles.readFile("C:\\Users\\Home\\Documents\\My Box Files\\Asp\\RoChange\\Exp08_First.txt");
//
//        ElectrodeSystem eSystem = new ElectrodeSystem(0.08, 0.04, 0, 0);
//        BodyGeometry bodyGeometry = new BodyGeometry(0.04, 0.020);
//
//        SphereModel sphereModel = new SphereModel();
//        sphereModel.setRo(5, 1.35);
//
//        ElectrodeSystem roSystem = new ElectrodeSystem(0.06, 0.03, 0, 0);
//        OneLayerModel oneLayerModel = new OneLayerModel();
//
//        ExpMeasurement<Double> mainImp =  new ExpMeasurement<>(sphereModel, eSystem, bodyGeometry, listData);
//        ExpMeasurement<Double> roImp = new ExpMeasurement<>(oneLayerModel, roSystem, bodyGeometry, listRo);
//
//        List<Double> listOfRo = ((OneLayerModel)roImp.getModel()).getRoDelta(roImp.getData());
//
//        ReoPostProcessor processor = new ReoPostProcessor();
//        processor.setUseFirstLayer(true);
//        List<Double> listRadius = processor.getRadiusWithRo1(mainImp, roImp);
//        for(Double sample: listRadius){
//            System.out.println(""+sample);
//        }
//    }

    private static void assertReadFileTest(List<Double> imp, List<Double> roImp){
        assertEquals(-2.4, roImp.get(1), 0.01);
        assertEquals(-64.4, roImp.get(200), 0.01);
        assertEquals(89.2, roImp.get(300), 0.01);
        assertEquals(-15.2, roImp.get(400), 0.01);

        assertEquals(347.4, imp.get(1), 0.001);
        assertEquals(363.4, imp.get(200), 0.001);
        assertEquals(247.5, imp.get(300), 0.001);
        assertEquals(25.42, imp.get(400), 0.001);
    }
    private static void assertCalcRo(List<Double> listOfRo){
        assertEquals(0.000339, listOfRo.get(1), 0.000001);
        assertEquals(0.009104, listOfRo.get(200), 0.000001);
        assertEquals(-0.012610, listOfRo.get(300), 0.000001);
        assertEquals(0.002148, listOfRo.get(400), 0.000001);
    }
    private static void assertRadius(List<Double> list){
        assertEquals(1.5, list.get(1), 0.001);
        assertEquals(2.2, list.get(200), 0.001);
        assertEquals(-0.1, list.get(300), 0.001);
        assertEquals(-1.3, list.get(400), 0.001);
    }
}
