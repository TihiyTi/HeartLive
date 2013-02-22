package com.tihiy.reo;

public class Measurement implements InterfaceMeasurement {
    ElectrodeSystem electrodeSystem;
    InterfaceModel model;

    public Measurement(InterfaceModel model, ElectrodeSystem eSystem){
        electrodeSystem = eSystem;
        this.model =  model;
    }

    @Override
    public PotentialMap getPotentialMap() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float[][] MatrixResult() {
        return new float[0][];  //To change body of implemented methods use File | Settings | File Templates.
    }
}
