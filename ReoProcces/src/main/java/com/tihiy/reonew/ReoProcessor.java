package com.tihiy.reonew;

import java.util.ArrayList;
import java.util.List;

public class ReoProcessor {
    public static boolean MILLI_OMH = true;
    public static boolean OMH = false;

    private SphereModelSimple model;

    private int numOfStep = 200;
    private double stepOfRadius = 0.001 * 0.1;

    public ReoProcessor(SphereModelSimple model){
        this.model = model;
    }

    public double getDeltaRadius(double deltaImpedance){
        model.resetParam();
        double radius = model.getR();
        double dR = 0;
        double minValue = Double.MAX_VALUE;
        for(int i = -numOfStep/2; i < numOfStep/2; i++){
            double impOld = model.getImpedance();
            double impNew = model.setR(radius + i* stepOfRadius).getImpedance();
            double dImp = Math.abs(impNew - impOld - deltaImpedance);
            if(minValue > dImp){
                minValue = dImp;
                dR = i* stepOfRadius;
            }
        }
        model.resetParam();
        return dR;
    }
    public double getDeltaRadius(double deltaImpedance, double deltaRo){
        model.resetParam();
        double radius = model.getR();
        double roTissue = model.getRoTissue();
        double dR = 0;
        double minValue = Double.MAX_VALUE;
        for(int i = -numOfStep/2; i < numOfStep/2; i++){
            double impOld = model.getImpedance();
            double impNew = model.setR(radius + i* stepOfRadius).setRoTissue(roTissue + deltaRo).getImpedance();
            double dImp = Math.abs(impNew - impOld - deltaImpedance);
            if(minValue > dImp){
                minValue = dImp;
                dR = i* stepOfRadius;
            }
        }
        model.resetParam();
        return dR;
    }

    public List<Double> getDeltaRadiusList(List<Double> listOfImpedance, boolean type){
        List<Double> listOfRadius = new ArrayList<>();
        for(Double impValue: listOfImpedance){
            if(type){
                listOfRadius.add(getDeltaRadius(impValue/1000));
            }else{
                listOfRadius.add(getDeltaRadius(impValue));
            }
        }
        return listOfRadius;
    }
    public List<Double> getDeltaRadiusList(List<Double> listOfImpedance, List<Double> listOfDeltaRo, boolean type){
        List<Double> listOfRadius = new ArrayList<>();
        for(int i = 0; i < listOfImpedance.size(); i++){
            double impValue = listOfImpedance.get(i);
            double dRoValue = listOfDeltaRo.get(i);
            if(type){
                listOfRadius.add(getDeltaRadius(-impValue/1000, -dRoValue));
            }else{
                listOfRadius.add(getDeltaRadius(-impValue, -dRoValue));
            }
        }
        return listOfRadius;
    }

    public List<Double> getImpedanceList(List<Double> listOfRadius){
        List<Double> listOfImpedance = new ArrayList<>();
        double radius = model.getR();
        for(Double radValue: listOfRadius){
            listOfImpedance.add(model.setR(radius + radValue).getImpedance());
        }
        return listOfImpedance;
    }
}
