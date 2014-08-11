package com.tihiy.reonew;

import com.tihiy.reo.ElectrodeSystem;

public class SphereCalc {
    private SphereModelParam p;
    public SphereCalc(SphereModelParam param) {
        p = param;
    }
    public double getMeasurementFullImp(){
        double value = model.getBasePotInPoint(electrodeSystem.getUElectrode(ElectrodeSystem.El.MINUS));
        value -= model.getBasePotInPoint(electrodeSystem.getUElectrode(ElectrodeSystem.El.PLUS));
        value += getMeasurement();
        return value;
    }
    public double getMeasurement() {
        double value = model.getPotentialInPoint(electrodeSystem.getUElectrode(ElectrodeSystem.El.MINUS));
        value -= model.getPotentialInPoint(electrodeSystem.getUElectrode(ElectrodeSystem.El.PLUS));
        return value;
    }

    private double toM(){
        return Math.sqrt((p.rSphere+p.h)*(p.rSphere+p.h) +
                (p.b - p.yShift)*(p.b - p.yShift) + p.xShift*p.xShift);
    }
    private double toN(){
        return Math.sqrt((p.rSphere+p.h)*(p.rSphere+p.h) +
                (p.b + p.yShift)*(p.b + p.yShift) + p.xShift*p.xShift);
    }
    private double toA(){
        return Math.sqrt((p.rSphere+p.h)*(p.rSphere+p.h) +
                (p.a - p.yShift)*(p.a - p.yShift) + p.xShift*p.xShift);
    }
    private double toB(){
        return Math.sqrt((p.rSphere+p.h)*(p.rSphere+p.h) +
                (p.a + p.yShift)*(p.a + p.yShift) + p.xShift*p.xShift);
    }

    private double cos


    public double getPotentialInPoint(ReoPoint point) {
        double potentialValue;
        potentialValue = potentialInPointFromElectrode(point, electrodeSystem.getIElectrode(ElectrodeSystem.El.MINUS));
        potentialValue -= potentialInPointFromElectrode(point, electrodeSystem.getIElectrode(ElectrodeSystem.El.PLUS));
        return potentialValue;
    }
    public double getBasePotInPoint(ReoPoint point) {
        double value;
        value = basePotInPointFromElectrode(point, electrodeSystem.getIElectrode(ElectrodeSystem.El.MINUS));
        value -= basePotInPointFromElectrode(point, electrodeSystem.getIElectrode(ElectrodeSystem.El.PLUS));
        return value;
    }
    public double potentialInPointFromElectrode(ReoPoint point, ReoPoint electrode){
        double potentialValue;
        double toPoint = toPoint(point);
        double toElectrode = toPoint(electrode);
        double cosinus = getCosinus(toPoint, toElectrode, fromPointToElectrode(point, electrode));
        potentialValue = roTissue/(Math.PI*toElectrode)*summaPoN(10, toPoint,toElectrode,cosinus);
//        log.info("ToPoint = "+ toPoint + "\n" + "ToElectrode = " + toElectrode + "\n"+ "Cosinus = " + cosinus +
//                "\n potentialValue" + potentialValue);
        return potentialValue;
    }
    public double basePotInPointFromElectrode(ReoPoint point, ReoPoint electrode){
        double w = fromPointToElectrode(point, electrode);
        return roTissue/(2*Math.PI*w);
    }
    private double toPoint(ReoPoint point){
        double value;
        double x = point.getX();
        double y = point.getY();
        double z = point.getZ();
        double r = bodyGeometry.getrSphere();
        double h = bodyGeometry.getH();
        value = Math.sqrt((r + h - z)*(r + h - z)+ y*y + x*x);
        return value;
    }
    private static double fromPointToElectrode(ReoPoint point, ReoPoint electrode){
        double value;
        value = Math.sqrt((electrode.getX() - point.getX())*(electrode.getX() - point.getX()) +
                (electrode.getY() - point.getY())*(electrode.getY() - point.getY()) +
                point.getZ()*point.getZ());
        return value;
    }
    private static double getCosinus(double toPoint, double toElectrode, double toPointElectrode){
        double value;
        value = toPoint*toPoint+toElectrode*toElectrode - toPointElectrode*toPointElectrode;
        value /= (2 * toPoint * toElectrode);
        return value;
    }
    private double underSumm(int n, double toPoint, double toElectrode, double cosinus){
        double value = 1;
        for(int i = 0; i < n; i++){
            value *= p.rSphere*p.rSphere/(toElectrode*toPoint);
        }
        value *= p.rSphere/toPoint;
        value *= ((n*(p.roBlood - p.roTissue))/(n*(p.roBlood + p.roTissue) + p.roBlood));
        value *= legandr(n,cosinus);
//        log.info("underSumm = "+ value);
        return value;
    }
    private static double legandr(int n, double cosinus){
        double element = 1;
        double elementPrePre = 1;
        double elementPre = cosinus;
        for(int i = 2; i <= n; i++){
            element = (2*i - 1)* cosinus *elementPre - (i - 1)*elementPrePre;
            element /= i;
            elementPrePre = elementPre;
            elementPre = element;
        }
        if(n==1){
            return cosinus;
        }
        return element;
    }
    private double summaPoN(int n, double toPoint, double toElectrode, double cosinus){
        double value = 0;
        for(int i = 0; i < n; i++){
            value += underSumm(i, toPoint, toElectrode, cosinus);
//            log.info("element Sum = " + value);
        }
//        log.info("SummaPoN = " + value);
        return value;
    }
}
