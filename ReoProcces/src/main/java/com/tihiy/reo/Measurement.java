package com.tihiy.reo;

public class Measurement implements InterfaceMeasurement {
    ElectrodeSystem electrodeSystem;
    InterfaceModel model;

    public Measurement(InterfaceModel model, ElectrodeSystem eSystem){
        this.electrodeSystem = eSystem;
        this.model =  model;
    }

    // return value of pulse impedance
    public double test(){

        return 0;
    }
}
