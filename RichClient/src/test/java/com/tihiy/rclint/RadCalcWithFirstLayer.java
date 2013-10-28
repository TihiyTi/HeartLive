package com.tihiy.rclint;

import com.tihiy.rclint.control.Controller;
import com.tihiy.rclint.models.SignalModel;
import com.tihiy.rclint.implement.ControlPanel;
import com.tihiy.rclint.view.SignalPanel;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class RadCalcWithFirstLayer {
    public static final String PULSE_SIGNAL = "sourceSignal";
    public static final String BASE_SIGNAL = "baseSignal";
    public static final String FIRST_LAYER_SIGNAL = "targetSignal";
    public static final String RADIUS_SIGNAL = "radiusSignal";

    @Test
    public void testFirstLayer(){

        SignalModel sourceSignal = new SignalModel(PULSE_SIGNAL);
        SignalModel targetSignal =  new SignalModel(FIRST_LAYER_SIGNAL);
        SignalModel baseSignal = new SignalModel(BASE_SIGNAL);
        SignalModel radiusSignal = new SignalModel(RADIUS_SIGNAL);

        Controller mc = new Controller();

        JPanel panels = new JPanel();
        SignalPanel view = new SignalPanel(mc, sourceSignal.getList(), PULSE_SIGNAL);
        SignalPanel view2 = new SignalPanel(mc, targetSignal.getList(), FIRST_LAYER_SIGNAL);
        SignalPanel view3 = new SignalPanel(mc, radiusSignal.getList(), RADIUS_SIGNAL);
        panels.add(view, BorderLayout.NORTH);
        panels.add(view2, BorderLayout.CENTER);
        panels.add(view3, BorderLayout.NORTH);

        ControlPanel cp = new ControlPanel(mc);

        mc.addModel(PULSE_SIGNAL,sourceSignal);
        mc.addModel(FIRST_LAYER_SIGNAL, targetSignal);
        mc.addModel(BASE_SIGNAL, baseSignal);
        mc.addModel(RADIUS_SIGNAL, radiusSignal);
        mc.addView(view);
        mc.addView(view2);
        mc.addView(view3);
        mc.addView(cp);

        RadCalcFrame frame = new RadCalcFrame("Radius", cp, panels);
        frame.setVisible(true);
        while (frame.isVisible());

    }

}

