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
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 26.05.13
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public class DefSignalManagerTest {
    @Test
    public void simpleTest() throws InterruptedException {
        SignalModelIntRT modelIntRT = new SignalModelIntRT();
        DafaulSignalManager signalManager = new DafaulSignalManager(modelIntRT);
        signalManager.setProtocol(Protocol.SimpleEcg);
        ComPortListener comPortListener =  ComPortListener.getInstance(signalManager);
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(comPortListener, 0L, 2L, TimeUnit.SECONDS);

//        SignalModel model = new SignalModel();
        Controller mc = new Controller();

        SignalPanel view = new SignalPanel(mc, modelIntRT.getList());
        ControlPanel cp = new ControlPanel(mc);

        mc.addModel(modelIntRT);
        mc.addView(view);
        mc.addView(cp);

        JFrame frame = new JFrame("testing");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(view, BorderLayout.CENTER);
        frame.getContentPane().add(cp, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);
        while (frame.isVisible());

        comPortListener.closeAllPorts();
    }


    class DafaulSignalManager extends AbstractSignalManager{
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        private Logger log = Logger.getLogger(this.getClass().getName());
        SignalModelIntRT model;

        DafaulSignalManager(SignalModelIntRT signalModel) {
            model = signalModel;
        }


        public BlockingQueue getQueue(){
            return queue;
        }

        @Override
        public void getSamples(double[] samples, String flowName) {
            log.info("Protocol not supported");
        }

        @Override
        public void getSamples(byte[] samples, String flowName) {
//        Collections.addAll(queue, samples);
            log.info("Protocol not supported");
        }

        @Override
        public <T> void getSamples(List<T> samples) {
//            if(samples.size()!=0){
//                if(samples.get(0) instanceof Integer){
//                    for(Object i: samples){
//                        Integer integer = (Integer)i;
//                        queue.add(integer);
//                        System.out.println(""+integer);
//                    }
//                }
//            }
            model.addToList(samples);
        }

        @Override
        public void createSignal(String flowName) {
            if (queue==null){
                queue = new LinkedBlockingQueue();
            }else{
                log.info("Signal already exist");
            }
        }
    }
}
