package com.tihiy.comm.serial;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class DefaultSimpleSignalManager extends AbstractSignalManager{
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
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
//        Collections.addAll(queue, samples);
        log.info("Protocol not supported");
    }

    @Override
    public <T> void getSamples(List<T> samples) {
        if(samples.size()!=0){
            if(samples.get(0) instanceof Integer){
                for(Object i: samples){
                    Integer integer = (Integer)i;
                    queue.add(integer);
                    System.out.println(""+integer);
                }
            }
        }
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
