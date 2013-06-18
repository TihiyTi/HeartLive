package com.tihiy.reo;

/**
 * Created with IntelliJ IDEA.
 * User: Home
 * Date: 18.06.13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
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

    public double[] getRZArray(double roCurrent){
        int i = roCurrent - ro
        return matrix[];
    }

}
