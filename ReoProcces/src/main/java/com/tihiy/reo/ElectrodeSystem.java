package com.tihiy.reo;

public class ElectrodeSystem{
    static enum El {PLUS, MINUS}

    private double xShift = 0;
    private double yShift = 0;
    private double a;
    private double b;

    public ElectrodeSystem(double a, double b ,double xShift, double yShift) {
        this.xShift = xShift;
        this.yShift = yShift;
        this.a = a;
        this.b = b;
    }

    public ElectrodeSystem(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void setPosition(double xShift, double yShift){
        this.xShift = xShift;
        this.yShift = yShift;
    }

    public ReoPoint getIElectrode(El el){
        if(el==El.MINUS){
            return new ReoPoint(xShift, yShift - a);
        }else{
            return new ReoPoint(xShift, yShift + a);
        }
    }

    public ReoPoint getUElectrode(El el){
        if(el==El.MINUS){
            return new ReoPoint(xShift, yShift - b);
        }else{
            return new ReoPoint(xShift, yShift + b);
        }
    }

    public double getA(){
        return a;
    }

    public double getB(){
        return b;
    }
}