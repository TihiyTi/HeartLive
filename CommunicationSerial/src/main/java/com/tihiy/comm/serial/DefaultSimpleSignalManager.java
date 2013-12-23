package com.tihiy.comm.serial;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DefaultSimpleSignalManager<T extends Number> extends AbstractSignalManager<T> {
    BlockingQueue<T> queue = new LinkedBlockingQueue<>();
    String currentFlow;

    public  BlockingQueue<T> getQueue(){
        return queue;
    }

    @Override
    public void createSignal(String flowName){
        super.createSignal(flowName);

    }

    @Override
    public BlockingQueue<T> getQueue(String flowName) {
        if(currentFlow == null){
            currentFlow = flowName;
            return queue;
        }else{
            if(flowName.equals(currentFlow)){
                return queue;
            }else{
                return null;
            }
        }
    }

}