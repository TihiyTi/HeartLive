package com.tihiy.reo;

import junit.framework.TestCase;

public class SphereModelTest extends TestCase {
    public void testSphereModel(){
        ElectrodeSystem eSystem = new ElectrodeSystem(0.06, 0.03, 0, 0);
        BodyGeometry bodyGeometry = new BodyGeometry(0.040, 0.020);
        SphereModel model = new SphereModel();
        Measurement measurement = new Measurement(model , eSystem, bodyGeometry);
        double[] value = getPotentials(model, eSystem);
        double[] expValue = {-1.646, -0.23, -0.23, -1.646};
        for(int i = 0; i < 4; i++){
            assertEquals(expValue[i], value[i], 0.001);
        }

        eSystem.setPosition(0, 0.01);
        expValue = new double[]{-2.212, -0.315, -0.184, -1.18};
        value = getPotentials(model, eSystem);
        for(int i = 0; i < 4; i++){
            assertEquals(expValue[i], value[i], 0.001);
        }

        eSystem.setPosition(0, -0.01);
        expValue = new double[]{-1.18, -0.184, -0.315, -2.212};
        value = getPotentials(model, eSystem);
        for(int i = 0; i < 4; i++){
            assertEquals(expValue[i], value[i], 0.001);
        }

        eSystem.setPosition(0.01, 0);
        expValue = new double[]{-1.58, -0.243, -0.243, -1.58};
        value = getPotentials(model, eSystem);
        for(int i = 0; i < 4; i++){
            assertEquals(expValue[i], value[i], 0.001);
        }

        eSystem.setPosition(-0.01, 0);
        expValue = new double[]{-1.58, -0.243, -0.243, -1.58};
        value = getPotentials(model, eSystem);
        for(int i = 0; i < 4; i++){
            assertEquals(expValue[i], value[i], 0.001);
        }
    }

    public void testMovingESystem(){
        ElectrodeSystem eSystem = new ElectrodeSystem(0.06, 0.03, 0, 0.01);
        BodyGeometry bodyGeometry = new BodyGeometry(0.050, 0.020);
        SphereModel model = new SphereModel();
        model.setRo(5, 1.5);
        InterfaceMeasurement measurement = new Measurement(model , eSystem, bodyGeometry);
        double value = measurement.getMeasurement();
        assertEquals(-2.058, value, 0.001);
        eSystem.setPosition(0, 0);
        value = measurement.getMeasurement();
        assertEquals(-2.032, value, 0.001);
        eSystem.setPosition(0, -0.01);
        value = measurement.getMeasurement();
        assertEquals(-2.058, value, 0.001);

        eSystem.setPosition(0.02, 0.01);
        value = measurement.getMeasurement();
        assertEquals(-1.7, value, 0.001);
        eSystem.setPosition(0.02, 0);
        value = measurement.getMeasurement();
        assertEquals(-1.681, value, 0.001);
        eSystem.setPosition(0.02, -0.01);
        value = measurement.getMeasurement();
        assertEquals(-1.7, value, 0.001);

        eSystem.setPosition(-0.02, 0.01);
        value = measurement.getMeasurement();
        assertEquals(-1.7, value, 0.001);
        eSystem.setPosition(-0.02, 0);
        value = measurement.getMeasurement();
        assertEquals(-1.681, value, 0.001);
        eSystem.setPosition(-0.02, -0.01);
        value = measurement.getMeasurement();
        assertEquals(-1.7, value, 0.001);
    }

    public void test3dNoMove(){
        ElectrodeSystem eSysytem = new ElectrodeSystem(0.06, 0, 0, 0);
        BodyGeometry bodyGeometry = new BodyGeometry(0.04, 0.02);
        SphereModel model = new SphereModel();
        model.setRo(7, 1.35);
        Measurement measurement = new Measurement(model, eSysytem, bodyGeometry);
        ReoPoint electrode = eSysytem.getIElectrode(ElectrodeSystem.El.MINUS);
        double value = model.potentialInPointFromElectrode(new ReoPoint(0, 0, 0), electrode );
        assertEquals(-1.208, value, 0.001);
        value = model.potentialInPointFromElectrode(new ReoPoint(0, -0.02, 0), electrode );
        assertEquals(-1.681, value, 0.001);
        value = model.potentialInPointFromElectrode(new ReoPoint(0, -0.02, 0.01), electrode );
        assertEquals(-2.585, value, 0.001);
        value = model.potentialInPointFromElectrode(new ReoPoint(0.01, -0.02, 0), electrode );
        assertEquals(-1.596, value, 0.001);
        value = model.potentialInPointFromElectrode(new ReoPoint(-0.01, -0.02, 0), electrode );
        assertEquals(-1.596, value, 0.001);
        value = model.potentialInPointFromElectrode(new ReoPoint(0.01, 0, 0), electrode );
        assertEquals(-1.147, value, 0.001);
        value = model.potentialInPointFromElectrode(new ReoPoint(0, 0.02, 0), electrode );
        assertEquals(-0.485, value, 0.001);
    }

