package com.tihiy.reo;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class ArticleTest extends TestCase {
    public void testArticle_1() throws IOException {
        List<Double> listData_1 = ReadingFiles.readFile("C:\\Users\\Alex\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716\\P1.txt");
        List<Double> listData_2 = ReadingFiles.readFile("C:\\Users\\Alex\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716\\P2.txt");
        List<Double> listData_3 = ReadingFiles.readFile("C:\\Users\\Alex\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716\\P3.txt");
        List<Double> listData_4 = ReadingFiles.readFile("C:\\Users\\Alex\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716\\P4.txt");
        List<Double> listData_5 = ReadingFiles.readFile("C:\\Users\\Alex\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716\\P5.txt");
        List<Double> listRo = ReadingFiles.readFile("C:\\Users\\Alex\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716\\Ro.txt");

        ElectrodeSystem eSystem_1 = new ElectrodeSystem(0.04, 0.02, 0, 0.037);
        ElectrodeSystem eSystem_2 = new ElectrodeSystem(0.04, 0.02, 0, 0.043);
        ElectrodeSystem eSystem_3 = new ElectrodeSystem(0.05, 0.025, 0, 0.042);
        ElectrodeSystem eSystem_4 = new ElectrodeSystem(0.05, 0.025, 0, 0.042);
        ElectrodeSystem eSystem_5 = new ElectrodeSystem(0.04, 0.02, 0, 0.037);
        BodyGeometry bodyGeometry_1 = new BodyGeometry(0.045, 0.019);
        BodyGeometry bodyGeometry_2 = new BodyGeometry(0.035, 0.019);
        BodyGeometry bodyGeometry_3 = new BodyGeometry(0.042, 0.019);
        BodyGeometry bodyGeometry_4 = new BodyGeometry(0.042, 0.015);
        BodyGeometry bodyGeometry_5 = new BodyGeometry(0.045, 0.019);

        SphereModel sphereModel = new SphereModel();
        sphereModel.setRo(5, 1.35);

        ElectrodeSystem roSystem = new ElectrodeSystem(0.06, 0.03, 0, 0);
        OneLayerModel oneLayerModel = new OneLayerModel();

        ExpMeasurement<Double> mainImp_1 =  new ExpMeasurement<>(sphereModel, eSystem_1, bodyGeometry_1, listData_1);
        ExpMeasurement<Double> mainImp_2 =  new ExpMeasurement<>(sphereModel, eSystem_2, bodyGeometry_2, listData_2);
        ExpMeasurement<Double> mainImp_3 =  new ExpMeasurement<>(sphereModel, eSystem_3, bodyGeometry_3, listData_3);
        ExpMeasurement<Double> mainImp_4 =  new ExpMeasurement<>(sphereModel, eSystem_4, bodyGeometry_4, listData_4);
        ExpMeasurement<Double> mainImp_5 =  new ExpMeasurement<>(sphereModel, eSystem_5, bodyGeometry_5, listData_5);
        ExpMeasurement<Double> roImp = new ExpMeasurement<>(oneLayerModel, roSystem, bodyGeometry_1, listRo);

        ReoPostProcessor processor = new ReoPostProcessor();
        processor.setUseFirstLayer(true);
        List<Double> listRadius_1 = processor.badGetRadiusWithRo1(mainImp_1, roImp, 0.045, 5);
        List<Double> listRadius_2 = processor.badGetRadiusWithRo1(mainImp_2, roImp, 0.035, 5);
        List<Double> listRadius_3 = processor.badGetRadiusWithRo1(mainImp_3, roImp, 0.042, 5);
        List<Double> listRadius_4 = processor.badGetRadiusWithRo1(mainImp_4, roImp, 0.042, 5);
        List<Double> listRadius_5 = processor.badGetRadiusWithRo1(mainImp_5, roImp, 0.045, 5);
        processor.setUseFirstLayer(false);
        List<Double> listRadius_n1 = processor.badGetRadiusWithRo1(mainImp_1, roImp, 0.045, 5);
        List<Double> listRadius_n2 = processor.badGetRadiusWithRo1(mainImp_2, roImp, 0.035, 5);
        List<Double> listRadius_n3 = processor.badGetRadiusWithRo1(mainImp_3, roImp, 0.042, 5);
        List<Double> listRadius_n4 = processor.badGetRadiusWithRo1(mainImp_4, roImp, 0.042, 5);
        List<Double> listRadius_n5 = processor.badGetRadiusWithRo1(mainImp_5, roImp, 0.045, 5);

        System.out.println("ListRadius_1");
        for(Double sample: listRadius_1){
            System.out.println(""+sample);
        }
        System.out.println("ListRadius_2");
        for(Double sample: listRadius_2){
            System.out.println(""+sample);
        }
        System.out.println("ListRadius_3");
        for(Double sample: listRadius_3){
            System.out.println(""+sample);
        }
        System.out.println("ListRadius_4");
        for(Double sample: listRadius_4){
            System.out.println(""+sample);
        }
        System.out.println("ListRadius_5");
        for(Double sample: listRadius_5){
            System.out.println(""+sample);
        }
        System.out.println("ListRadius_n1");
        for(Double sample: listRadius_n1){
            System.out.println(""+sample);
        }
        System.out.println("ListRadius_n2");
        for(Double sample: listRadius_n2){
            System.out.println(""+sample);
        }
        System.out.println("ListRadius_n3");
        for(Double sample: listRadius_n3){
            System.out.println(""+sample);
        }
        System.out.println("ListRadius_n4");
        for(Double sample: listRadius_n4){
            System.out.println(""+sample);
        }
        System.out.println("ListRadius_n5");
        for(Double sample: listRadius_n5){
            System.out.println(""+sample);
        }
    }
}
