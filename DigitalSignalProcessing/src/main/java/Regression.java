import com.tihiy.jfreeclient.SignalJFreePanel;

import java.util.Arrays;
import java.util.List;

public class Regression {
    public static boolean viewGraph = false;

    public static List<Double> getLinearRegression(List<Double> list){
        List<Double> regression;
        PolynomialApproximator approximator = new PolynomialApproximator();
        regression = approximator.getApproxSignal(list, 1);
        list.forEach(e -> System.out.printf("%.1f", e));
        regression.forEach(e-> System.out.printf("%.1f", e));
        if(viewGraph){
            viewGraph(list, regression);
        }
        return regression;
    }

    private static void viewGraph(List<Double> signal, List<Double> regression){
        SignalJFreePanel panel = new SignalJFreePanel(Arrays.asList(signal,regression), Arrays.asList("Signal", "Regression"));
        panel.getSignalInFrame(true);
    }

    public static void main(String[] args) {
        List<Double> signal = Arrays.asList(0.,1.,1.,3.,5.,4.,6.);
        Regression.viewGraph = true;
        Regression.getLinearRegression(signal);
    }
}
