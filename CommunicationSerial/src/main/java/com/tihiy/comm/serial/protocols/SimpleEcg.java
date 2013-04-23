package com.tihiy.comm.serial.protocols;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimpleEcg implements ProtocolParser {
    int numOfBytes = 3;
    byte sinchro = '1';
    @Override
    public List<List<Integer>> getFormattedData(PushbackInputStream stream) {
        List<Integer> list = new ArrayList<>();
        try {
            if(stream.available()>0){
                byte[] buf = new byte[stream.available()];
                int n = stream.read(buf);
                int i = 0;
                if(n > numOfBytes){
                    for(; (buf.length - i) > 3 ; i+=3){
                        if(buf[i]==sinchro){
                            int value = buf[i+2] + (buf[i+1]<<8);
                            list.add(value);
                        }else {
                            Logger.getLogger(getClass().getName()).info("Protocol CRASH");
                        }
                    }
                }
                byte[] unBuf = new byte[buf.length - i];
                System.arraycopy(buf, i , unBuf, 0, buf.length - i);
                stream.unread(unBuf);
            }
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.FINE, String.format("%s %s", toString(), e.getMessage()), e);
//            e.printStackTrace();
        }
        List<List<Integer>> returnValue = new ArrayList<>();
        returnValue.add(list);
        return returnValue;
    }
}
