import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.List;

public class MyCrossCorrelation {
    private List<List<List<Double>>> approxMoveChannelInterval;
    private List<List<List<Double>>> impedanceChannelInterval;
    public boolean removeTrends = false;

    public MyCrossCorrelation(List<List<List<Double>>> approxMoveChannelInterval, List<List<List<Double>>> impedanceChannelInterval) {
        this.approxMoveChannelInterval = approxMoveChannelInterval;
        this.impedanceChannelInterval = impedanceChannelInterval;
    }

    public List<Double> getCorrelationMoveWithImpedanceS(int channel){
        List<Double> correlations = new ArrayList<>();
        if(!removeTrends){
            List<List<Double>> approxMove = approxMoveChannelInterval.get(channel);
            List<Double> moveFirstInterval = approxMove.get(0);
            Correlation cor = new Correlation();
            impedanceChannelInterval.forEach(e -> {
                List<Double> impedanceFirstInterval = e.get(0);
                correlations.add(cor.correlation(moveFirstInterval,impedanceFirstInterval));
            });
        }
        return correlations;
    }
}
