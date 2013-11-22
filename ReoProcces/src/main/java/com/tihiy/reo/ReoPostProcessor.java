package com.tihiy.reo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReoPostProcessor {
    Logger log = Logger.getLogger(getClass().getName());

    private ExpMeasurement<Double> mainMeasurement;
    private ExpMeasurement<Double> firstLayerMeasurement;
    private boolean useFirstLayer = true;
    private boolean useBaseImpedance = true;

    private double roEquivalent = 5;

    public List<Double> getRadiusWithRo1 (){
        if((mainMeasurement==null)||(firstLayerMeasurement==null)){
            log.info("One of two measurement don't configure");
        }
        return getRadiusWithRo1(mainMeasurement, firstLayerMeasurement);
    }


    public List<Double> getRadiusWithRo1(ExpMeasurement<Double> mainImpedance, ExpMeasurement<Double> roImpedance){

        if(useBaseImpedance){
            roEquivalent = mainImpedance.getRoEquivalent();
        }

        MatrixFromRoRToZ matrix = new MatrixFromRoRToZ(0.045, roEquivalent, 0.0001, 0.001);
        matrix.fillMatrix(mainImpedance);

        List<Double> listOfRo = ((OneLayerModel)roImpedance.model).getRoDelta(roImpedance.getData());
        List<Double> listOfRoNull = new ArrayList<>();
        for(Double sample: listOfRo){
            listOfRoNull.add(0.0);
        }
        if(!useFirstLayer){
            listOfRo = listOfRoNull;
        }
        List<Double> listOfdRad = new ArrayList<>();
        for(int i = 0; i < listOfRo.size(); i++){
            double dRadius = matrix.getRad(listOfRo.get(i), -mainImpedance.getData().get(i) / 1000);
            listOfdRad.add(dRadius);
        }

        return listOfdRad;
    }

    //todo delete this method because mainImpedance contains data about radBegin and roBegin
    public List<Double> badGetRadiusWithRo1(ExpMeasurement<Double> mainImpedance, ExpMeasurement<Double> roImpedance, double rad, double ro){

        MatrixFromRoRToZ matrix = new MatrixFromRoRToZ(rad, ro, 0.0001, 0.001);
        matrix.fillMatrix(mainImpedance);

        List<Double> listOfRo = ((OneLayerModel)roImpedance.model).getRoDelta(roImpedance.getData());
        List<Double> listOfRoNull = new ArrayList<>();
        for(Double sample: listOfRo){
            listOfRoNull.add(0.0);
        }
        if(!useFirstLayer){
            listOfRo = listOfRoNull;
        }
        List<Double> listOfdRad = new ArrayList<>();
        for(int i = 0; i < listOfRo.size(); i++){
            double dRadius = matrix.getRad(listOfRo.get(i), -mainImpedance.getData().get(i) / 1000);
            listOfdRad.add(dRadius);
        }

        return listOfdRad;
    }

    // Configure measurement from radial electrode system
    public void setMainMeasurement(double a, double b, double xShift, double yShift, double rSphere, double h, List<Double> list){
        ElectrodeSystem eSystem = new ElectrodeSystem(a, b, xShift, yShift);
        BodyGeometry bodyGeometry = new BodyGeometry(rSphere, h);
        SphereModel sphereModel = new SphereModel();
        sphereModel.setRo(5, 1.35);
        mainMeasurement = new ExpMeasurement<Double>(sphereModel, eSystem, bodyGeometry, list);
    }
    public void setMainMeasurement(double a, double b, double xShift, double yShift, double rSphere, double h, List<Double> list, List<Double> base){
        setMainMeasurement(a,b,xShift,yShift,rSphere,h,list);
        mainMeasurement.setBaseImp(base);
    }
//    public void setMainMeasurement(List<double[]> parameters, List<Double> list, List<Double> base){
//        .
//        setMainMeasurement();
//    }

    // Configure measurement from first layer
    public void setFirstLayerMeasurement(double a, double b, List<Double> list){
        ElectrodeSystem eSystem = new ElectrodeSystem(a, b, 0, 0);
        OneLayerModel model = new OneLayerModel();
        firstLayerMeasurement =  new ExpMeasurement<>(model, eSystem, null, list);
    }
    public boolean isUseFirstLayer() {
        return useFirstLayer;
    }

    public void setUseFirstLayer(boolean useFirstLayer) {
        this.useFirstLayer = useFirstLayer;
    }

    public void setUseBaseImpedance(boolean useBaseImpedance){
        this.useBaseImpedance = useBaseImpedance;
    }

    public void setRoEquivalent(double roEquivalent) {
        this.roEquivalent = roEquivalent;
    }


}
