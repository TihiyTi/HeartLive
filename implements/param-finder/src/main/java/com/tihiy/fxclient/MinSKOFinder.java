package com.tihiy.fxclient;

import com.tihiy.WindowUtils;
import com.tihiy.jfreeclient.SignalJFreePanel;
import com.tihiy.reonew.SphereCalc;
import com.tihiy.reonew.SphereModelParam;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinSKOFinder {
    public static int PARAM_H = 0;
    public static int PARAM_R = 1;
    public static int PARAM_Y = 2;
    public static int PARAM_RO1 = 3;

    private int bR = 0;
    private int eR = 0;
    private int bH = 0;
    private int eH = 0;
    private double bRo = 0;
    private double eRo = 0;
    private int bY = 0;
    private int eY = 0;

    private int minR = 0;
    private int minH = 0;
    private double minRo = 0;
    private int minY = 0;
    private double minSKO = Double.MAX_VALUE;
    public List<Double> minModelList;

    public static boolean DEBUG = false;

    public void getParamWithMinSKO(List<Double> experimentalList){
        for(int h = bH; h < eH; h++){
            for(int r = bR; r < eR; r++){
                for(double ro = bRo; ro < eRo; ro = ro + 0.1){
                    for(int y = bY; y < eY; y++){
                        List<Double> modelList = getModelData(experimentalList.size(), h, r, ro, y);
                        Double sko = SKO(experimentalList, modelList);
                        if(DEBUG){
                            System.out.println(modelList.toString());
                            System.out.println(experimentalList.toString());
                            System.out.println("              SKO = "+ sko);
                        }
                        if(sko < minSKO){
                            minSKO = sko;
                            minH = h;
                            minR =r;
                            minRo = ro;
                            minY = y;
                            minModelList = modelList;
                        }
                    }
                }
            }
        }
    }

    private List<Double> getModelData(int size, int h, int r, double ro, int y) {
        List<Double> modelList = new ArrayList<>();
        SphereModelParam param = new SphereModelParam(ro, 1.35, 0.05, 0.025, r/1000., h/1000., 0, y/1000.);
        SphereCalc calc = new SphereCalc(param);
        for (int i = 0; i < size; i++) {
            param.setYShift(y / 1000. + 0.005 * i);
            modelList.add(calc.getMeasurementFullImp());
        }
        if (DEBUG){
            System.out.println("Ro = " + ro);
//            System.out.println(modelList.toString());
        }
        return modelList;
    }
    public List<Double> getModelData(int size, int h, int r, double ro, int y, int stepInMillimeterOrMilliomh, int PARAM) {
        List<Double> modelList = new ArrayList<>();
        SphereModelParam param = new SphereModelParam(ro, 1.35, 0.05, 0.025, r/1000., h/1000., 0, y/1000.);
        SphereCalc calc = new SphereCalc(param);
        for (int i = 0; i < size; i++) {
            if(PARAM == PARAM_Y){
                param.setYShift(stepInMillimeterOrMilliomh /1000. * (i-size/2));
            }
            if(PARAM == PARAM_H){
                param.setH(stepInMillimeterOrMilliomh /1000. * i);
            }
            if(PARAM == PARAM_R){
                param.setrSphere(stepInMillimeterOrMilliomh /1000. * i);
            }
            if(PARAM == PARAM_RO1){
                param.setRoTissue(stepInMillimeterOrMilliomh/1000. * i);
            }
            modelList.add(calc.getMeasurementFullImp());
        }
        if (DEBUG){
            System.out.println("Ro = " + ro);
//            System.out.println(modelList.toString());
        }
        return modelList;
    }

    private Double SKO(List<Double> list1, List<Double> list2){
        Double summa = (double) 0;
        for (int i = 0; i < list1.size(); i++) {
            Double delta = list1.get(i) - list2.get(i);
            summa = summa + delta*delta;
        }
        return summa/list1.size();
    }

    public void setRInterval(int beginR, int endR){
        bR = beginR;
        eR = endR;
    }
    public void setHInterval(int beginH, int endH){
        bH = beginH;
        eH = endH;
    }
    public void setRoInterval(int beginRo, int endRo){
        bRo = beginRo;
        eRo = endRo;
    }
    public void setYInterval(int beginY, int endY){
        bY = beginY;
        eY = endY;
    }
    public void printResult(){
        System.out.println("Min R = " + minR);
        System.out.println("Min H = " + minH);
        System.out.println("Min ro = " + minRo);
        System.out.println("Min Y = " + minY);
    }
    public String returnResult(){
        return "Min R = " + minR + "\r\n" + "Min H = " + minH + "\r\n"+
                "Min ro = " + minRo  + "\r\n" + "Min Y = " + minY + "\r\n" +
                "SKO = " +  minSKO + "\r\n";
    }

    public static void main(String[] args) {
        MinSKOFinder finder = new MinSKOFinder();
//        DEBUG = true;
        finder.bR = 20; finder.eR = 80;
        finder.bH = 0; finder.eH = 40;
        finder.bY = 0; finder.eY = 60;
        finder.bRo = 4; finder.eRo = 15;
//        List<Double> expList = Arrays.asList(38.335, 38.492, 38.716, 38.999);
//        List<Double> expList = Arrays.asList(32.4, 33.5, 34.7);
        List<Double> expList = Arrays.asList(28.,30.,31.3,33.,34.6,35.3,35.,34.6,34.9,33.7,36.,36.6);
//        List<Double> expList = Arrays.asList(32.2, 30.8, 31.4, 31.2, 32.4, 31.8, 33.7, 34.6, 35.5, 34.8,
//                35.3, 34.7, 35., 35.5,34.3);
//        List<Double> expList = Arrays.asList(32.4, 30.7, 32.5, 31.6, 31.9, 33.4, 35.4, 36.9, 37.6,
//                38.6, 41., 43.2);
//        List<Double> expList = Arrays.asList(37., 39.5, 41.3, 43.,45.);

        finder.getParamWithMinSKO(expList);
        finder.printResult();
        finder.getSignalInFrame("Signals", finder.minModelList, expList);
//        finder.getModelData(5, 20, 42, 5, 35);
    }

    public void getSignalInFrame(String frameName, List<Double> modelList, List<Double> expList){
        JFrame frame = new JFrame("Signal");
        frame.setTitle(frameName);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(800, 500));
        frame.getContentPane().add(getSignal(modelList, expList));
        WindowUtils.centerOnScreenAndSetVisible(frame);
    }
    private JPanel getSignal(List<Double> modelList, List<Double> expList){
        return getPanelWithSignal(modelList, expList);
    }

    private JPanel getPanelWithSignal(List<Double> oneList, List<Double> twoList) {
        List<List<Double>> listOfSignal = new ArrayList<>();
        List<String> listOfNames = new ArrayList<>();
        listOfSignal.add(oneList);
        listOfNames.add("ModelList");
        if(twoList!=null){
            listOfSignal.add(twoList);
            listOfNames.add("ExpList");
        }
        return new SignalJFreePanel(listOfSignal, listOfNames);
    }
}
