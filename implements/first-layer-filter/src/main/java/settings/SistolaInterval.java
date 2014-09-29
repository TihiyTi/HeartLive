package settings;

import java.util.Arrays;
import java.util.List;

public class SistolaInterval {
    public static boolean BEGIN = true;
    public static boolean END= false;
    // See Signal for test
    // Ivan
//    private static List<Integer> listOfIntervals = Arrays.asList(97, 253, 632, 783, 1167, 1322, 1698,1850, 2219,2378, 2734,2892, 3240,3398, 3747,3907, 4250,4404, 4766,4902);
    // Alex
    private static List<Integer> listOfIntervals = Arrays.asList(162, 340, 606, 752, 1040, 1187, 1465, 1654, 1881, 2032, 2286, 2439, 2678, 2858, 3066, 3221, 3453, 3609, 3845, 3991, 4244,4407, 4649, 4807);

    public static int getPoint(int numOfInterval, boolean position){
        if(numOfInterval > listOfIntervals.size()/2){
            System.out.println("Введен некорректный номер интервала");
        }
        if(position){
            return listOfIntervals.get((numOfInterval-1)*2);
        }else{
            return listOfIntervals.get((numOfInterval-1)*2 + 1);
        }
    }
    public static int getNumOfInterval(){
        return listOfIntervals.size()/2;
    }
}