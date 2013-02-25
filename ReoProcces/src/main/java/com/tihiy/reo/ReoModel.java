//package com.tihiy.reo;
//
//public final class ReoModel implements InterfaceModel {
//
//    private ElectrodeSystem electrodeSystem;
//    private BodyGeometry bodyGeometry;
//    private double rSphera;
//    private double h;
//    private double roBlood;
//    private double roTissue;
//
//    public ReoModel(double rSphera, double h, double roBlood, double roTissue) {
//        setrSphera(rSphera);
//        setH(h);
//        setRoBlood(roBlood);
//        setRoTissue(roTissue);
//    }
//
//    private double fromOtoElectrode(ElectrodeSystem e){
//        double value;
//        value = fromOtoPoint(e.getMinusElectrode());
//        return value;
//    }
//
//    private double fromOtoPoint(ReoPoint point){
//        double value;
//        double x = point.getX();
//        double y = point.getY();
//        double z = point.getZ();
//        value = Math.sqrt((rSphera + h - z)*(rSphera + h - z)+ y*y + x*x);
//        return value;
//    }
//
//    // Todo wokring for each electrode (view sign  ("znak"))
//    private static double fromPointToElectrode(ReoPoint point, ReoPoint electrode){
//        double value;
//        value = Math.sqrt((electrode.getY() - point.getY())*(electrode.getY() - point.getY())+ point.getZ()*point.getZ());
//        return value;
//    }
//    private static double getCosinus(double toPoint, double toElectrode, double toPointElectrode){
//        double value;
//        value = toPoint*toPoint+toElectrode*toElectrode - toPointElectrode*toPointElectrode;
//        value /= (2 * toPoint * toElectrode);
//        return value;
//    }
//
//    private double potentialInPointFromElectrode(ReoPoint point, ReoPoint electrode){
//        double potentialValue;
//        double toPoint = fromOtoPoint(point);
//        double toElectrode = fromOtoPoint(electrode);
//        double cosinus = getCosinus(toPoint, toElectrode, fromPointToElectrode(point, electrode));
//        potentialValue = roTissue/(2*Math.PI*toElectrode)*summaPoN(10, toPoint,toElectrode,cosinus);
//        return potentialValue;
//    }
//
//    private double underSumm(int n, double toPoint, double toElectrode, double cosinus){
//        double value = 1;
//        for(int i = 0; i < n; i++){
//            value *= rSphera*rSphera/(toElectrode*toPoint);
//        }
//        value *= rSphera/toPoint;
//        value *= ((n*(roBlood - roTissue))/(n*(roBlood+roTissue)+roBlood));
//        value *= legandr(n,cosinus);
////        System.out.println("value "+n+" = "+value);
//        return value;
//    }
//    private static double legandr(int n, double cosinus){
//        double element = 1;
//        double elementPrePre = 1;
//        double elementPre = cosinus;
//        for(int i = 2; i <= n; i++){
//            element = (2*i - 1)* cosinus *elementPre - (i - 1)*elementPrePre;
//            element /= i;
//            elementPrePre = elementPre;
//            elementPre = element;
//        }
//        if(n==1){
//            return cosinus;
//        }
//        return element;
//    }
//    private double summaPoN(int n, double toPoint, double toElectrode, double cosinus){
//        double value = 0;
//        for(int i = 0; i < n; i++){
//            value += underSumm(i, toPoint, toElectrode, cosinus);
//        }
////        System.out.println("summaPo = " + value);
//        return value;
//    }
//
//    @Override
//    public double getPotentialInPoint(ReoPoint point, ElectrodeSystem electrodeSystem) {
//        double potentialValue;
//        potentialValue = potentialInPointFromElectrode(point, electrodeSystem.getElectrode(ElectrodeSystem.El.MINUS));
////        potentialValue += potentialInPointFromElectrode(point, electrodeSystem.getMinusElectrode());
//        return potentialValue;
//    }
//
//    public void setrSphera(double rSphera) {
//        this.rSphera = rSphera;
//    }
//
//    public void setH(double h) {
//        this.h = h;
//    }
//
//    public void setRoBlood(double roBlood) {
//        this.roBlood = roBlood;
//    }
//
//    public void setRoTissue(double roTissue) {
//        this.roTissue = roTissue;
//    }
//}
//
