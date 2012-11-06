package com.tihiy.reo;

import junit.framework.TestCase;
//import org.junit.Test;

public class ReoModelTest extends TestCase {
    public void testMainTest(){
        ElectrodeSystem e = new ElectrodeSystem(0.060,0.030, 0, 0);
        InterfaceModel reoModel = new ReoModel(0.040, 0.020, 1.35, 7.00);
//        Measurement measure = new Measurement(reoModel, e);
        double value;
        value = reoModel.getPotentialInPoint(new ReoPoint(0, 0.03), e);
        assertEquals(-1.646, value, 0.001);
        value = reoModel.getPotentialInPoint(new ReoPoint(0, 0.04), e);
        assertEquals(-1.479, value,  0.001);
        value = reoModel.getPotentialInPoint(new ReoPoint(0, -0.02), e);
        assertEquals(-0.485, value,  0.001);
        value = reoModel.getPotentialInPoint(new ReoPoint(0, 0.060), e);
        assertEquals(-1.0487, value,  0.001);
        double potemtialMap[] = new double[100];
        for(int i = 0; i < 100; i++){
//            for(int j = 0; j < 100; i++){
                potemtialMap[i] = reoModel.getPotentialInPoint(new ReoPoint(0, 0.06 + (0.001*i - 0.05)),e);
//            }
            System.out.printf("%.3f", potemtialMap[i]);
            System.out.println(" ");
        }
    }
}
