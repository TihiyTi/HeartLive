package com.tihiy.rclint.models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

// NOT COMPLETE
public class SignalSynchroData<T extends Number> {
    private Logger log = Logger.getAnonymousLogger();
    private ArrayDeque<T> list = new ArrayDeque<>();
    private double[] array;

    private int currentScale = 0;
    private int currentSize = 0;
    private int absolutePointer = 0;
    private int pointer;

    public synchronized int addElements(ArrayList<T> buffer){
        list.addAll(buffer);
        absolutePointer += buffer.size();
        if(array!=null){
            addToArray(buffer);
        }
        return absolutePointer;
    }

    public synchronized double[] getData(){
        if(array == null){
            log.info("Array == null");
            return null;
        }
        return copyArray();
    }

    public synchronized double[] getData(int scale, int length){
        if(length == 0){
            return null;
        }
        if((scale == currentScale)&(length == currentSize)){
            return copyArray();
        }
        array = remakeArray(scale, length);
        return copyArray();
    }

    public double[] remakeArray(int scale, int size){
        currentSize = size;
        currentScale = scale;
        if(pointer >= currentSize){
            pointer = currentSize - 1;
        }
        int step = Math.round(Math.scalb(1, currentScale));
        double[] buffer = new double[currentSize];
        Iterator iter = list.descendingIterator();
        if(step >= 1){
            int index = pointer;
            for(int i = 0; i < currentSize; i++){
                if((index = index-1)<0){
                    index = currentSize - 1;
                }
                buffer[index] = (double) iter.next();
                if(!iter.hasNext()){
                    break;
                }
                boolean breakable = false;
                for(int j = 1; j < step; j++){
                    if(iter.hasNext()){
                        iter.next();
                    }else{
                        breakable = true;
                        break;
                    }
                }
                if(breakable){
                    break;
                }
            }
        }else{
            Logger.getAnonymousLogger().info("Minus scale not supported!");
        }
        return buffer;
    }

    private void addToArray(ArrayList<T> bufList){
        int size = bufList.size();
        double[] buffer = new double[size];
        for(int i = 0; i < size; i++){
            buffer[i] = bufList.get(i).doubleValue();
        }
        if(currentSize > buffer.length){
            if(buffer.length <= (currentSize - pointer)){
                System.arraycopy(buffer, 0, array, pointer, buffer.length);
                addPointer(buffer.length);
            }else{
                System.arraycopy(buffer, 0, array, pointer, currentSize - pointer);
                System.arraycopy(buffer, currentSize - pointer, array, 0, buffer.length - (currentSize - pointer));
                addPointer(buffer.length);
//                secondRound = true;
            }
        }else{
            log.info("Unsupported operation: buffer.length > array.length");
        }
    }

    private void addPointer(int add){
        pointer += add;
        pointer%= currentSize;
    }

    private double[] copyArray(){
        double[] buffer = new double[currentSize];
        System.arraycopy(array,0,buffer,0,currentSize);
        return buffer;
    }

}
