package com.tihiy.comm.serial;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class DefaultSimpleSignalManager extends AbstractSignalManager<Integer> {
    Queue<Integer> queue = new LinkedBlockingQueue<>();
    private final Logger log = Logger.getLogger(getClass().getName());

    public Queue<Integer> getQueue() {
        return queue;
    }

    @Override
    public void getSamples(List<Integer> samples) {
        queue.addAll(samples);
    }

}
