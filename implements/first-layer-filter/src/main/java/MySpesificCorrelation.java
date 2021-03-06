import com.tihiy.WindowUtils;
import com.tihiy.jfreeclient.SignalJFreePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static settings.SistolaInterval.*;

public class MySpesificCorrelation {
    private List<Double> moveList;
    private List<Double> impedance;
    private List<List<Double>> shortImpedances;
    private List<List<Double>> cutShortImpedances;
    private List<List<Double>> approxMovies;
    private int polynomeRange;
    public boolean removeTrends = false;

    public MySpesificCorrelation(List<Double> moveList, List<Double> impedance, int polynomeRange) {
        this.moveList = moveList;
        this.impedance = impedance;
        this.polynomeRange = polynomeRange;
        shortImpedances = getMiniSignal();
        cutShortImpedances = getCutShortImpedances();
        approxMovies = getApproxMovies();
    }

    public List<Double> getCorrel(){
        List<Double> values = new ArrayList<>();
        Correlation cor = new Correlation();
        if(removeTrends){
            cutShortImpedances.forEach(e-> values.add(cor.correlationWithoutTrends(moveList,e)));
        }else {
            cutShortImpedances.forEach(e-> values.add(cor.correlation(moveList,e)));
        }
        return values;
    }
    public List<Double> getCorrelAprox(){
        List<Double> values =  new ArrayList<>();
        Correlation cor = new Correlation();
        for (int i = 0; i < shortImpedances.size(); i++) {
            if(removeTrends){
                values.add(cor.correlationWithoutTrends(shortImpedances.get(i), approxMovies.get(i)));
            }else {
                values.add(cor.correlation(shortImpedances.get(i), approxMovies.get(i)));
            }

        }
        return values;
    }

    public JPanel getSignal(){
        return getPanelWithSignal(moveList, "MoveFromMRI", cutShortImpedances);
    }
    public JPanel getApproxSignal(){
        return getPanelWithSignal(approxMovies.get(0),"ApproxMove",shortImpedances);
    }

    private JPanel getPanelWithSignal(List<Double> oneList, String oneName , List<List<Double>> multyList) {
        List<List<Double>> listOfSignal = new ArrayList<>();
        listOfSignal.add(oneList);
        listOfSignal.addAll(multyList);
        List<String> listOfNames = new ArrayList<>();
        listOfNames.add(oneName);
        int i = 0;
        multyList.forEach(e -> {
            listOfNames.add("" + (multyList.indexOf(e) + 1));
        });
//        listOfNames.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        List<Double> listOfScales = Arrays.asList(0.05,1.,1.,1.,1.,1.,1.,1.,1.,1.,1.);
        SignalJFreePanel signalJFreePanel = new SignalJFreePanel(listOfSignal, listOfNames, true, listOfScales);
        return signalJFreePanel;
    }

    public void getSignalInFrame(boolean isApproxSignal){
        getSignalInFrame(isApproxSignal, "");
    }
    public void getSignalInFrame(boolean isApproxSignal, String frameName){
        JFrame frame = new JFrame("Signal");
        frame.setTitle(frameName);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(800, 500));
        if(isApproxSignal){
            frame.getContentPane().add(getApproxSignal());
        }else {
            frame.getContentPane().add(getSignal());
        }
        WindowUtils.centerOnScreenAndSetVisible(frame);
    }
    public List<List<Double>> getMiniSignal() {
        List<List<Double>> miniLists = new ArrayList<>();
        for(int i = 1; i <= getNumOfInterval(); i++){
            miniLists.add(impedance.subList(getPoint(i, BEGIN), getPoint(i, END)));
        }
        return miniLists;
    }
    private List<List<Double>> getCutShortImpedances(){
        List<List<Double>> values = new ArrayList<>();
        shortImpedances.forEach(e->{
            List<Double> impedanceCut = new ArrayList<>();
            for (int i = 0; i < moveList.size() - 1; i++) {
                int index = (int)(1.*i*(e.size()-1)/(moveList.size() - 1));
                impedanceCut.add(e.get(index));
            }
            impedanceCut.add(e.get(e.size()-1));
            values.add(impedanceCut);

        });
        return values;
    }
    public List<List<Double>> getApproxMovies(){
        List<List<Double>> values = new ArrayList<>();
        PolynomialApproximator approx = new PolynomialApproximator();
        shortImpedances.forEach(e->{
            List<Double> approxMove = approx.getApproxSignal(moveList, e.size(), polynomeRange);
            values.add(approxMove);
        });
        return values;
    }
}
