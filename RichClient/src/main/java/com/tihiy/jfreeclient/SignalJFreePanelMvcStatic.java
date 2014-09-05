package com.tihiy.jfreeclient;

import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

public class SignalJFreePanelMvcStatic extends AbstractViewPanel{
    private String signalName;
    private List<String> signalNames;
    private Map<String, List<Double>> signalMap;

    public SignalJFreePanelMvcStatic(String signalName) {
        this.signalName = signalName;
    }


    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }
}
