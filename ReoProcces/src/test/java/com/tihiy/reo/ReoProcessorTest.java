package com.tihiy.reo;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class ReoProcessorTest extends TestCase{
    public static void testRadiusCalc() throws IOException {

        List<Double> listData = ReadingFiles.readFile("Imp2.txt");
        List<Double> listRo = ReadingFiles.readFile("ImpRo.txt");

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
        List<Double> listRadius = processor.getRadiusWithRo1(mainImp, roImp);

    }

    private static void assertReadFileTest(List<Double> imp, List<Double> roImp){
        assertEquals(-2.4, roImp.get(1), 0.01);
        assertEquals(-64.4, roImp.get(200), 0.01);
        assertEquals(89.2, roImp.get(300), 0.01);
        assertEquals(-15.2, roImp.get(400), 0.01);

        // for Imp.txt
//        assertEquals(172.5, imp.get(1), 0.01);
//        assertEquals(149.5, imp.get(200), 0.01);
//        assertEquals(168.3, imp.get(300), 0.01);
//        assertEquals(5.111, imp.get(400), 0.01);
        // for Imp2.txt
        assertEquals(82.98, imp.get(1), 0.001);
        assertEquals(59.99, imp.get(200), 0.001);
        assertEquals(78.82, imp.get(300), 0.001);
        assertEquals(-84.39, imp.get(400), 0.001);
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
