package com.tihiy.ecg.morph;

import com.tihiy.ecg.AbstractHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRFinder {
    protected List<AbstractHandler> handlers = new ArrayList<>();
    protected List<Float> data;
    protected int frequency;

    protected AbstractRFinder(List<Float> data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }

    public void addHandler(AbstractHandler hand){
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

    public void removeHandler(AbstractHandler hand){
        // ToDo realize to remove handler
        // Don't realize yet!
    }
}
