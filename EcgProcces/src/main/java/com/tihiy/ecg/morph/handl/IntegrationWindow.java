package com.tihiy.ecg.morph.handl;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 11.10.12
 * Time: 18:21
 * To change this template use File | Settings | File Templates.
 */
public class IntegrationWindow extends Handler {
    private int windowWight;
    @Override
    public void toDoOperation(){
        int size = startData.size();
        int last = size - 1;
        int curWindowWight;
        if(size < windowWight){
            curWindowWight = size;
        }else{
            curWindowWight = windowWight;
        }
        float sum = 0;
        for (int i = 0; i < curWindowWight; i++) {
            sum += startData.get(last - i);
        }
        finishData.add(sum/curWindowWight);
    }

    public void setWindowWight(int wight){
        windowWight = wight;
    }
}
