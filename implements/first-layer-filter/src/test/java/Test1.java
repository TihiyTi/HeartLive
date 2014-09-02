import com.tihiy.jfreeclient.SignalViewCreator;
import utils.ShortSignalCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test1 {
    static List<Double> list = Arrays.asList(0., 0., 0., 0., 0., 2., 3., 3., 4., 4.);

    public static void main(String[] args) {
        System.out.println(list.toString());
        SignalViewCreator.createSignalView(list,);
    }

    public static List<Double> simpleFilter(List<Double> list, int numOfPoint){
        List<Double> result =  new ArrayList<>();
        for(int i = 0; i < numOfPoint/2; i++){
            result.add((double) 0);
        }
        if(list.size() > numOfPoint){
            for(int i = 0; i < list.size()-numOfPoint; i++){
                double summ = 0;
                for(int j = 0; j < numOfPoint; j++){
                    summ = summ + list.get(i+j);
                }
                result.add(summ/numOfPoint);
            }
        }
        System.out.println(list.size()  + " "+ result.size());
        for(int i = 0; i < numOfPoint/2; i++){
            result.add((double) 0);
        }
        return result;
    }
}

