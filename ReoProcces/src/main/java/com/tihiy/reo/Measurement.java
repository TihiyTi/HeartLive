package com.tihiy.reo;

public class Measurement implements InterfaceMeasurement {
    ElectrodeSystem electrodeSystem;
    InterfaceModel model;
//    BodyGeometry bodyGeometry;

    public Measurement(InterfaceModel model, ElectrodeSystem eSystem, BodyGeometry bodyGeometry){
        electrodeSystem = eSystem;
        this.model =  model;
        this.model.setBodyGeometry(bodyGeometry);
        this.model.setElectrodeSystem(electrodeSystem);
//        this.bodyGeometry = bodyGeometry;
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

    @Override
    public float[][] MatrixResult() {
        return new float[0][];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setElectrodeSystem(ElectrodeSystem electrodeSystem) {
    }

    @Override
    public void setBodyGeometry(BodyGeometry bodyGeometry) {
    }


    // todo this method is bad method because we don't ask model about somethings
    @Override
    public InterfaceModel getModel(){
        return model;
    }
}
