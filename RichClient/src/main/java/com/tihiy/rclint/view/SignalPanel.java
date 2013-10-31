package com.tihiy.rclint.view;

import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SignalPanel extends AbstractViewPanel {
    AbstractController mc;
    private String signalName;

    private List<Double> signal;
    private BlockingQueue<Integer> queue;
    boolean queueFlag = false;

    int mouseX = 0;

    public SignalPanel(AbstractController mc, List<Double> list, String name) {
        this.mc = mc;
        this.signal = list;
        setBackground(Color.orange);
        setPreferredSize(new Dimension(getWidth(), 200));
        signalName = name;
        initComponent();
    }

    public SignalPanel(AbstractController mc, List<Double> list) {
        this.mc = mc;
        this.signal = list;
        setBackground(Color.orange);
        setPreferredSize(new Dimension(getWidth(), 200));
        initComponent();
    }

    public SignalPanel(List<Double> list) {
        this.signal = list;
        setBackground(Color.orange);
        initComponent();
    }

    public SignalPanel(BlockingQueue<Integer> queue){
        this.queue = queue;
        queueFlag = true;
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Repainter(this), 0L, 1L, TimeUnit.SECONDS);
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        super.paintComponent(g);
        if(queueFlag){
            if(!queue.isEmpty()){
                List<Integer> list = new ArrayList<>();
                list.addAll(queue);
                int height = getHeight();

                Integer max = Collections.max(list);
                Integer min = Collections.min(list);

                for (int i = 0; i < list.size() - 1; i++) {
                    int yPoint_1 = (int)((0.1*height + (max - list.get(i))*0.8*height)/(max - min));
                    int yPoint_2 = (int)((0.1*height + (max - list.get(i+1))*0.8*height)/(max - min));
                    g.drawLine(i,yPoint_1, (i+1),yPoint_2);
                }
            }
        }else{
            if(!signal.isEmpty()){
                int height = getHeight();
//        int width = getWidth();

                Double max = Collections.max(signal);
                Double min = Collections.min(signal);

                for (int i = 0; i < signal.size() - 1; i++) {
                    int yPoint_1 = (int)((0.1*height + (max - signal.get(i))*0.8*height)/(max - min));
                    int yPoint_2 = (int)((0.1*height + (max - signal.get(i+1))*0.8*height)/(max - min));
                    g.drawLine(i,yPoint_1, (i+1),yPoint_2);
                }
            }
        }

        if(mouseX < signal.size()){
            g.drawString("Амплитуда сигнала: " + signal.get(mouseX), 50, getHeight() - 10);
        }
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(signalName)){
            List<Double> list = (List)evt.getNewValue();
            signal = list;
            repaint();
        }
    }

    private void initComponent(){
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = (int)e.getPoint().getX();
                repaint();
            }
        });
    }

    static class Repainter implements Runnable{
        SignalPanel panel;

        Repainter(SignalPanel panel) {
            this.panel = panel;
        }

        @Override
        public void run() {
            panel.repaint();
        }
    }
}
