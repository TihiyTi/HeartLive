package com.tihiy.rclint.addon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddOn implements AddOnInterface<List<Integer>>{

    List<Integer> max;


    @Override
    public void paint(Graphics g, JComponent component) {
        g.setColor(Color.GREEN);
        if(max != null){
            for(Integer value: max){
                g.drawLine(value, 0, value, component.getHeight());
            }
        }
    }

    @Override
    public void setState(List<Integer> state) {
        max = state;
    }
}
