import java.util.ArrayList;
import java.util.List;

import static settings.SistolaInterval.*;

public class MySpesificCorrelation {
    private List<Double> multyPeriodicSignal;
    private List<List<Double>> miniLists;
    private List<Double> movingSignal;
    private List<Double> movingApproxSignal;
    private Correlation cor;

    public MySpesificCorrelation(){

    }

    public MySpesificCorrelation(List<Double> multyPeriodicSignal, List<Double> movingSignal, List<Double> movingApproxSignal) {
        this.multyPeriodicSignal = multyPeriodicSignal;
        this.movingSignal = movingSignal;
        this.movingApproxSignal = movingApproxSignal;
        miniLists = getMiniSignal();
    }

    public List<Double> getImpedMoveCorrelation(){
        List<Double> listOfCorrelation = new ArrayList<>();
        List<List<Double>> miniList = getMiniSignal();
        return null;
    }

    public List<Double> getImpexApproxMoveCorrelation(){
        List<Double> listOfCorrelation = new ArrayList<>();
        List<List<Double>> miniList = getMiniSignal();
        miniList.forEach(e -> listOfCorrelation.add(cor.correlation(e,movingApproxSignal)));
        return listOfCorrelation;
    }

    public List<List<Double>> exportMiniLists(){
        return miniLists;
    }

    private List<List<Double>> getMiniSignal() {
        List<List<Double>> miniLists = new ArrayList<>();
        for(int i = 1; i <= getNumOfInterval(); i++){
            miniLists.add(multyPeriodicSignal.subList(getPoint(i, BEGIN), getPoint(i, END)));
        }
        return miniLists;
    }


}
