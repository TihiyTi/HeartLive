package com.tihiy.rclint.models;

import com.tihiy.rclint.mvcAbstract.AbstractModel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SignalDynamicModelInteger extends AbstractModel {
    private String signalName;
    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public SignalDynamicModelInteger(String name){
        this.signalName = name;
        queue.add(0);
    }

    public BlockingQueue<Integer> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Integer> queue) {
        firePropertyChange(signalName, this.queue, queue);
        this.queue = queue;
    }


}
