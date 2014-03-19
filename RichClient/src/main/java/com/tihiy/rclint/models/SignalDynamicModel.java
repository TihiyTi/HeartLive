package com.tihiy.rclint.models;

import com.tihiy.rclint.mvcAbstract.DynamicModelInterface;
import org.hamcrest.internal.ArrayIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class SignalDynamicModel<T extends Number> extends SignalModelLite implements DynamicModelInterface{
    private BlockingQueue<T> queue;
    private SignalSynchroData<T> data = new SignalSynchroData<>();

    public SignalDynamicModel(String signalName) {
        super(signalName);
        startQueueProcessor();
    }

    @Override
    public void setBufferQueue(BlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public void setArray(double[] array){
        Logger.getAnonymousLogger().info("Don't support in dynamic model");
    }

    public void setScaleSize(int scale, int size){
        firePropertyChange(signalName, new double[0], data.getData(scale, size));
    }

    private void startQueueProcessor(){
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if (queue != null) {
                    ArrayList<T> bufList = new ArrayList<>();
                    while (!queue.isEmpty()) {
                        bufList.add(queue.poll());
                    }
                    data.addElements(bufList);
                    firePropertyChange(signalName, new double[0], data.getData());
                }
            }
        }, 0L, 40L, TimeUnit.MILLISECONDS);
    }
}
