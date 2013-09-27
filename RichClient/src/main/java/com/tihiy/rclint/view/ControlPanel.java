package com.tihiy.rclint.view;

import com.tihiy.rclint.control.Controller;
import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends AbstractViewPanel {
    private final Controller mc;

    public ControlPanel(Controller mc) {
        this.mc = mc;
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(200, 200));

        initComponent();
    }

    private void initComponent(){
        butChooseSignal = new JButton("Choose Signal");
        butChooseFirstLayerSignal = new JButton("First Layer Signal");
        butCalculate =  new JButton("Calculate");

        butChooseSignal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mc.addSignal("sourceSignal", chooseFile());
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butChooseFirstLayerSignal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mc.addSignal("targetSignal", chooseFile());
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.calculate();
            }
        });


        add(butChooseSignal);
        add(butChooseFirstLayerSignal);
        add(butCalculate);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private JButton butChooseSignal;
    private JButton butChooseFirstLayerSignal;
    private JButton butCalculate;

    private static File chooseFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.changeToParentDirectory();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showDialog(new JFrame(), "Choose signal!");
        return fileChooser.getSelectedFile();
    }
}
