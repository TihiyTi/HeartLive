package com.tihiy.fxclient;

import com.tihiy.fxclient.element.interfaces.MultiSignalModelInterface;
import com.tihiy.fxclient.element.interfaces.SignalModelInterface;

import java.util.HashMap;
import java.util.Map;

public class MultiSignalModel implements MultiSignalModelInterface{
    Map<String, SignalModelInterface> map = new HashMap<>();




    @Override
    public SignalModelInterface getSignal(String name) {
        return null;
    }
}
