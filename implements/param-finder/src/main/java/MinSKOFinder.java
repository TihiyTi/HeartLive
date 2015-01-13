import com.tihiy.reonew.SphereCalc;
import com.tihiy.reonew.SphereModelParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinSKOFinder {
    private int bR = 0;
    private int eR = 0;
    private int bH = 0;
    private int eH = 0;
    private double bRo = 0;
    private double eRo = 0;
    private int bY = 0;
    private int eY = 0;

    private int minR = 0;
    private int minH = 0;
    private double minRo = 0;
    private int minY = 0;

    public static boolean DEBUG = false;

    public void getParamWithMinSKO(List<Double> experimentalList){
        Double minSKO = Double.MAX_VALUE;
        for(int h = bH; h < eH; h++){
            for(int r = bR; r < eR; r++){
                for(double ro = bRo; ro < eRo; ro = ro + 0.1){
                    for(int y = bY; y < eY; y++){
                        List<Double> modelList = getModelData(experimentalList.size(), h, r, ro, y);
                        Double sko = SKO(experimentalList, modelList);
                        if(DEBUG){
                            System.out.println(modelList.toString());
                            System.out.println(experimentalList.toString());
                            System.out.println("              SKO = "+ sko);
                        }
                        if(sko < minSKO){
                            minSKO = sko;
                            minH = h;
                            minR =r;
                            minRo = ro;
                            minY = y;
                        }
                    }
                }
            }
        }
    }

    public List<Double> getModelData(int expSize, int h, int r, double ro, int y) {
        List<Double> modelList = new ArrayList<>();
        SphereModelParam param = new SphereModelParam(ro, 1.35, 0.05, 0.025, r/1000., h/1000., 0, y/1000.);
        SphereCalc calc = new SphereCalc(param);
        for (int i = 0; i < expSize; i++) {
            param.setYShift(y/1000. + 0.005*i);
            modelList.add(calc.getMeasurementFullImp());
        }
        if (DEBUG){
            System.out.println("Ro = " + ro);
//            System.out.println(modelList.toString());
        }
        return modelList;
    }

    public Double SKO(List<Double> list1, List<Double> list2){
        Double summa = (double) 0;
        for (int i = 0; i < list1.size(); i++) {
            Double delta = list1.get(i) - list2.get(i);
            summa = summa + delta*delta;
        }
        return summa/list1.size();
    }

    public void setRInterval(int beginR, int endR){
        bR = beginR;
        eR = endR;
    }
    public void setHInterval(int beginH, int endH){
        bH = beginH;
        eH = endH;
    }
    public void setRoInterval(int beginRo, int endRo){
        bRo = beginRo;
        eRo = endRo;
    }
    public void setYInterval(int beginY, int endY){
        bY = beginY;
        eY = endY;
    }
    public void printResult(){
        System.out.println("Min R = " + minR);
        System.out.println("Min H = " + minH);
        System.out.println("Min ro = " + minRo);
        System.out.println("Min Y = " + minY);
    }

    public static void main(String[] args) {
        MinSKOFinder finder = new MinSKOFinder();
//        DEBUG = true;
        finder.bR = 20; finder.eR = 60;
        finder.bH = 5; finder.eH = 40;
        finder.bY = 0; finder.eY = 40;
        finder.bRo = 4; finder.eRo = 10;
//        List<Double> expList = Arrays.asList(38.335, 38.492, 38.716, 38.999);
        List<Double> expList = Arrays.asList(28.,30.,31.3,33.,34.6,35.3,35.,34.6,34.9,33.7,36.,36.6);
        finder.getParamWithMinSKO(expList);
        finder.printResult();

//        finder.getModelData(5, 20, 42, 5, 35);
    }
}
