import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.util.List;

public class Correlation {

    public double correlation(List<Double> aList, List<Double> bList){
        PearsonsCorrelation correlation = new PearsonsCorrelation();
        double[] a = new double[aList.size()];
        double[] b = new double[bList.size()];
        for (int i = 0; i < aList.size(); i++) {
            a[i] = aList.get(i);
            b[i] = bList.get(i);
        }
        return correlation.correlation(a,b);
    }
//    public List<Double> correlationVector(List<Double> aList, List<Double> bList){
//        PearsonsCorrelation correlation = new PearsonsCorrelation();
//        double[] a = new double[aList.size()];
//        double[] b = new double[bList.size()];
//        for (int i = 0; i < aList.size(); i++) {
//            a[i] = aList.get(i);
//            b[i] = bList.get(i);
//        }
//        correlation.
//    }

    @Deprecated
    public double[] correlation(double[] a, double[] b){
        double[] result =  new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = covar(a,b,i)/(Math.sqrt(meanValue(a)*meanValue(b)));
        }
        return result;
    }
    @Deprecated
    public double koeffCorrellation(double[] a, double[] b){
        double[] result = correlation(a,b);
        double max = Double.MIN_VALUE;
        for (double aResult : result) {
            if (max < aResult) {
                max = aResult;
            }
        }
        return max;
    }
    @Deprecated
    public double meanValue(double[] x){
        double sum = 0;
        for (double aX : x) {
            sum += (aX * aX);
        }
        return sum/x.length;
    }
    @Deprecated
    public double covar(double[] a, double[] b, int shift){
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            double second;
            if(i - shift >= 0){
                second = b[i - shift];
            }else{
                second = 0;
            }
            sum += a[i]*second;
        }
        return sum/a.length;
    }
}
