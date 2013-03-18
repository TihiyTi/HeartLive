package com.tihiy.rclint.view;

import com.tihiy.rclint.control.Controller;
import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 16.03.13
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public class ControlPanel extends AbstractViewPanel {
    private Controller mc;

    public ControlPanel(Controller mc) {
        this.mc = mc;
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(200, 200));

        initComponent();
    }

    private void initComponent(){
        but1 = new JButton("Test");

        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.command(Controller.COMMAND_RANDOM_LIST);
            }
        });

        add(but1);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private JButton but1;
}
