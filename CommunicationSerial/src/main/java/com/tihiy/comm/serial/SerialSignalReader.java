package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReader;
import gnu.io.NRSerialPort;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
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

    @Override
    public List getAllData(File file) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean isReadComplete() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void nothings() throws IOException {
    //This is how to use NRSerialPort objects
    NRSerialPort serial = new NRSerialPort("COM3", 115200);
    serial.connect();
    DataInputStream ins = new DataInputStream(serial.getInputStream());
    DataOutputStream outs = new DataOutputStream(serial.getOutputStream());
    byte b = (byte)ins.read();
    outs.write(b);
    serial.disconnect();
    }

}
