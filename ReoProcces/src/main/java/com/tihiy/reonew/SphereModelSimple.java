package com.tihiy.reonew;

public class SphereModelSimple {
    private SphereModelParam param;
    private SphereModelParam paramFix;
    private SphereCalc calc;
    public SphereModelSimple(SphereModelParam param){
        this.param = param;
        fixParam();
        calc = new SphereCalc(param);
    }

    public double getImpedance(){
        double result = calc.getMeasurement();
        resetParam();
        return result;
    }


    public void resetParam(){
        param.a = paramFix.a;
        param.b = paramFix.b;
        param.rSphere = paramFix.rSphere;
        param.h = paramFix.h;
        param.roBlood = paramFix.roBlood;
        param.roTissue = paramFix.roTissue;
        param.xShift = paramFix.xShift;
        param.yShift = paramFix .yShift;
    }
    public void fixParam(){
        paramFix = new SphereModelParam(param.roTissue, param.roBlood, param.a, param.b,
                param.rSphere, param.h, param.xShift, param.yShift);
    }

    public SphereModelSimple setRoBlood(double roBlood){
        param.roBlood = roBlood;
        return this;
    }
    public SphereModelSimple setRoTissue(double roTissue){
        param.roTissue = roTissue;
        return this;
    }
    public double getRoTissue(){
        return param.roTissue;
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
    public double getR(){
        return param.rSphere;
    }
//    public SphereModelSimple setDeltaR(double dr){
//        param.rSphere = param.rSphere + dr;
//        return this;
//    }
    public SphereModelSimple setH(double h){
        param.h = h;
        return this;
    }
}
