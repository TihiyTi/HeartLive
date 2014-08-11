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

    public SphereModelParam(double b, double a, double roTissue, double xShift, double rSphere, double roBlood, double yShift, double h) {
        this.b = b;
        this.a = a;
        this.roTissue = roTissue;
        this.xShift = xShift;
        this.rSphere = rSphere;
        this.roBlood = roBlood;
        this.yShift = yShift;
        this.h = h;
    }
}
