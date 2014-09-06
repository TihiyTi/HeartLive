package com.tihiy.jfreeclient.jfreeutil;

import java.util.ArrayList;
import java.util.List;

public class SignalCreator {
    private int dFreq = 1;

    public SignalJFreeSetting signal(List<Double> dataSignal){
        SignalJFreeSetting signal =  new SignalJFreeSetting();
        signal.setSignalData(dataSignal);
        signal.setSignalArgs(createArgs(dataSignal.size()));

        return signal;
    }

    public SignalCreator setFreq(int freq){
        dFreq = freq;
        return this;
    }

    private List<Double> createArgs(int signalSize){
        List<Double> args = new ArrayList<>();
        for (int i = 0; i < signalSize; i++) {
            args.add(i/(1.*dFreq));
        }
        return args;
    }

}
