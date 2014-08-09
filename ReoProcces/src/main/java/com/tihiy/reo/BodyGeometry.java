package com.tihiy.reo;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 23.02.13
 * Time: 15:43
 */
public class BodyGeometry {
    private double rSphere;
    private double h;
    private double rSphereDiastole;

    public BodyGeometry(double rSphere, double h) {
        this.rSphere = rSphere;
        this.rSphereDiastole = rSphere;
        this.h = h;
    }

    public double getrSphere() {
        return rSphere;
    }

    public void setrSphere(double rSphere) {
        this.rSphere = rSphere;
    }
    public void resetRSphere(){
        rSphere = rSphereDiastole;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }
}
