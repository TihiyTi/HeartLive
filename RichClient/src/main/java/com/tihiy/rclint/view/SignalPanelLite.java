package com.tihiy.rclint.view;

import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;

public class SignalPanelLite extends AbstractViewPanel {
    private double[] signal = new double[0];
    private String signalName;
    private int mouseX = 0;

    public SignalPanelLite(String signalName){
        this.signalName = signalName;
        initComponent();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(signal.length > 0){
            int height = getHeight();
            double max = getMax(signal);
            double min = getMin(signal);
            for (int i = 0; i < signal.length - 1; i++) {
                int yPoint_1 = (int)((0.1*height + (max - signal[i])*0.8*height)/(max - min));
                int yPoint_2 = (int)((0.1*height + (max - signal[i+1])*0.8*height)/(max - min));
                g2.drawLine(i,yPoint_1, (i+1),yPoint_2);
            }
        }
        if(mouseX < signal.length){
            g2.drawString("Амплитуда сигнала: " + signal[mouseX], 50, getHeight() - 10);
        }
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(signalName)){
            signal = (double[])evt.getNewValue();
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
            }
        });
    }

    private double getMax(double[] array){
        double max = array[0];
        for(int i= 1; i < array.length; i++){
            if(max < array[i]){
                max = array[i];
            }
        }
        return max;
    }
    private double getMin(double[] array){
        double min = array[0];
        for(int i= 1; i < array.length; i++){
            if(min < array[i]){
                min = array[i];
            }
        }
        return min;
    }
}
