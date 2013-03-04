package com.tihiy.reo;

public interface InterfaceMeasurement {

    public PotentialMap getPotentialMap();

    public double getPotentialInPoint(ReoPoint point);

    public double getMeasurement(ReoPoint firstPoint, ReoPoint secondPoint);

    public double getMeasurement();

    public float[][] MatrixResult();

    public void setElectrodeSystem(ElectrodeSystem electrodeSystem);

    public void setBodyGeometry(BodyGeometry bodyGeometry);

}
