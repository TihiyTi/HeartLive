package com.tihiy.rclint.view;

import com.tihiy.rclint.mvcAbstract.ViewAddOnInterface;
import com.tihiy.rclint.control.SimpleController;
import com.tihiy.rclint.models.SignalDynamicModel;
import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;

public class SignalPanelLite extends AbstractViewPanel {
    private SimpleController mc;
    private double[] signal = new double[0];
    private int mouseX = 0;

    public SignalPanelLite(String signalName){
        viewName = signalName;
        setPreferredSize(new Dimension(getWidth(), 200));
        initComponent();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
        g2.setColor(Color.RED);
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
            g2.drawString("Signal amplitude: " + signal[mouseX], 50, getHeight() - 10);
        }
        // ToDo add on support
        if(null!=addOns){
            for (ViewAddOnInterface addon: addOns){
                addon.paint(g, this);
            }
        }
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(viewName)){
            if((null!=addOns) && evt instanceof IndexedPropertyChangeEvent){
                int index = ((IndexedPropertyChangeEvent) evt).getIndex();
                addOns.get(index).setState(evt.getNewValue());
            }else {
                signal = (double[])evt.getNewValue();
            }
        }
        repaint();
    }

    private void initComponent(){
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {}
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = (int)e.getPoint().getX();
                repaint();
            }
        });
    }

    public void initFeedback(SimpleController controller){
        mc = controller;
        SignalDynamicModel model = (SignalDynamicModel) mc.getModel(viewName);
        model.setScaleSize(0, getWidth());
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SignalDynamicModel model = (SignalDynamicModel) mc.getModel(viewName);
                        model.setScaleSize(0, getWidth());
                    }
                });
            }
        });
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                SignalDynamicModel model = (SignalDynamicModel) mc.getModel(viewName);
                model.setScaleSize(0, getWidth());
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

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
            if(min > array[i]){
                min = array[i];
            }
        }
        return min;
    }
}
