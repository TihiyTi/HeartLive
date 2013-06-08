package com.tihiy.comm.serial;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DefaultSimpleSignalManager extends AbstractSignalManager<Integer> {
    Queue<Integer> queue = new LinkedBlockingQueue<>();
    String currentFlow;

    public  Queue<Integer> getQueue(){
        return queue;
    }

    @Override
    public void createSignal(String flowName){
        super.createSignal(flowName);

    }

    @Override
    public Queue<Integer> getQueue(String flowName) {
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