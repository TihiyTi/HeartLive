package com.tihiy.ecg.morph.handl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 01.10.12
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class HighPassFilter implements Handler {
    private List<Float> startData;
    private List<Float> finishData = new ArrayList<Float>();

    public void toDoOperation(){
        int size = startData.size();
        int last = size - 1;
        float a = 0;
        if(last >= 0){
//            a = startData.get(last)/ 32;
            if(last >= 1){
//                a += 2 * startData.get(last - 1);
                if(last >= 2){
//                    a -= startData.get(last - 2);
                    if(last >= 6){
//                        a -= startData.get(last - 6) / 16;
                        if(last >= 32){
//                            a += 2 * startData.get(last - 12) / 32;
                        }
                    }
                }
            }
        }
        finishData.add(a);
    }

    @Override
    public List<Float> getData() {
        return finishData;
    }

    @Override
    public void setData(List<Float> data) {
        startData = data;
    }
}
