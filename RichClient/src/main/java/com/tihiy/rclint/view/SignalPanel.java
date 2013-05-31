package com.tihiy.rclint.view;

import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;

public class SignalPanel extends AbstractViewPanel {
    AbstractController mc;

    private List<Float> signal;

    public SignalPanel(AbstractController mc, List<Float> list) {
        super();
        this.mc = mc;
        this.signal = list;
        setBackground(Color.orange);
        setPreferredSize(new Dimension(getWidth(), 200));
    }

    public SignalPanel(List<Float> list) {
        super();
        this.signal = list;
        setBackground(Color.orange);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        if(signal.size() >0 ){
            int height = getHeight();
//        int width = getWidth();

            Float max = Collections.max(signal);
            Float min = Collections.min(signal);

            for (int i = 0; i < signal.size() - 1; i++) {
                int yPoint_1 = (int)((0.1*height + (max - signal.get(i))*0.8*height)/(max - min));
                int yPoint_2 = (int)((0.1*height + (max - signal.get(i+1))*0.8*height)/(max - min));
                g.drawLine(i,yPoint_1, (i+1),yPoint_2);
            }
        }

    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("list")){
            List<Float> list = (List)evt.getNewValue();
            signal = list;
            repaint();
        };
    }
}
