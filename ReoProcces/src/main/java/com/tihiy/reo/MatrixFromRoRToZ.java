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

    public double getRad(double roCurrent, double impedance){
        int index = getIndexRZArray(roCurrent);
        return getRadFromArray(index, impedance);
    }

    private int getIndexRZArray(double roCurrent){
        return (int)Math.round((roCurrent - roBegin)/roDelta);
    }

    private double getRadFromArray(int indexRo ,double impedance){
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
        return matrix[indexRo][indexImp];
    }

}
