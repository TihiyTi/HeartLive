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
        assertEquals(48.347, value, 0.001);
        measurement.setRSphere(0.02);
        value = measurement.getMeasurementFullImp();
        assertEquals(49.244, value, 0.001);
        measurement.setRSphere(0.025);
        value = measurement.getMeasurementFullImp();
        assertEquals(49.116, value, 0.001);
        measurement.setRSphere(0.03);
        value = measurement.getMeasurementFullImp();
        assertEquals(48.983, value, 0.001);
        measurement.setRSphere(0.035);
        value = measurement.getMeasurementFullImp();
        assertEquals(48.85, value, 0.001);
        measurement.setRSphere(0.04);
        value = measurement.getMeasurementFullImp();
        assertEquals(48.721, value, 0.001);
        measurement.setRSphere(0.045);
        value = measurement.getMeasurementFullImp();
        assertEquals(48.598, value, 0.001);
        measurement.setRSphere(0.05);
        value = measurement.getMeasurementFullImp();
        assertEquals(48.48, value, 0.001);
    }

}
