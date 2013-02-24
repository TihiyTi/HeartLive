package com.tihiy.reo;

public interface InterfaceMeasurement {

    public PotentialMap getPotentialMap();

    public float[][] MatrixResult();

    public void setElectrodeSystem(ElectrodeSystem electrodeSystem);

    public void setBodyGeometry(BodyGeometry bodyGeometry);

}
