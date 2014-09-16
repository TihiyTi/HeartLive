import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignalProccesor {
    public static List<Double> invert(List<Double> list){
        List<Double> result = new ArrayList<>();
        for (Double aDouble : list) {
            result.add(-aDouble);
        }
        return  result;
    }

    public static List<Double> makeSignalUnipolar(List<Double> list){
        List<Double> result = new ArrayList<>();
        double min = Collections.min(list);
        list.forEach(e-> result.add(e - min));
        return result;
    }
}
