package com.tihiy.reo;

public class MatrixFromRoRToZ {

    double [][] matrix;

    private double radBegin = 0;
    private double radEnd   = 0;
    private double radDelta = 0;
    private double roBegin  = 0;
    private double roEnd;
    private double roDelta;
    private int numberOfStep = 100;

    public MatrixFromRoRToZ(double radBegin, double roBegin, double radDelta, double roDelta) {
        this.radBegin = radBegin;
        this.roBegin = roBegin;
        this.radDelta = radDelta;
        this.roDelta = roDelta;
        matrix = new double[numberOfStep][numberOfStep];
    }

    public void fillMatrix(InterfaceMeasurement measurement){
        measurement.getModel().setRoTissue(roBegin);
        measurement.setRSphere(radBegin);
        double baseImp = measurement.getMeasurementFullImp();
        for(int i = 0; i < numberOfStep; i++){
            for(int j = 0; j < numberOfStep; j++){
                double ro = roBegin + roDelta* (i - numberOfStep/2);
                double rad = radBegin + radDelta*(j - numberOfStep/2);
                measurement.getModel().setRoTissue(ro);
                measurement.setRSphere(rad);
                matrix[i][j] = measurement.getMeasurementFullImp() - baseImp;
            }
        }
    }

    public double impZmatrix(double dRo, double dRad, InterfaceMeasurement measurement){
        measurement.getModel().setRoTissue(roBegin);
        measurement.setRSphere(radBegin);
        double baseImp = measurement.getMeasurementFullImp();
        measurement.getModel().setRoTissue(roBegin + dRo);
        measurement.setRSphere(radBegin + dRad);
        double endImp = measurement.getMeasurementFullImp();
        return (endImp - baseImp);
    }

    public double getRad(double dRoCurrent, double dImpedance){
        int index = getIndexRZArray(dRoCurrent);
        return getRadFromArray(index, dImpedance);
    }

    private int getIndexRZArray(double dRoCurrent){
        return (int)Math.round(dRoCurrent/roDelta) + numberOfStep/2;
    }

    private double getRadFromArray(int indexRo, double impedance){
        double delta = Math.abs(impedance);
        int indexImp = 0;
        for(int i = 0; i < matrix[indexRo].length ; i++ ){
            double valueImp = matrix[indexRo][i];
            double newDelta = Math.abs(impedance - valueImp);
            if(newDelta < delta){
                delta = newDelta;
                indexImp = i;
            }
        }
        return (indexImp - numberOfStep/2)*radDelta*1000;
    }
}