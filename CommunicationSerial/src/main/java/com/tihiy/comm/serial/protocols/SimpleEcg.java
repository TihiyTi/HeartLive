package com.tihiy.comm.serial.protocols;

import com.tihiy.comm.serial.SignalReturn;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimpleEcg implements ProtocolParser {
    int numOfBytes = 3;
    byte sinchro = 0x11;
    @Override
    public List<List<Integer>> getFormattedData(PushbackInputStream stream) {
        List<Integer> list = new ArrayList<>();
        try {
                int numOfPackage = (stream.available()/numOfBytes);
                if(numOfPackage > 0){
                    byte[] buf = new byte[numOfPackage*numOfBytes];
                    int n = stream.read(buf);
                    for(int i = 0; i < buf.length; i+=numOfBytes){
                        if(buf[i]==sinchro){
                            int value = buf[i+2] + (buf[i+1]<<8);
                            list.add(value);
                        }else {
                            stream.unread(buf);
                            Logger.getLogger(getClass().getName()).info("Protocol CRASH");
                        }
                    }

                }
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.FINE, String.format("%s %s", toString(), e.getMessage()), e);
//            e.printStackTrace();
        }
        List<List<Integer>> returnValue = new ArrayList<>();
        returnValue.add(list);
        return returnValue;
    }

    @Override
    public void sendFormattedData(PushbackInputStream stream, SignalReturn manager, String flowName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getNumber() {
        return numOfBytes;
    }
}
