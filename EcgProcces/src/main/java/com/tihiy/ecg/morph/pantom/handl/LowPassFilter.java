
package com.tihiy.ecg.morph.pantom.handl;

import com.tihiy.ecg.AbstractHandler;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 28.09.12
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 *
 */
//LowPasFilter for algorithm Pan Tompkins
public class LowPassFilter extends AbstractHandler {

    // To Do y = 2*x[i-1]...
    // 2*a - b + (c - 2*d +e)/32

    @Override
    public void toDoOperation(){
        int size = startData.size();
        int last = size - 1;
        float a = 0;
        if(last >= 0){
            a = startData.get(last)/ 32;
            if(last >= 1){
                a += 2 * startData.get(last - 1);
                if(last >= 2){
                    a -= startData.get(last - 2);
                    if(last >= 6){
                        a -= startData.get(last - 6) / 16;
                        if(last >= 32){
                            a += 2 * startData.get(last - 12) / 32;
                        }
                    }
                }
            }
        }
        finishData.add(a);
    }
}
