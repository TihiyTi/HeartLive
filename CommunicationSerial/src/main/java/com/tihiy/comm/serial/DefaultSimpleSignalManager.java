package com.tihiy.comm.serial;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class DefaultSimpleSignalManager extends AbstractSignalManager implements SignalReturn{
    BlockingQueue queue;
    private Logger log = Logger.getLogger(this.getClass().getName());

    public BlockingQueue getQueue(){
        return queue;
    }

    @Override
    public void getSamples(double[] samples, String flowName) {
        log.info("Protocol not supported");
    }

    @Override
    public void getSamples(byte[] samples, String flowName) {
        Collections.addAll(queue, samples);
    }

    @Override
    public void getSamples(List<List<Integer>> samples) {
        log.info("Protocol not supported");
    }

    @Override
    public void createSignal(String flowName) {
        if (queue==null){
            queue = new LinkedBlockingQueue();
        }else{
            log.info("Signal already exist");
        }
    }
}
