package com.tihiy.rclint;

import com.tihiy.rclint.control.Controller;
import com.tihiy.rclint.models.SignalModel;
import com.tihiy.rclint.view.ControlPanel;
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
        SignalModel radiusSignal = new SignalModel(RADIUS_SIGNAL);

        Controller mc = new Controller();

//        JPanel panels = new JPanel();
        SignalPanel view = new SignalPanel(mc, sourceSignal.getList(), PULSE_SIGNAL);
        SignalPanel view2 = new SignalPanel(mc, targetSignal.getList(), FIRST_LAYER_SIGNAL);
//        SignalPanel view3 = new SignalPanel(mc, radiusSignal.getList());
//        panels.add(view, BorderLayout.NORTH);
//        panels.add(view2, BorderLayout.CENTER);
//        panels.add(view3, BorderLayout.NORTH);

        ControlPanel cp = new ControlPanel(mc);

        mc.addModel(PULSE_SIGNAL,sourceSignal);
        mc.addModel(FIRST_LAYER_SIGNAL, targetSignal);
        mc.addModel(RADIUS_SIGNAL, radiusSignal);
        mc.addView(view);
        mc.addView(cp);

        JFrame frame = new JFrame("Radius Calculation with First Layer");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(view, BorderLayout.CENTER);
        frame.getContentPane().add(view2, BorderLayout.SOUTH);

//        frame.getContentPane().add(view3, BorderLayout.NORTH);
        frame.getContentPane().add(cp, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
        while (frame.isVisible());

    }

//    class RadController extends Controller{
//        public static final String COMMAND_ADD_
//
//        public void addSignal(String name, File file){
//
//        }
//    }
}

