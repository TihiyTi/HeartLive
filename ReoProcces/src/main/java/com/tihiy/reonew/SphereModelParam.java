package com.tihiy.reonew;

public class SphereModelParam {
    double xShift = 0;
    double yShift = 0;
    double a;
    double b;

    double rSphere;

    double h;
    double roBlood;
    double roTissue;
    public SphereModelParam(double roTissue, double roBlood, double a, double b, double rSphere, double h, double xShift, double yShift) {
        this.b = b;
        this.a = a;
        this.roTissue = roTissue;
        this.xShift = xShift;
        this.rSphere = rSphere;
        this.roBlood = roBlood;
        this.yShift = yShift;
        this.h = h;
    }

    public void setYShift(double yShift){
        this.yShift = yShift;
    }

    public void setrSphere(double rSphere) {
        this.rSphere = rSphere;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setRoTissue(double roTissue) {
        this.roTissue = roTissue;
    }
}
