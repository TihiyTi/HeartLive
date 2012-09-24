package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReader;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class SerialSignalReader implements SignalReader {
    private BlockingQueue<Float> inputData =  new LinkedBlockingQueue<Float>();

    public SerialSignalReader() {
    }

    @Override
    public BlockingQueue getData(File file) {

        return inputData;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
