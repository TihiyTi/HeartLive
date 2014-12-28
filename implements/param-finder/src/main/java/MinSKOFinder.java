import com.tihiy.reonew.SphereCalc;
import com.tihiy.reonew.SphereModelParam;

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



    public void getParamWithMinSKO(List<Double> experimentalList){
        for(int h = bH; h < eH; h++){
            for(int r = bR; r < eR; r++){
                for(double ro = bRo; ro < eRo; ro = ro + 0.1){
                    for(int y = bY; y < eY; y++){
                        getSKO(experimentalList, h,r,ro,y);
                    }
                }
            }
        }
    }

    private void getSKO(List<Double> experimentalList, int h, int r, double ro, int y) {
        SphereModelParam param = new SphereModelParam(ro, 1.35, 0.05, 0.025, r, h, 0, y);
        SphereCalc calc = new SphereCalc(param);
        calc.getMeasurementFullImp();
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
}
