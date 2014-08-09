package com.tihiy.reo;

import java.util.ArrayList;
import java.util.List;

public class Measurement implements InterfaceMeasurement {
    ElectrodeSystem electrodeSystem;
    InterfaceModel model;

    public Measurement(InterfaceModel model, ElectrodeSystem eSystem, BodyGeometry bodyGeometry){
        electrodeSystem = eSystem;
        this.model =  model;
        this.model.setBodyGeometry(bodyGeometry);
        this.model.setElectrodeSystem(electrodeSystem);
    }

    @Override
    public PotentialMap getPotentialMap() {
        return null;
    }

    @Override
    public double getPotentialInPoint(ReoPoint point) {
        return model.getPotentialInPoint(point);
    }

    @Override
    public double getMeasurement(ReoPoint firstPoint, ReoPoint secondPoint) {
        double value = model.getPotentialInPoint(firstPoint);
        value -= model.getPotentialInPoint(secondPoint);
        return value;
    }

    @Override
    public double getMeasurement() {
        double value = model.getPotentialInPoint(electrodeSystem.getUElectrode(ElectrodeSystem.El.MINUS));
        value -= model.getPotentialInPoint(electrodeSystem.getUElectrode(ElectrodeSystem.El.PLUS));
        return value;
    }

    //Todo this method is bad for not sphere model, because it return something xxx
    @Override
    public double getMeasurementFullImp(){
        double value = model.getBasePotInPoint(electrodeSystem.getUElectrode(ElectrodeSystem.El.MINUS));
        value -= model.getBasePotInPoint(electrodeSystem.getUElectrode(ElectrodeSystem.El.PLUS));
        value += getMeasurement();
        return value;
    }

    public List<Double> getListOfImpedance(List<Double> drValueList){
        List<Double> listOfImpedance = new ArrayList<>();
        resetRSphere();
        double radiusDiastole = getModel().getBodyGeometry().getrSphere();
        for(Double drValue: drValueList){
            setRSphere(radiusDiastole+drValue/1000);
            listOfImpedance.add(getMeasurement());
        }
        return listOfImpedance;
    }

    @Override
    public void setElectrodeSystem(ElectrodeSystem electrodeSystem) {
    }
    @Override
    public void setBodyGeometry(BodyGeometry bodyGeometry) {
    }
    @Override
    public void setRSphere(double radius){
        model.getBodyGeometry().setrSphere(radius);
    }
    public void resetRSphere(){model.getBodyGeometry().resetRSphere();}
    // todo this method is bad method because we don't ask model about somethings
    @Override
    public InterfaceModel getModel(){
        return model;
    }
}
