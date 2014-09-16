package com.tihiy.reonew.utils;

import org.ejml.simple.SimpleMatrix;

// dz = dx * a
// b = a^-1
// returnDx = dzNew*b
public class DxMatrixFinder {
    SimpleMatrix dx;
    SimpleMatrix dz;
    SimpleMatrix a;
    SimpleMatrix b;

    public DxMatrixFinder(SimpleMatrix dx, SimpleMatrix dz){
        this.dz = dz;
        this.dx = dx;
        a = dx.solve(dz);
        b = a.invert();
    }
    public DxMatrixFinder(){
        dx = new SimpleMatrix(5,5);
        dz = new SimpleMatrix(5,5);

        dz.setColumn(0, 0, 36,56,72,156,228);
        dz.setColumn(1, 0, 40,70,90,172,234);
        dz.setColumn(2, 0, 31,68,84,154,214);
        dz.setColumn(3, 0, 20,40,47,102,129);
        dz.setColumn(4, 0, 33,52,67,150,221);
//
//        dz.setColumn(0, 0, 36,48,77,166,229);
//        dz.setColumn(1, 0, 32,65,98,173,233);
//        dz.setColumn(2, 0, 32,66,93,167,219);
//        dz.setColumn(3, 0, 21,36,47,106,133);
//        dz.setColumn(4, 0, 37,48,77,167,229);

        // До сглаживания перемещений
//        dx.setColumn(0, 0, 1,1,3.6,5.4,7.2);
//        dx.setColumn(1, 0, 3.54,3.54,5.31,10.62,15.93);
//        dx.setColumn(2, 0, 1.75,3.5,7,10.5,14.2);
//        dx.setColumn(3, 0, -1.75,1.775,3.5,8.75,12.25);
//        dx.setColumn(4, 0, 3.42,6.84,8.55,11.97,13.68);
//        После сглаживания перемещений
        dx.setColumn(0, 0, 0,-0.2,2.9,6.3,7.3);
        dx.setColumn(1, 0, 1.9,3.6,5.1,10,15.7);
        dx.setColumn(2, 0, 0.1,2.1,6.3,10.8,14.3);
        dx.setColumn(3, 0, -1.8,-0.2,3.6,8.3,12.3);
        dx.setColumn(4, 0, 1.7,5,8.7,11.6,13.6);
//
//        После сглаживания перемещений
//        dx.setColumn(0, 0, 0, 0.01, -0.2, 0.8, 2.9, 5.1, 6.3, 6.6, 7.3);
//        dx.setColumn(1, 0, 1.9, 3.2, 3.6, 4, 5.1, 7.2, 10, 13.1, 15.7);
//        dx.setColumn(2, 0, 0.1, 1.4, 2.1, 3.7, 6.3, 9, 10,8, 11.9, 14.3);
//        dx.setColumn(3, 0, -1.8, -1.4, -0.2, 1.5, 3.6, 5.9, 8.3, 10.5, 12.3);
//        dx.setColumn(4, 0, 1.7, 3.6, 5, 6.7, 8.7, 10.4, 11.6, 12.2, 13.6);

        a = dx.solve(dz);
        b = a.invert();
    }

    public void setDz(SimpleMatrix dz){
        this.dz = dz;
        a = dx.solve(dz);
        b = a.invert();
    }

    public SimpleMatrix getDx(SimpleMatrix dzNew){
        dz = dzNew;
        SimpleMatrix result = dz.mult(b);
        printAll(result);
        return result;

    }
    public SimpleMatrix getOriginDx(){
        return dx;
    }
    private void printAll(SimpleMatrix result){
//        System.out.println("DX matrix");
//        dx.print(2,2);
//        System.out.println();

//        System.out.println("              DZ matrix");
//        dz.print(2, 2);
//        System.out.println();

//        System.out.println("A matrix");
//        a.print(2,2);
//        System.out.println();
//
//        System.out.println("B matrix");
//        b.print(2,2);
//        System.out.println();

//        System.out.println("RESULT matrix");
        SimpleMatrix forPrint = result.extractVector(true, 4);
        forPrint.print(2,1);
        System.out.println();
    }
}
