package com.tihiy.rclint.view;

import junit.framework.TestCase;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 03.10.12
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */
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
    }
}