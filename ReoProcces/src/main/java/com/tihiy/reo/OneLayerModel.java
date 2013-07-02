package com.tihiy.reo;

import java.util.ArrayList;
import java.util.List;

public class OneLayerModel implements InterfaceModel{
    private ElectrodeSystem electrodeSystem;
    private static final double ro = 5;

    public List<Double> getRoDelta(Iterable<Double> impedance){
        List<Double> list =  new ArrayList<>();
        for(Double d: impedance){
            double value = d*Math.PI/(2*electrodeSystem.getB())*
                    (electrodeSystem.getA() * electrodeSystem.getA() - electrodeSystem.getB()*electrodeSystem.getB());
            list.add(value/1000);
        }
        return list;
    }

    @Override
    public double getPotentialInPoint(ReoPoint point) {
        return 0;
    }

    @Override
    public double getBasePotInPoint(ReoPoint point) {
        return 0;
    }

    @Override
    public void setElectrodeSystem(ElectrodeSystem e) {
        electrodeSystem = e;
    }

    @Override
    public void setBodyGeometry(BodyGeometry b) {
    }

    @Override
    public BodyGeometry getBodyGeometry() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setRoTissue(double roTissue) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}