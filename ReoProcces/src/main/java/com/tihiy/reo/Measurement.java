package com.tihiy.reo;

public class Measurement implements InterfaceMeasurement {
//    ElectrodeSystem electrodeSystem;
    InterfaceModel model;
//    BodyGeometry bodyGeometry;

    public Measurement(InterfaceModel model, ElectrodeSystem eSystem, BodyGeometry bodyGeometry){
//        electrodeSystem = eSystem;
        this.model =  model;
        this.model.setBodyGeometry(bodyGeometry);
        this.model.setElectrodeSystem(eSystem);
//        this.bodyGeometry = bodyGeometry;
    }

    @Override
    public PotentialMap getPotentialMap() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float[][] MatrixResult() {
        return new float[0][];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setElectrodeSystem(ElectrodeSystem electrodeSystem) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBodyGeometry(BodyGeometry bodyGeometry) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
