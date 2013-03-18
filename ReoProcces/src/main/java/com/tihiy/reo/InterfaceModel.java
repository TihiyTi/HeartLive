package com.tihiy.reo;

public interface InterfaceModel {
    double getPotentialInPoint(ReoPoint point);
    double getBasePotInPoint(ReoPoint point);

    void setElectrodeSystem(ElectrodeSystem e);
    void setBodyGeometry(BodyGeometry b);
}
