package com.tihiy.comm.sig;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class  Channel <T extends Number>{
    BlockingQueue<T> queue = new LinkedBlockingQueue<>();

    public void add(T element){
        queue.add(element);
    }
}
