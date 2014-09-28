import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.util.ArrayList;
import java.util.List;

public class Correlation {
    /*
    Реализует приведение List<Double>  к double[] используемому в библиотеке Apache
     */
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
    public double correlationWithoutTrends(List<Double> aList, List<Double> bList){
        return correlation(removeTrend(aList), removeTrend(bList));
    }

    private List<Double> removeTrend(List<Double> list){
        List<Double> clear = new ArrayList<>();
        PolynomialApproximator approximator = new PolynomialApproximator();
        List<Double> listNoTrends = approximator.getApproxSignal(list,1);
        for (int i = 0; i < list.size(); i++) {
            clear.add(list.get(i) - listNoTrends.get(i));
        }
        return clear;
    }
}
