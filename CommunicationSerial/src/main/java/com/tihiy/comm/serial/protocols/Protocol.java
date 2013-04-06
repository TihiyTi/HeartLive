package com.tihiy.comm.serial.protocols;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.List;

public enum Protocol{
    ByteFlow(), SimpleEcg(3, new byte[]{'1'}, new SimpleEcg());

    private int number;
    private byte[] sinchroBytes;
    private ProtocolParser protocolParser;

    Protocol(int number, byte[] sinchroBytes, ProtocolParser protocolParser){
        this.number = number;
        this.sinchroBytes = sinchroBytes;
        this.protocolParser = protocolParser;
    }
    Protocol(){}

    public boolean isProtocol(PushbackInputStream stream){
        try {
            byte[] buf = new byte[2*number];
            if(stream.available() >= 2*number){
                stream.read(buf);
                int i = 0;
                boolean isSynchro = false;
                while((i < buf.length)||(!isSynchro)){
                    if(buf[i] == sinchroBytes[0]){
                        // Todo re-think how to check protocol
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;
    }

    public List<List<Integer>> getFormattedData(PushbackInputStream bufStream) {
        return protocolParser.getFormattedData(bufStream);
    }
}
