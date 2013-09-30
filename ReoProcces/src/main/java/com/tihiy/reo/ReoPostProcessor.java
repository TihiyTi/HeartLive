package com.tihiy.reo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReoPostProcessor {
    Logger log = Logger.getLogger(getClass().getName());

    private ExpMeasurement<Double> mainMeasurement;
    private ExpMeasurement<Double> firstLayerMeasurement;
    private boolean useFirstLayer = true;

    public List<Double> getRadiusWithRo1 (){
        if((mainMeasurement==null)||(firstLayerMeasurement==null)){
            log.info("One of two measurement don't configure");
        }
        return getRadiusWithRo1(mainMeasurement, firstLayerMeasurement);
    }

    public List<Double> getRadiusWithRo1(ExpMeasurement<Double> mainImpedance, ExpMeasurement<Double> roImpedance){

        MatrixFromRoRToZ matrix = new MatrixFromRoRToZ(0.04, 5, 0.0001, 0.001);
        matrix.fillMatrix(mainImpedance);

        List<Double> listOfRo = ((OneLayerModel)roImpedance.model).getRoDelta(roImpedance.data);
        List<Double> listOfRoNull = new ArrayList<>();
        for(Double sample: listOfRo){
            listOfRoNull.add(0.0);
        }
        if(!useFirstLayer){
            listOfRo = listOfRoNull;
        }
        List<Double> listOfdRad = new ArrayList<>();
        for(int i = 0; i < listOfRo.size(); i++){
            double dRadius = matrix.getRad(listOfRo.get(i), -mainImpedance.data.get(i) / 1000);
            listOfdRad.add(dRadius);
        }

        return listOfdRad;
    }


    // Configure measurement from radial electrode system
    public void setMainMeasurement(){
        ElectrodeSystem eSystem = new ElectrodeSystem(0.06, 0.03, 0, 0);
        BodyGeometry bodyGeometry = new BodyGeometry(0.04, 0.020);
        SphereModel sphereModel = new SphereModel();
        sphereModel.setRo(5, 1.35);
        mainMeasurement = new ExpMeasurement<Double>(sphereModel, eSystem, bodyGeometry,);
        mainMeasurement.

    }

    public boolean isUseFirstLayer() {
        return useFirstLayer;
    }

    public void setUseFirstLayer(boolean useFirstLayer) {
        this.useFirstLayer = useFirstLayer;
    }


}
