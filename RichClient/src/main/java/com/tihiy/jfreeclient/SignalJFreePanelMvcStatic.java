package com.tihiy.jfreeclient;

import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class SignalJFreePanelMvcStatic extends AbstractViewPanel{
    private String signalName;
    private List<String> signalNames;
//    private Map<>

    public SignalJFreePanelMvcStatic(String signalName) {
        this.signalName = signalName;
    }


    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }
}
