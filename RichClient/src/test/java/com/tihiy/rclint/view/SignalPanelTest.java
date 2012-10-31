package com.tihiy.rclint.view;

import junit.framework.TestCase;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SignalPanelTest extends TestCase {
    @Test(timeout = 1000, expected = InterruptedException.class)
    public void testPanel(){
        List list = new ArrayList();
        SignalPanel panel = new SignalPanel(list);
        panel.setPreferredSize(new Dimension(500, 300));
//        panel.setBackground(Color.GREEN);
        for(int i = 0; i < 300; i++ ){
            list.add((float)Math.random()*100);
        }
        JFrame frame = new JFrame("testing");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
//        while(frame.isVisible()){}
        DefaultPieDataset dataset;
//        dataset.sortByKeys();
    }

    public void tryToJFreeChart(){
        XYDataset xyDataset =  new XYSeriesCollection();
    }
}
