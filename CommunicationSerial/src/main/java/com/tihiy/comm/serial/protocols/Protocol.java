package com.tihiy.comm.serial.protocols;

import com.tihiy.comm.serial.SignalReturn;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.List;

public enum Protocol{
    ByteFlow(), SimpleEcg(3, new byte[]{0x11}, new SimpleEcg());

    private int number;
    private byte[] sinchroBytes;
    private ProtocolParser protocolParser;

    Protocol(int number, byte[] sinchroBytes, ProtocolParser protocolParser){
        this.number = protocolParser.getNumber();
        this.sinchroBytes = sinchroBytes;
        this.protocolParser = protocolParser;
    }
    Protocol(){
        protocolParser = new ByteFlow();
    }

    // Only for single sinchrobyte
    public boolean isProtocol(PushbackInputStream stream){
        if(this != ByteFlow ){
            try {
                byte[] buf = new byte[2*number];
                stream.read(buf);
                for(int i = 0, len = buf.length/2; i < len; i++){
                    if((buf[i] == sinchroBytes[0]) && (buf[i + number] == sinchroBytes[0])){
                        stream.unread(buf, i, len - i);
                        return true;
                    }
                }
                stream.unread(buf);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return false;
    }

//    public List<List<Integer>> getFormattedData(PushbackInputStream bufStream) {
//        return protocolParser.getFormattedData(bufStream);
//    }

    public ProtocolParser getProtocolParser(){
        return protocolParser;
    }

    public int getNumber(){
        return number;
    }
}
