package com.tihiy.reonew;

import junit.framework.TestCase;

public class SphereCalcTest extends TestCase{

    public void testGetMeasurement() throws Exception {
        SphereModelParam param = new SphereModelParam(5,1.35, 0.04,0.02, 0.045,0.02, 0,0);
        SphereModelSimple model = new SphereModelSimple(param);
        assertEquals(-4.708, model.getImpedance(), 0.001);
        assertEquals(-4.388, model.setAB(0.05, 0.025).getImpedance(), 0.001);

        ReoProcessor proc = new ReoProcessor(model);
        assertEquals(-0.010, proc.getDeltaRadius(1.124), 0.0001);
        assertEquals(-0.005, proc.getDeltaRadius(0.542), 0.0001);

        assertEquals(-0.01, proc.getDeltaRadius(1.004, 0.1), 0.0001);
        assertEquals(-0.01, proc.getDeltaRadius(0.883, 0.2), 0.0001);

        System.out.println(proc.getDeltaRadius(0.0, 0.2));
        System.out.println(proc.getDeltaRadius(0.02, 0.2));
        System.out.println(proc.getDeltaRadius(0.3, 0.2));

    }
}