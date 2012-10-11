package com.tihiy.ecg.morph.handl;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 11.10.12
 * Time: 17:49
 * To change this template use File | Settings | File Templates.
 */
public class Squaring extends Handler {
    @Override
    public void toDoOperation(){
        int last = startData.size() - 1;
        finishData.add((float)Math.sqrt(startData.get(last)));
    }
}
