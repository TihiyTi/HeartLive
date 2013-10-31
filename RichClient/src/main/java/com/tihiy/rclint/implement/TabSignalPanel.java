package com.tihiy.rclint.implement;

import com.tihiy.rclint.control.Controller;
import com.tihiy.rclint.view.SignalPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class TabSignalPanel extends JTabbedPane {
    Controller mc;

    public TabSignalPanel(Controller mc) {
        this.mc = mc;
        initComponent();
    }

    private void initComponent() {
        addTab("1", addSingleTab(Controller.PRECARD_1, Controller.FIRST, Controller.RADIUS_1));
        addTab("2", addSingleTab(Controller.PRECARD_2, Controller.FIRST, Controller.RADIUS_2));
        addTab("3", addSingleTab(Controller.PRECARD_3, Controller.FIRST, Controller.RADIUS_3));
        addTab("4", addSingleTab(Controller.PRECARD_4, Controller.FIRST, Controller.RADIUS_4));
        addTab("5", addSingleTab(Controller.PRECARD_5, Controller.FIRST, Controller.RADIUS_5));
    }

    private Component addSingleTab(String precardio, String first, String radius){
        JPanel panel = new JPanel(new BorderLayout());
        SignalPanel s1 = addPanel(precardio);
        SignalPanel s2 = addPanel(first);
        SignalPanel s3 = addPanel(radius);
        panel.add(s1, BorderLayout.NORTH);
        panel.add(s2, BorderLayout.CENTER);
        panel.add(s3, BorderLayout.SOUTH);
        return panel;
    }

    private SignalPanel addPanel(String nameSignal){
        SignalPanel tempPanel = new SignalPanel(mc, Collections.<Double>emptyList(), nameSignal);
        mc.addView(tempPanel);
        return tempPanel;
    }
}
