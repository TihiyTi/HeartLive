package com.tihiy.reo.calc;

import com.tihiy.reo.Measurement;

public class FindRadEquivalent {
    Measurement measurement;

    public FindRadEquivalent(Measurement measurement) {
        this.measurement = measurement;
    }

    public double getRadEquval(double baseImpidance){
        double delta = Math.abs(measurement.getMeasurementFullImp() - baseImpidance);
        double rEquivalent = 0;
        for(int i = 0; i < 100; i++){
            measurement.setRSphere(0.001 * i);
            double newDelta = Math.abs(measurement.getMeasurementFullImp()-baseImpidance);
            if( newDelta < delta ){
                delta = newDelta;
                rEquivalent = 0.001 * i;
            }
        }
        return rEquivalent;
    }
}
