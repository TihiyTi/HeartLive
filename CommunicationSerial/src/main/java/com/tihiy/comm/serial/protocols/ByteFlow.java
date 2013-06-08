package com.tihiy.comm.serial.protocols;

import com.tihiy.comm.serial.SignalManagerInterface;

import java.io.IOException;
import java.io.PushbackInputStream;

public class ByteFlow implements ProtocolParser {

    @Override
    public void sendFormattedData(PushbackInputStream stream, SignalManagerInterface manager, String flowName){
        try {
            int n = stream.available();
            byte[] buf = new byte[n];
            stream.read(buf);
            for(byte bt: buf){
                Integer integer = (int) bt;
                manager.getQueue(flowName).add(integer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getNumber() {
        return 0;
    }
}
