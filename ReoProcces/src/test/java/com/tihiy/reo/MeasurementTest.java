package com.tihiy.reo;

import junit.framework.TestCase;

public class MeasurementTest extends TestCase{

    public void testGetMeasurementFullImp() throws Exception {
        ElectrodeSystem eSystem = new ElectrodeSystem(0.06, 0.03, 0, 0.048);
        BodyGeometry bodyGeometry = new BodyGeometry(0.056, 0.039);
        SphereModel model = new SphereModel();
        model.setRo(7, 1.35);
        InterfaceMeasurement measurement = new Measurement(model , eSystem, bodyGeometry);
        double value = measurement.getMeasurementFullImp();
        assertEquals(47.179, value, 0.001);
        measurement.setRSphere(0.02);
        value = measurement.getMeasurementFullImp();
        assertEquals(48.974, value, 0.001);
        measurement.setRSphere(0.025);
        value = measurement.getMeasurementFullImp();
    }

}
