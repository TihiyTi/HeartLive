package com.tihiy.rclint.models;

import com.tihiy.rclint.control.Controller;
import com.tihiy.rclint.implement.ControlPanel;
import com.tihiy.rclint.view.SignalPanel;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 14.03.13
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
public class SignalModelTest {
    @Test
    public  void testMain(){
        SignalModel model = new SignalModel();
        Controller mc = new Controller();

        SignalPanel view = new SignalPanel(mc, model.getList());
        ControlPanel cp = new ControlPanel(mc);

        mc.addModel(model);
        mc.addView(view);
        mc.addView(cp);

        JFrame frame = new JFrame("testing");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(view, BorderLayout.CENTER);
        frame.getContentPane().add(cp, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);
        while (frame.isVisible());

    }
}
