package com.tihiy.rclint.models;

import com.tihiy.rclint.mvcAbstract.DynamicModelInterface;

import java.util.concurrent.*;

public class SignalDynamicModel<T extends Number> extends SignalModel implements DynamicModelInterface{

    private BlockingQueue queue = new LinkedBlockingQueue();

    public SignalDynamicModel(String signalName) {
        super(signalName);
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new QueueProcessor(),0L, 250L, TimeUnit.MILLISECONDS);
    }


    @Override
    public void setQueue(BlockingQueue queue) {
        this.queue = queue;
    }

    class QueueProcessor implements Runnable{
        @Override
        public void run() {
//            queue.
        }
    }
}
