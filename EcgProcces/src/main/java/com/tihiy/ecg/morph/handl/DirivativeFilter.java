package com.tihiy.ecg.morph.handl;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 11.10.12
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class DirivativeFilter extends Handler{

    @Override
    public void toDoOperation() {
        int size = startData.size();
        int last =  size - 1;
        float a = 0;
        if(last >= 0){
            a += (2*startData.get(last));
            if(last >= 1){
                a += startData.get(last - 1);
                if(last >= 3){
                    a -= startData.get(last - 3);
                    if(last >= 4){
                        a -= 2*startData.get(last - 4);
                    }
                }
            }
        }
        a = a/10;
        finishData.add(a);
    }
}
