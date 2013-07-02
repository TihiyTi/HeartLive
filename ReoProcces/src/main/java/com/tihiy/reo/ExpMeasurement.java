package com.tihiy.reo;

import java.util.List;

public class ExpMeasurement<T extends Number> extends Measurement {

    List<T> data;

    public ExpMeasurement(InterfaceModel model, ElectrodeSystem eSystem, BodyGeometry bodyGeometry, List<T> list) {
        super(model, eSystem, bodyGeometry);
        data = list;
    }

    public List<T> getData(){
        return data;
    }
}