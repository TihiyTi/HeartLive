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

        dx.setColumn(0, 0, 1,1,3.6,5.4,7.2);
        dx.setColumn(1, 0, 3.54,3.54,5.31,10.62,15.93);
        dx.setColumn(2, 0, 1.75,3.5,7,10.5,14.2);
        dx.setColumn(3, 0, -1.75,1.775,3.5,8.75,12.25);
        dx.setColumn(4, 0, 3.42,6.84,8.55,11.97,13.68);

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
