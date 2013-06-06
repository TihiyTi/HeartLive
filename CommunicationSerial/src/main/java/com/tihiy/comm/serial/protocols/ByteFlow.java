package com.tihiy.comm.serial.protocols;

import com.tihiy.comm.serial.SignalReturn;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 29.05.13
 * Time: 18:54
 * To change this template use File | Settings | File Templates.
 */
public class ByteFlow implements ProtocolParser {
//    @Override
//    public List<List<Integer>> getFormattedData(PushbackInputStream stream) {
//        List<Integer> list = new ArrayList<>();
//        try {
//            int n = stream.available();
//            byte[] buf = new byte[n];
//            stream.read(buf);
//            for(byte b: buf){
//                list.add((int)b);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        List<List<Integer>> returnValue = new ArrayList<>();
//        returnValue.add(list);
//        return returnValue;
//    }

    @Override
    public void sendFormattedData(PushbackInputStream stream, SignalReturn manager, String flowName){
        try {
            int n = stream.available();
            byte[] buf = new byte[n];
            stream.read(buf);
            manager.getSamples(buf ,flowName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getNumber() {
        return 0;
    }
}
