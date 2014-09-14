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
    private int polynomeRange;

    public MySpesificCorrelation(List<Double> moveList, List<Double> impedance, int polynomeRange) {
        this.moveList = moveList;
        this.impedance = impedance;
        this.polynomeRange = polynomeRange;
        shortImpedances = getMiniSignal();
        cutShortImpedances = getCutShortImpedances();
    }

    public List<Double> getCorrel(){
        List<Double> values = new ArrayList<>();
        Correlation cor = new Correlation();
        cutShortImpedances.forEach(e-> values.add(cor.correlation(moveList,e)));
        return values;
    }


    public JPanel getSignal(){
        List<List<Double>> listOfSignal = new ArrayList<>();
        listOfSignal.add(moveList);
        listOfSignal.addAll(cutShortImpedances);
        List<String> listOfNames = new ArrayList<>();
        listOfNames.add("MoveFromMRI");
        listOfNames.addAll(Arrays.asList("1","2","3","4","5","6","7","8","9","10"));
        return new SignalJFreePanel(listOfSignal, listOfNames);
    }
    public void getSignalInFrame(){
        JFrame frame = new JFrame("Signal");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 500));
        frame.getContentPane().add(getSignal());
        WindowUtils.centerOnScreenAndSetVisible(frame);
    }

    private List<List<Double>> getMiniSignal() {
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
}
