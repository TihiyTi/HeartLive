package com.tihiy.comm.serial;

import com.tihiy.comm.serial.protocols.Protocol;
import org.junit.Test;

import javax.swing.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 26.05.13
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public class DefSignalManagerTest {
    @Test
    void simpleTest() throws InterruptedException {
        JFrame frame =  new JFrame("Default signal manager");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        DefaultSimpleSignalManager signalManager = new DefaultSimpleSignalManager();
        signalManager.setProtocol(Protocol.SimpleEcg);
        ComPortListener comPortListener =  ComPortListener.getInstance(signalManager);
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(comPortListener, 0L, 2L, TimeUnit.SECONDS);
        if(signalManager.getQueue() != null){
            BlockingQueue queue = signalManager.getQueue();
            for(int i = 0; i < 100; i++){
                System.out.println(queue.take());
            }
        }else{
            System.out.println("Port with protocol not found");
        }
        comPortListener.closeAllPorts();
    }
}
