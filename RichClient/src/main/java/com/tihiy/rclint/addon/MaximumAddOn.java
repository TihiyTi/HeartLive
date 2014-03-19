package com.tihiy.rclint.addon;

import com.tihiy.rclint.mvcAbstract.ViewAddOnInterface;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MaximumAddOn implements ViewAddOnInterface<List<Integer>> {
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
