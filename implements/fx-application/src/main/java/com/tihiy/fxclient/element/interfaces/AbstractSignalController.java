package com.tihiy.fxclient.element.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractSignalController {
    protected List<SignalModelInterface> models = new ArrayList<>();

    protected void bindAll(){
        bindSignal();
        bindName();
        bindSampleFreq();
    }
    public void setModels(SignalModelInterface... model){
        this.models = Arrays.asList(model);
    }


    protected abstract void bindSignal();
    protected abstract void bindName();
    protected abstract void bindSampleFreq();
}
