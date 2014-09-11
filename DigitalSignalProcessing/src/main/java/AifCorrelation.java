public class AifCorrelation {
    public double[] correlation(double[] a, double[] b){
        double[] result =  new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = covar(a,b,i)/(Math.sqrt(meanValue(a)*meanValue(b)));
        }
        return result;
    }
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

    public double meanValue(double[] x){
        double sum = 0;
        for (double aX : x) {
            sum += (aX * aX);
        }
        return sum/x.length;
    }
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
