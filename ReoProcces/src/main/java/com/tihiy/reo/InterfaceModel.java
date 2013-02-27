package com.tihiy.reo;

public interface InterfaceModel {
    double getPotentialInPoint(ReoPoint point);

    void setElectrodeSystem(ElectrodeSystem e);
    void setBodyGeometry(BodyGeometry b);
}
