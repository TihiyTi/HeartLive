import com.sun.org.apache.xpath.internal.SourceTree;
import org.ejml.simple.SimpleMatrix;

import java.util.Arrays;
import java.util.List;

public class PolynomialApproximator {
    boolean log = false;

    public PolynomialApproximator(){}
    public PolynomialApproximator(boolean log){
        this.log = log;
    }

    public void approx(List<Double> signal, int polynomRange){
        SimpleMatrix koeffMatrix = fillKoeffMatrix(signal.size(), polynomRange);
        SimpleMatrix skMatrix = fillSkMatrix(signal, polynomRange);
        SimpleMatrix aMatrix = koeffMatrix.solve(skMatrix);
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

    private double kFunction(int k, int exponent){
        double summ = 0;
        for (int i = 0; i < k; i++) {
            summ += Math.pow(k,exponent);
        }
        return summ;
    }

    private SimpleMatrix fillSkMatrix(List<Double> list, int polynomeRange){
        SimpleMatrix skMatrix = new SimpleMatrix()
    }

    public static void main(String[] args) {
        List<Double> list = Arrays.asList(0., 0., 0., 1.2, 3.4);
        PolynomialApproximator approximator = new PolynomialApproximator(true);
        approximator.approx(list, 2);
    }

}

