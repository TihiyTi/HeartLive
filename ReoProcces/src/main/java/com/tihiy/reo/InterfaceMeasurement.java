package com.tihiy.reo;

public interface InterfaceMeasurement {

    PotentialMap getPotentialMap();

    double getPotentialInPoint(ReoPoint point);

    double getMeasurement(ReoPoint firstPoint, ReoPoint secondPoint);

    double getMeasurement();

    double getMeasurementFullImp();

    float[][] MatrixResult();

    void setElectrodeSystem(ElectrodeSystem electrodeSystem);

    void setBodyGeometry(BodyGeometry bodyGeometry);

    InterfaceModel getModel();

    void setRSphere(double radius);
}
