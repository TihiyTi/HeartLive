package com.tihiy.rclint.models;

import java.util.logging.Logger;

public class CircleArrayLite {
    private Logger log = Logger.getAnonymousLogger();
    private double[] array;
    private int fullSize = 0;
    private int fullPointer = 0;

    private int size = 0;
    private int pointer = 0;
    private boolean secondRound = false;

    public CircleArrayLite(int size){
        this.size = size;
        fullSize = 2*size;
        array = new double[fullSize];
    }

    public void addArray(double[] buffer){
        if(fullSize > buffer.length){
            if(buffer.length <= (fullSize - fullPointer)){
                System.arraycopy(buffer, 0, array, fullPointer, buffer.length);
                addPointer(buffer.length);
            }else{
                System.arraycopy(buffer, 0, array, fullPointer, fullSize - fullPointer);
                System.arraycopy(buffer, fullSize - fullPointer, array, 0, buffer.length - (fullSize - fullPointer));
                addPointer(buffer.length);
                secondRound = true;
            }
        }else{
            log.info("Unsupported operation: buffer.length > array.length");
        }
    }
    public double[] copyArray(){
        double[] newArray = new double[size];
        if(fullPointer >= size){
            System.arraycopy(array, fullPointer - pointer, newArray, 0, pointer);
            System.arraycopy(array, fullPointer - size, newArray, pointer, size - pointer);
        }else{
            if(fullPointer >= pointer){
                System.arraycopy(array, fullPointer - pointer, newArray, 0, pointer);
                System.arraycopy(array, 0, newArray, size - (fullPointer - pointer), fullPointer - pointer);
                if(secondRound){
                    System.arraycopy(array, fullSize - (size - fullPointer), newArray, pointer, size - fullPointer);
                }
            }else{
                System.arraycopy(array, 0, newArray, pointer - fullPointer, fullPointer);
                System.arraycopy(array, fullSize - (pointer - fullPointer), newArray, 0, pointer - fullPointer);
                System.arraycopy(array, fullSize - (size - fullPointer), newArray, pointer, size - pointer);
            }
        }
        return newArray;
    }

    private void incPointer(){
        fullPointer++;
        fullPointer %= fullSize;
        pointer++;
        pointer%= size;
    }
    private void addPointer(int add){
        fullPointer += add;
        fullPointer %= fullSize;
        pointer += add;
        pointer%= size;
    }
}
