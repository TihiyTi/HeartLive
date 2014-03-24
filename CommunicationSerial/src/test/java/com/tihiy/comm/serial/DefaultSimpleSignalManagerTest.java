package com.tihiy.comm.serial;

import com.tihiy.comm.serial.protocols.Protocol;
import org.junit.Test;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DefaultSimpleSignalManagerTest {
    @Test
    public void simpleTest() throws InterruptedException {
        DefaultSimpleSignalManager signalManager = new DefaultSimpleSignalManager();
        signalManager.setProtocol(Protocol.ByteFlow);
//        signalManager.setProtocol(Protocol.SimpleEcg);
        ComPortListener comPortListener = ComPortListener.getInstance(signalManager);
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(comPortListener, 0L, 2L, TimeUnit.SECONDS);

//        SignalPanel view = new SignalPanel((BlockingQueue<Integer>) signalManager.getQueue());

        JFrame frame = new JFrame("testing");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.getContentPane().add(view, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        while (frame.isVisible()) ;

        comPortListener.closeAllPorts();
    }
}
