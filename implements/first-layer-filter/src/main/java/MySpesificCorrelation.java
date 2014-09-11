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
        int size = movingSignal.size();
        miniLists.forEach(e->{
            List<Double> miniListCut = new ArrayList<>();
            for (int i = 0; i < size - 1; i++) {
                double newMiniListCutElement = 1.*e.size()/(size -1)*i;
                miniListCut.add(newMiniListCutElement);
            }
            double corElement = cor.correlation(miniListCut, movingSignal);
            listOfCorrelation.add(corElement);
        });
        return listOfCorrelation;
    }

    public List<Double> getImpexApproxMoveCorrelation(){
        List<Double> listOfCorrelation = new ArrayList<>();
        miniLists.forEach(e -> listOfCorrelation.add(cor.correlation(e,movingApproxSignal)));
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
