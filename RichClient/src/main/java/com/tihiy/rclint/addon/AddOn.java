package com.tihiy.rclint.addon;
import com.tihiy.rclint.mvcAbstract.AbstractModel;

import javax.swing.*;
import java.awt.*;

public class AddOn extends AbstractModel{
    private String addOnName;
    private Integer value = 20;
    public AddOn(String parentModelName){
        this.addOnName = parentModelName + "addOn";
    }
    public void setValue(int value){
        firePropertyChange(addOnName, 0, value);
    }
    public void paint(Graphics g, JPanel comp){
        g.setColor(Color.GREEN);
        g.drawLine(value, 0, value, comp.getHeight());
    }
}
