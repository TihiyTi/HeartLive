import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.List;

public class PolynomialApproximator {
    boolean log = false;

    public PolynomialApproximator(){}
    public PolynomialApproximator(boolean log){
        this.log = log;
    }

    public SimpleMatrix approxKoef(List<Double> signal, int polynomRange){
        SimpleMatrix koeffMatrix = fillKoeffMatrix(signal.size(), polynomRange);
        SimpleMatrix skMatrix = fillSkMatrix(signal, polynomRange);
        SimpleMatrix aMatrix = koeffMatrix.solve(skMatrix);
//        aMatrix.print(4, 6);
        return aMatrix;
    }
    public List<Double> getApproxSignal(List<Double> list, int polynomRange){
        SimpleMatrix aMatrix = approxKoef(list,polynomRange);
        List<Double> approxSignal = new ArrayList<>();
        for(int i = 0; i < list.size();i++){
            approxSignal.add(polynome(i, aMatrix));
        }
        return approxSignal;
    }
    public List<Double> getApproxSignal(List<Double> list, List<Double> args, int polynomRange){
        SimpleMatrix aMatrix = approxKoef(list,polynomRange);
        List<Double> approxSignal = new ArrayList<>();
        args.forEach(e-> approxSignal.add(polynome(e, aMatrix)));
        return approxSignal;
    }
    public List<Double> getApproxSignal(List<Double> list, int sizeOfReturnList, int polynomRange){
        List<Double> args= new ArrayList<>();
        for (int i = 0; i < sizeOfReturnList; i++) {
            args.add(i*1.*(list.size()-1)/(sizeOfReturnList-1));
        }
        return getApproxSignal(list, args, polynomRange);
    }

    private SimpleMatrix fillKoeffMatrix(int signalLength ,int polynomRange){
        SimpleMatrix matrix = new SimpleMatrix(polynomRange + 1, polynomRange + 1);
        for (int numOfRow = 0; numOfRow <= polynomRange; numOfRow++) {
            for (int numOfColumn = 0; numOfColumn <= polynomRange; numOfColumn++) {
                matrix.set(numOfRow, numOfColumn, kFunction(signalLength, numOfColumn + numOfRow));
            }
        }
        if(log){
            System.out.println("Koeffitient matrix");
            matrix.print(5, 3);
        }
        return matrix;
    }

    private SimpleMatrix fillSkMatrix(List<Double> list, int polynomeRange){
        SimpleMatrix matrix = new SimpleMatrix(polynomeRange + 1, 1);
        for (int numOfRow = 0; numOfRow < polynomeRange + 1; numOfRow++) {
            matrix.set(numOfRow, 0, skFunktion(list, list.size(), numOfRow));
        }
        if(log){
            System.out.println("{S(k)*k^n}matrix");
            matrix.print(5, 3);
        }
        return  matrix;
    }

    private double kFunction(int k, int exponent){
        double summ = 0;
        for (int i = 0; i < k; i++) {
            summ += Math.pow(i,exponent);
        }
        return summ;
    }

    private double skFunktion(List<Double> list, int k, int exponent){
        double summ = 0;
        for (int i = 0; i < k; i++) {
            summ += list.get(i) * Math.pow(i,exponent);
        }
        return summ;
    }

    private double polynome(double arg, SimpleMatrix koef){
        double sum = 0;
        int numRows = koef.numRows();
        for (int i = 0; i < numRows; i++) {
            sum += koef.get(i)*Math.pow(arg, i);
        }
        return sum;
    }

}

