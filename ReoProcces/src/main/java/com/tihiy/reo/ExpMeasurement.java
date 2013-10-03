package com.tihiy.reo;

import java.util.Collection;
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
        data = pulse;
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

    //todo this is bad method calculate roEquivalent, because used only first element of base Impedance
    public double getRoEquivalent(){
        double result = (Double)baseImp.get(0)*Math.PI;
        result *= (electrodeSystem.getA() * electrodeSystem.getA() - electrodeSystem.getB() * electrodeSystem.getB());
        result /= (2 * electrodeSystem.getB());
        return result;
    }
}
