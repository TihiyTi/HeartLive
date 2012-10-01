package com.tihiy.ecg.morph;

import com.tihiy.ecg.morph.handl.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 28.09.12
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class RFinder  {
    private ArrayList<Handler> handlers = new ArrayList<>();
    private List data;

    public RFinder(List data) {
        this.data = data;
    }

    public void runPanTomkins(){
        for (Handler handler : handlers) {

        }

    }
    public void stopPanTomkins(){

    }

    public void addHandler(Handler hand){
        handlers.add(hand);
        int size = handlers.size();
        int last = size - 1;
        if(size == 1){
            handlers.get(0).setData(data);
        }else{
            handlers.get(last).setData(handlers.get(last-1).getData());
        }
//        Last handler don't return data to the world!!!
    }

    public void removeHandler(Handler hand){
        // Don't realize yet!
    }
}