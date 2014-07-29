package com.tihiy.rclint.view;

import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.14
 * Time: 20:43
 */
public class MultiSignalPanel extends AbstractViewPanel{
    private AbstractController mc;
    private Map<String, List<Double>> signalMap;
    private int scale = 2;
    private int mouseX = 0;
    private int mouseY = 0;

    public MultiSignalPanel(AbstractController mc, Map<String,List<Double>> map) {
        this.mc = mc;
        this.signalMap = map;
        initComponent();
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        super.paintComponent(g);
        int numOfSignal = 1;
        for(String key: signalMap.keySet()){
            List<Double> signal = signalMap.get(key);
            paintSignal(g, signal);
            if(mouseX < signal.size()){
                g.drawString("Амплитуда сигнала"+ key +": " + signal.get(mouseX), mouseX + 50, getHeight() - 10*numOfSignal);
                numOfSignal++;
            }
        }
    }

    private void paintSignal(Graphics g, List<Double> signal) {
        if(!signal.isEmpty()){
            int height = getHeight();

            Double max = Collections.max(signal);
            Double min = Collections.min(signal);

            for (int i = 0; i < signal.size() - 1; i++) {
                int yPoint_1 = (int)((0.1*height + (max - signal.get(i))*0.8*height)/(max - min));
                int yPoint_2 = (int)((0.1*height + (max - signal.get(i+1))*0.8*height)/(max - min));
                g.drawLine(i/scale,yPoint_1, (i+1)/scale,yPoint_2);
            }
        }
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if(signalMap.containsKey(evt.getPropertyName())){
            List<Double> list = (List)evt.getNewValue();
            signalMap.put(evt.getPropertyName(), list);
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
                mouseY = (int)e.getPoint().getY();
                repaint();
            }
        });
    }
}
