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
    private final LinkedList<T> list =  new LinkedList<>();
    private CircleArrayLite array;

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

    // todo сменить на package protected
    public void setArraySize(int size){
        array = new CircleArrayLite(size);

//        double[] newArray = new double[size];
//        synchronized (list){
//            Iterator<T> it = list.descendingIterator();
//            int i;
//            if(size >= list.size()){
//                i = size - 1;
//            }else{
//                i = list.size();
//            }
//            while((i >= 0) & it.hasNext()){
//                newArray[i] = it.next().doubleValue();
//                i--;
//            }
//        }
//        firePropertyChange(signalName, new double[0], signalArray);
    }

    public void scaleArray(int scale){
        synchronized (list){
            Logger.getAnonymousLogger().info("Scale not supported yet!");
        }
    }

    private void startQueueProcessor(){

        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if ((queue != null) & (array != null)) {
                    ArrayList<T> bufList = new ArrayList<>();
                    while (!queue.isEmpty()) {
                        bufList.add(queue.poll());
                    }
                    double[] buffer = new double[bufList.size()];
                    for (int i = 0; i < bufList.size(); i++) {
                        buffer[i] = bufList.get(i).doubleValue();
                        System.out.print(""+buffer[i]+",");
                    }
                    System.out.println();
                    array.addArray(buffer);
                    firePropertyChange(signalName, new double[0], array.copyArray());
                }
            }
        }, 0L, 40L, TimeUnit.MILLISECONDS);
    }
}
