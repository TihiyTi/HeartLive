import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.Arrays;
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
        aMatrix.print(4, 6);
        return aMatrix;
    }
    public List<Double> getApproxSignal(List<Double> list, int polynomRange){
        SimpleMatrix aMatrix = approxKoef(list,polynomRange);
        List<Double> approxSignal = new ArrayList<>();
        for(int i = 0; i < list.size();i++){
            for (int j = 0; j < 10; j++) {
                approxSignal.add(polynome(i+j/10., aMatrix));
            }
        }
        return approxSignal;
    }
    public List<Double> getApproxSignal(List<Double> list, List<Double> args, int polynomRange){
        SimpleMatrix aMatrix = approxKoef(list,polynomRange);
        List<Double> approxSignal = new ArrayList<>();
        args.forEach(e-> approxSignal.add(polynome(e, aMatrix)));
        return approxSignal;
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

    public static void main(String[] args) {
        List<Double> list1 = Arrays.asList(0.,  0.,  0.,  0.,  3.6,  5.4,  5.4,  7.2,  7.2);
        List<Double> list2 = Arrays.asList(1.77,3.54,3.54,3.54,5.31, 7.08, 10.62,12.39,15.93);
        List<Double> list3 = Arrays.asList(0.,  1.75,1.75,3.5, 7.,   8.75, 10.5, 12.25,14.2);
        List<Double> list4 = Arrays.asList(-1.75,-1.75, 0., 1.775, 3.5,5.25,8.75, 10.5,12.25);
        List<Double> list5 = Arrays.asList(1.71, 3.42,5.13,6.84,8.55,10.26,11.97,11.97,13.68);

        List<Double> listOfArgs = new ArrayList<>();
        List<Double> listOfApproxArgs = new ArrayList<>();
//        int i = 0;
//        for (Double ignored : list1) {
//            for(int j = 0; j < 10; j++){
//                if(i!= list1.size() -1){
//                    listOfApproxArgs.add((double) i+j/10.0);
//                }
//            }
//            listOfArgs.add((double) i++);
//        }
        for (int k = 0; k < list1.size(); k++) {
            listOfApproxArgs.add((double) k);
            listOfArgs.add((double) k);
        }
        PolynomialApproximator approximator = new PolynomialApproximator();
//        approximator.approxKoef(list1, 7);
//        System.out.println(approximator.getApproxSignal(list1, 7).toString());
        System.out.println("Signal 1");
        System.out.println(approximator.getApproxSignal(list1, listOfApproxArgs, 5).toString());
//        SignalViewCreator.createSignalView(
//                Arrays.asList(list1, approximator.getApproxSignal(list1, listOfApproxArgs, 3), approximator.getApproxSignal(list1, listOfApproxArgs, 4), approximator.getApproxSignal(list1, listOfApproxArgs, 5)),
//                Arrays.asList(listOfArgs, listOfApproxArgs, listOfApproxArgs, listOfApproxArgs));


        System.out.println("Signal 2");
        System.out.println(approximator.getApproxSignal(list2, listOfApproxArgs, 4).toString());
//        SignalViewCreator.createSignalView(
//                Arrays.asList(list2, approximator.getApproxSignal(list2, listOfApproxArgs, 3),approximator.getApproxSignal(list2, listOfApproxArgs, 4),approximator.getApproxSignal(list2, listOfApproxArgs, 5)),
//                Arrays.asList(listOfArgs, listOfApproxArgs, listOfApproxArgs, listOfApproxArgs));

        System.out.println("Signal 3");
        System.out.println(approximator.getApproxSignal(list3, listOfApproxArgs, 5).toString());
//        SignalViewCreator.createSignalView(
//                Arrays.asList(list3, approximator.getApproxSignal(list3, listOfApproxArgs, 3),approximator.getApproxSignal(list3, listOfApproxArgs, 4),approximator.getApproxSignal(list3, listOfApproxArgs, 5)),
//                Arrays.asList(listOfArgs, listOfApproxArgs, listOfApproxArgs, listOfApproxArgs));

        System.out.println("Signal 4");
        System.out.println(approximator.getApproxSignal(list4, listOfApproxArgs, 3).toString());
//        SignalViewCreator.createSignalView(
//                Arrays.asList(list4, approximator.getApproxSignal(list4, listOfApproxArgs, 3),approximator.getApproxSignal(list4, listOfApproxArgs, 4),approximator.getApproxSignal(list4, listOfApproxArgs, 5)),
//                Arrays.asList(listOfArgs, listOfApproxArgs, listOfApproxArgs, listOfApproxArgs));

        System.out.println("Signal 5");
        System.out.println(approximator.getApproxSignal(list5, listOfApproxArgs, 5).toString());
//        SignalViewCreator.createSignalView(
//                Arrays.asList(list5, approximator.getApproxSignal(list5, listOfApproxArgs, 3),approximator.getApproxSignal(list5, listOfApproxArgs, 4),approximator.getApproxSignal(list5, listOfApproxArgs, 5)),
//                Arrays.asList(listOfArgs, listOfApproxArgs, listOfApproxArgs, listOfApproxArgs));
    }
}

