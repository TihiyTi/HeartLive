package com.tihiy.reo;

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