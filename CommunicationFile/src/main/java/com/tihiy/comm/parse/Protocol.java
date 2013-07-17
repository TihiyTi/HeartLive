package com.tihiy.comm.parse;

import com.tihiy.comm.sig.SignalInterface;

import java.nio.ByteBuffer;

public enum Protocol {
    Reo32(){
        @Override
        void parse(SignalInterface signal, ByteBuffer byteBuffer){
            while(byteBuffer.remaining() >= 100){
                byte[] array = new byte[100];
                byteBuffer.get(array);
                for(int numChannel = 0; numChannel < 32; numChannel++){
                    double base;
                    double pulse;
                    base = array[4 + 3*numChannel]<<12 + array[4 + 3*numChannel + 1]<<4;
                    pulse = array[4 + 3*numChannel+1]<<16 + array[4 + 3*numChannel + 2]<<8;
                    signal.getChannel("" + numChannel + "BASE").add(base);
                    signal.getChannel("" + numChannel + "PULSE").add(pulse);
                }
            }
        }
    };

    abstract void parse(SignalInterface signal, ByteBuffer byteBuffer);
}
