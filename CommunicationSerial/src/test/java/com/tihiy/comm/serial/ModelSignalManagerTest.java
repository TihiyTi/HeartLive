package com.tihiy.comm.serial;

import com.tihiy.comm.serial.protocols.Protocol;
import com.tihiy.rclint.control.Controller;
import com.tihiy.rclint.models.SignalModel;
import com.tihiy.rclint.models.SignalModelIntRT;
import com.tihiy.rclint.view.ControlPanel;
import com.tihiy.rclint.view.SignalPanel;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ModelSignalManagerTest {
    @Test
    public void simpleTest() throws InterruptedException {
        SignalModelIntRT modelIntRT = new SignalModelIntRT();
        DefaultSimpleSignalManager signalManager = new DefaultSimpleSignalManager();
        signalManager.setProtocol(Protocol.SimpleEcg);
        ComPortListener comPortListener =  ComPortListener.getInstance(signalManager);
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(comPortListener, 0L, 2L, TimeUnit.SECONDS);

//        SignalModel model = new SignalModel();
        Controller mc = new Controller();

        // TODO Comment because getList return <Float> List, but model is <Double> List.
//        SignalPanel view = new SignalPanel(mc, modelIntRT.getList());
        ControlPanel cp = new ControlPanel(mc);

        mc.addModel(modelIntRT);
//        mc.addView(view);
        mc.addView(cp);

        JFrame frame = new JFrame("testing");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.getContentPane().add(view, BorderLayout.CENTER);
        frame.getContentPane().add(cp, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);
        while (frame.isVisible());

        comPortListener.closeAllPorts();
    }
}
