package com.tihiy.comm.parse;

import com.tihiy.comm.sig.Signal;
import com.tihiy.comm.sig.SignalInterface;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SignalManager {
    private Map<String, SignalInterface> mapOfSignal = new HashMap<>();

    public void createSignal(String name){
        mapOfSignal.put(name, (SignalInterface)new Signal());
    }

    public void readSignal(File file, Protocol protocol){
        //todo

    }

    public SignalInterface getSignal(String name){
        return mapOfSignal.get(name);
    }
}
