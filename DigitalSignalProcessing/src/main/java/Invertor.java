import java.util.ArrayList;
import java.util.List;

public class Invertor {
    public static List<Double> invert(List<Double> list){
        List<Double> result = new ArrayList<>();
        for (Double aDouble : list) {
            result.add(-aDouble);
        }
        return  result;
    }
}