    public void test3dWithMove(){
        ElectrodeSystem eSysytem = new ElectrodeSystem(0.06, 0, 0, 0);
        BodyGeometry bodyGeometry = new BodyGeometry(0.04, 0.02);
        SphereModel model = new SphereModel();
        model.setRo(7, 1.35);
        Measurement measurement = new Measurement(model, eSysytem, bodyGeometry);

        eSysytem.setPosition(0, 0.01);
        ReoPoint electrode = eSysytem.getIElectrode(ElectrodeSystem.El.MINUS);
        double value = model.potentialInPointFromElectrode(new ReoPoint(0, 0, 0), electrode );
        assertEquals(-1.674, value, 0.001);

        eSysytem.setPosition(0, -0.02);
        electrode = eSysytem.getIElectrode(ElectrodeSystem.El.MINUS);
        value = model.potentialInPointFromElectrode(new ReoPoint(0, -0.02, 0), electrode );
        assertEquals(-1.005, value, 0.001);

        eSysytem.setPosition(0.02, 0.01);
        electrode = eSysytem.getIElectrode(ElectrodeSystem.El.MINUS);
        value = model.potentialInPointFromElectrode(new ReoPoint(0, -0.02, 0.01), electrode );
        assertEquals(-2.936, value, 0.001);
    }

    public void test2dRoVariable(){
        ElectrodeSystem eSysytem = new ElectrodeSystem(0.06, 0, 0, 0);
        BodyGeometry bodyGeometry = new BodyGeometry(0.04, 0.02);
        SphereModel model = new SphereModel();
        model.setRo(7, 1.35);
        Measurement measurement = new Measurement(model, eSysytem, bodyGeometry);
        ReoPoint electrode =  eSysytem.getIElectrode(ElectrodeSystem.El.MINUS);
        double value = model.basePotInPointFromElectrode(new ReoPoint(0,0,0), electrode);
        assertEquals(309.468, value, 0.001);
        value = model.basePotInPointFromElectrode(new ReoPoint(0, -0.01, 0), electrode);
        assertEquals(445.634, value, 0.001);
        value = model.basePotInPointFromElectrode(new ReoPoint(0, -0.05, 0), electrode);
        assertEquals(11140.846, value, 0.001);
        value = model.basePotInPointFromElectrode(new ReoPoint(0, -0.0599, 0), electrode);
        assertEquals(111408460.164, value, 0.001);
        value = model.basePotInPointFromElectrode(new ReoPoint(0, 0.05, 0.02), electrode);
        assertEquals(89.127, value, 0.001);
    }

    private static double[] getPotentials(SphereModel model, ElectrodeSystem eSystem){
        double[] value = new double[4];
        value[0] = model.potentialInPointFromElectrode(eSystem.getUElectrode(ElectrodeSystem.El.MINUS), eSystem.getIElectrode(ElectrodeSystem.El.MINUS));
        value[1] = model.potentialInPointFromElectrode(eSystem.getUElectrode(ElectrodeSystem.El.MINUS), eSystem.getIElectrode(ElectrodeSystem.El.PLUS));
        value[2] = model.potentialInPointFromElectrode(eSystem.getUElectrode(ElectrodeSystem.El.PLUS), eSystem.getIElectrode(ElectrodeSystem.El.MINUS));
        value[3] = model.potentialInPointFromElectrode(eSystem.getUElectrode(ElectrodeSystem.El.PLUS), eSystem.getIElectrode(ElectrodeSystem.El.PLUS));
        return  value;
    }
}
