package com.tihiy.reonew;

public class SphereCalc {
    private SphereModelParam p;
    public SphereCalc(SphereModelParam param) {
        p = param;
    }

    public double getMeasurementFullImp(){
        double value = p.roTissue*2*p.b/(Math.PI*(p.a*p.a-p.b*p.b));
        value += getMeasurement();
        return value;
    }
    public double getMeasurement() {
        double value = potentialInPointFromElectrode(toA(), toM(),getCosinus(toM(),toA(), (p.a - p.b)));
        value -= potentialInPointFromElectrode(toA(), toN(),getCosinus(toN(),toA(), (p.a + p.b)));
        value += potentialInPointFromElectrode(toB(), toN(),getCosinus(toN(),toB(), (p.a - p.b)));
        value -= potentialInPointFromElectrode(toB(), toM(),getCosinus(toM(),toB(), (p.a + p.b)));
        return value;
    }

    public double potentialInPointFromElectrode(double toEl, double toPt, double cosElPt){
        double potentialValue;
        potentialValue = p.roTissue/(Math.PI*toEl)*summaPoN(10, toPt, toEl, cosElPt);
        return potentialValue;
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
        }
        return value;
    }
}
