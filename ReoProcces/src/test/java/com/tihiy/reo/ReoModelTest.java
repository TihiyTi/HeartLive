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
    }

//    public void testLegandr(){
//        ReoModel reoModel = new ReoModel(0.056, 0.048, 1.35, 7);
//        assertEquals(1, reoModel.legandr(0, 0.5), 0.0001);
//        assertEquals(-0.125, reoModel.legandr(2,0.5), 0.0001);
//        assertEquals(-0.0605, reoModel.legandr(100,0.5), 0.0001);
//    }
}
