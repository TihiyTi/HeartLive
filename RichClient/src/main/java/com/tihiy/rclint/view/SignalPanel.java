package com.tihiy.rclint.view;

import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;

public class SignalPanel extends AbstractViewPanel {
    private final List<Float> signal;

    public SignalPanel(List<Float> signal) {
        super();
        this.signal = signal;
        setBackground(Color.orange);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);

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

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        repaint();
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
