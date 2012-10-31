package com.tihiy.reo;

public class ReoModel {
    private double fromOtoElectrode(ElectrodeSystem e, double h, double sphRadius){
        double value;
        value = fromOtoPoint(e.getElectrodeI(), h, sphRadius);
        return value;
    }

    private double fromOtoPoint(ReoPoint point, double h, double sphRadius){
        double value;
        double x = point.getX();
        double y = point.getY();
        double z = point.getZ();
        value = Math.sqrt((sphRadius + h - z)*(sphRadius + h - z)+ y*y + x*x);
        return value;
    }
}

class ReoPoint{
    private final double x;
    private final double y;
    private final double z;

    ReoPoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    ReoPoint(double x, double y) {
        this.x = x;
        this.y = y;
        z = 0;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}

class ElectrodeSystem{
    static enum El {RIGHT_I, LEFT_I}

    private double x = 0;
    private double y = 0;
    private double a;
    private double b;

    ElectrodeSystem(double a, double b ,double x, double y) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
    }

    ElectrodeSystem(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }
    // Todo Realize for everyone electrodes
    public ReoPoint getElectrodeI(){
        return new ReoPoint(x, y + a);
    }
}
