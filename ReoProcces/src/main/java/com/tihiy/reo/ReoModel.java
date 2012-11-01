package com.tihiy.reo;

public class ReoModel implements InterfaceModel {

    private double sphRadius;
    private double h;
    private double roBlood;
    private double roTissue;
//    private ElectrodeSystem electrodeSystem;

    public ReoModel(double sphRadius, double h) {
        this.sphRadius = sphRadius;
        this.h = h;
    }

    private double fromOtoElectrode(ElectrodeSystem e, double h, double sphRadius){
        double value;
        value = fromOtoPoint(e.getElectrodeI());
        return value;
    }

    private double fromOtoPoint(ReoPoint point){
        double value;
        double x = point.getX();
        double y = point.getY();
        double z = point.getZ();
        value = Math.sqrt((sphRadius + h - z)*(sphRadius + h - z)+ y*y + x*x);
        return value;
    }
    private double potentialInPointFromElectrode(ReoPoint point, ReoPoint electrodeI){
        double potentialValue = 0;
        double toPoint = fromOtoPoint(point);
        double toElectrode = fromOtoPoint(electrodeI);
//        double sum
        potentialValue = roTissue/(2*Math.PI*toElectrode)*
        return 0;
    }


    @Override
    public double getPotentialInPoint(ReoPoint point, ElectrodeSystem electrodeSystem) {
        double potentialValue = 0;
        double rUL;
        double rUR;
        double rIL;
        double rIR;
        potentialValue = potentialInPointFromElectrode(point, electrodeSystem.getElectrodeI());
//        potentialValue += potentialInPointFromElectrode(point, electrodeSystem.getElectrodeI());
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

