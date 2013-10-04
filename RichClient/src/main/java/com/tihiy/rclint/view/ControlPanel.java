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
        setPreferredSize(new Dimension(200, 400));
        setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponent();
    }

    private void initComponent(){
        butChooseSignal = new JButton("Choose Signal");
        butChooseFirstLayerSignal = new JButton("First Layer Signal");
        butCalculate =  new JButton("Calculate");
        mainSizeA = new JTextField("main Size A");
        mainSizeB = new JTextField("main Size B");
        mainXShift = new JTextField("main XShift");
        mainYShift = new JTextField("main YShift");
        mainRSphere = new JTextField("mainRSphere");
        mainH = new JTextField("mainH");
        firstSizeA = new JTextField("first Size A");
        firstSizeB = new JTextField("first Size B");
        firstXShift = new JTextField("first XShift");
        firstYShift = new JTextField("first YShift");
        firstRSphere = new JTextField("firstRSphere");
        firstH = new JTextField("firstH");

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
                double[] main = new double[6];
//                main[0] = Double.valueOf(mainSizeA.getText());
//                main[1] = Double.valueOf(mainSizeB.getText());
//                main[2] = Double.valueOf(mainXShift.getText());
//                main[3] = Double.valueOf(mainYShift.getText());
//                main[4] = Double.valueOf(mainRSphere.getText());
//                main[5] = Double.valueOf(mainH.getText());
                main[0] = 0.06;
                main[1] = 0.03;
                main[2] = 0;
                main[3] = 0;
                main[4] = 0.04;
                main[5] = 0.02;
                double[] first = new double[6];
//                first[0] = Double.valueOf(firstSizeA.getText());
//                first[1] = Double.valueOf(firstSizeB.getText());
//                first[2] = Double.valueOf(firstXShift.getText());
//                first[3] = Double.valueOf(firstYShift.getText());
//                first[4] = Double.valueOf(firstRSphere.getText());
//                first[5] = Double.valueOf(firstH.getText());
                first[0] = 0.06;
                first[1] = 0.03;
                first[2] = 0;
                first[3] = 0;
                first[4] = 0;
                first[5] = 0;
                mc.calculate(main, first);
            }
        });


        add(butChooseSignal);
        add(butChooseFirstLayerSignal);
        add(butCalculate);
        add(mainSizeA);
        add(mainSizeB);
        add(mainXShift);
        add(mainYShift);
        add(mainRSphere);
        add(mainH);
        add(firstSizeA);
        add(firstSizeB);
        add(firstXShift);
        add(firstYShift);
        add(firstRSphere);
        add(firstH);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private JButton butChooseSignal;
    private JButton butChooseFirstLayerSignal;
    private JButton butCalculate;
    private JTextField mainSizeA;
    private JTextField mainSizeB;
    private JTextField mainXShift;
    private JTextField mainYShift;
    private JTextField mainRSphere;
    private JTextField mainH;
    private JTextField firstSizeA;
    private JTextField firstSizeB;
    private JTextField firstXShift;
    private JTextField firstYShift;
    private JTextField firstRSphere;
    private JTextField firstH;

    private File defaultPath;

    private File chooseFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.changeToParentDirectory();
        if(defaultPath!=null){
            fileChooser.setCurrentDirectory( defaultPath);
        }
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showDialog(new JFrame(), "Choose signal!");
        if(defaultPath==null){
            defaultPath = fileChooser.getCurrentDirectory();
        }
        return fileChooser.getSelectedFile();
    }
}
