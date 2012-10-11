package com.tihiy.ecg.morph.handl;

/**
 * Created with IntelliJ IDEA.

 * User: aleksey
 * Date: 01.10.12
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class HighPassFilter extends Handler {

    @Override
    public void toDoOperation(){
        int size = startData.size();
        int last = size - 1;
        float a = 0;
        if(last >= 0){
            a -= (startData.get(last) / 32);
            if(last >= 1){
                a += startData.get(last - 1);
                if(last >= 16){
                    a += startData.get(last - 16)/16;
                    if(last >= 17){
                        a -= startData.get(last - 17);
                        if(last >= 32){
                            a += startData.get(last - 32) / 32;
                        }
                    }
                }
            }
        }
        finishData.add(a);
    }
}
