package com.tihiy.reo;

import java.util.List;

public class ExpMeasurement<T extends Number> extends Measurement {

    private List<T> data;
    private List<T> baseImp;

    public ExpMeasurement(InterfaceModel model, ElectrodeSystem eSystem, BodyGeometry bodyGeometry, List<T> pulse) {
        super(model, eSystem, bodyGeometry);
        data = pulse;
    }

    public ExpMeasurement(InterfaceModel model, ElectrodeSystem eSystem, BodyGeometry bodyGeometry, List<T> pulse, List<T> base){
        super(model, eSystem, bodyGeometry);
        baseImp = base;
    }

    public List<T> getData(){
        return data;
    }
    public void setData(List<T> data){
        this.data = data;
    }

    public void setBaseImp(List<T> baseImp){
        this.baseImp = baseImp;
    }
}
