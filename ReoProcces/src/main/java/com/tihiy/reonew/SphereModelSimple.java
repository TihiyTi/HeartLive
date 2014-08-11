package com.tihiy.reonew;

public class SphereModelSimple {
    SphereModelParam param;
    SphereModelParam paramFix;
    SphereCalc calc = new SphereCalc(param);
    public SphereModelSimple(SphereModelParam param){
        this.param = param;
        this.paramFix = param;
    }

    public double getImpedance(){
        return 0;
    }


    public void resetParam(){
        param = paramFix;
    }
    public void fixParam(){
        paramFix = param;
    }

    public SphereModelSimple setRo(double roBlood, double roTissue){
        param.roBlood = roBlood;
        param.roTissue = roTissue;
        return this;
    }
    public SphereModelSimple setAB(double a, double b){
        param.a = a;
        param.b = b;
        return this;
    }
    public SphereModelSimple setR(double r){
        param.rSphere = r;
        return this;
    }
    public SphereModelSimple setH(double h){
        param.h = h;
        return this;
    }
}
