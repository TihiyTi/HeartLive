package com.tihiy.reo;

import java.util.ArrayList;
import java.util.List;

public class ReoPostProcessor {

    private boolean useFirstLayer = true;

    public List<Double> getRadiusWithRo1(ExpMeasurement<Double> mainImpedance, ExpMeasurement<Double> roImpedance){

        MatrixFromRoRToZ matrix = new MatrixFromRoRToZ(0.04, 5, 0.0001, 0.001);
        matrix.fillMatrix(mainImpedance);

        List<Double> listOfRo = ((OneLayerModel)roImpedance.model).getRoDelta(roImpedance.data);
        List<Double> listOfRoNull = new ArrayList<>();
        for(Double sample: listOfRo){
            listOfRoNull.add(0.0);
        }
        if(useFirstLayer){
            listOfRo = listOfRoNull;
        }
        List<Double> listOfdRad = new ArrayList<>();
        for(int i = 0; i < listOfRo.size(); i++){
            double dRadius = matrix.getRad(listOfRo.get(i), -mainImpedance.data.get(i) / 1000);
            listOfdRad.add(dRadius);
        }

        return listOfdRad;
    }

    public boolean isUseFirstLayer() {
        return useFirstLayer;
    }

    public void setUseFirstLayer(boolean useFirstLayer) {
        this.useFirstLayer = useFirstLayer;
    }
}
