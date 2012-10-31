package com.tihiy.ecg.morph.pantom.handl;

import com.tihiy.ecg.AbstractHandler;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 11.10.12
 * Time: 17:49
 * To change this template use File | Settings | File Templates.
 */
public class Squaring extends AbstractHandler {
    @Override
    public void toDoOperation(){
        int last = startData.size() - 1;
        finishData.add(startData.get(last)*startData.get(last));
    }
}
