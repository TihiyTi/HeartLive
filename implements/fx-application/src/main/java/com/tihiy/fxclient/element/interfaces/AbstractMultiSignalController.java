package com.tihiy.fxclient.element.interfaces;

public abstract class AbstractMultiSignalController {
    protected MultiSignalModelInterface model;

    public void setModel(MultiSignalModelInterface model){
        this.model = model;
    }
}
