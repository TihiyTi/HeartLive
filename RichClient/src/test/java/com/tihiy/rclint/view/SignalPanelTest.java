package com.tihiy.rclint.view;


import com.tihiy.rclint.MapPanel;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class SignalPanelTest {

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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Test
    public void testMapPanel(){
        JFrame frame =  new JFrame("Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MapPanel map = new MapPanel();
        map.setPotentialMap(getTestMap());
        JPanel jpanel = map.createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        frame.getContentPane().add(jpanel);
        frame.pack();
        frame.setVisible(true);
        while(frame.isVisible()){}
    }

    public static double[][] getTestMap(){
        double [][] result = new double[3][100];
        for(int i = 0; i<10; i++){
            for(int j = 0; j < 10; j++){
                result[0][i*10 + j] = i;
                result[1][i*10 + j] = j;
                result[2][i*10 + j] = (i+j)/ 10.0d;
            }
        }
        return result;
    }
}
