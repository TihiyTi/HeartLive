package com.tihiy.rclint.implement.firstLayer;

import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControlPanelFLayer  extends AbstractViewPanel{

    private final ControllerFLayer mc;
    private File defaultPath;

    public ControlPanelFLayer(ControllerFLayer mc) {
        this.mc = mc;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        initComponent();
        initListeners();
    }

    private void initListeners() {
        butChooseSignal_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File sourceFile = chooseFile();
                    mc.addSignal(ControllerFLayer.PRECARD_1, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butChooseSignal_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile = chooseFile();
                try {
                    mc.addSignal(ControllerFLayer.PRECARD_2, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butChooseSignal_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile = chooseFile();
                try {
                    mc.addSignal(ControllerFLayer.PRECARD_3, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butChooseSignal_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile = chooseFile();
                try {
                    mc.addSignal(ControllerFLayer.PRECARD_4, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butChooseSignal_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile = chooseFile();
                try {
                    mc.addSignal(ControllerFLayer.PRECARD_5, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        butChooseBaseSignal_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile = chooseFile();
                try {
                    mc.addSignal(ControllerFLayer.PRECARD_BASE_1, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butChooseBaseSignal_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile = chooseFile();
                try {
                    mc.addSignal(ControllerFLayer.PRECARD_BASE_2, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butChooseBaseSignal_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile = chooseFile();
                try {
                    mc.addSignal(ControllerFLayer.PRECARD_BASE_3, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butChooseBaseSignal_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile = chooseFile();
                try {
                    mc.addSignal(ControllerFLayer.PRECARD_BASE_4, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butChooseBaseSignal_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile = chooseFile();
                try {
                    mc.addSignal(ControllerFLayer.PRECARD_BASE_5, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        butChooseFirstLayerSignal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile = chooseFile();
                try {
                    mc.addSignal(ControllerFLayer.FIRST, sourceFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        butCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.calculate(paramPanel.getParam(), defaultPath);
            }
        });
    }

    private void initComponent() {
        butChooseSignal_1 = new JButton("Choose Signal 1");
        butChooseSignal_2 = new JButton("Choose Signal 2");
        butChooseSignal_3 = new JButton("Choose Signal 3");
        butChooseSignal_4 = new JButton("Choose Signal 4");
        butChooseSignal_5 = new JButton("Choose Signal 5");
        butChooseFirstLayerSignal = new JButton("First Layer Signal");
        butChooseBaseSignal_1 = new JButton("Base signal 1");
        butChooseBaseSignal_2 = new JButton("Base signal 2");
        butChooseBaseSignal_3 = new JButton("Base signal 3");
        butChooseBaseSignal_4 = new JButton("Base signal 4");
        butChooseBaseSignal_5 = new JButton("Base signal 5");
        butCalculate =  new JButton("Calculate");
        butDefault = new JButton("Default signal");
        paramPanel = new TabParamPanel();

        GridBagConstraints constraints = new GridBagConstraints(0,0, 1,1, 0,0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0,0);
        add(butChooseSignal_1, constraints);
        constraints.gridy = 1;
        add(butChooseSignal_2, constraints);
        constraints.gridy = 2;
        add(butChooseSignal_3, constraints);
        constraints.gridy = 3;
        add(butChooseSignal_4, constraints);
        constraints.gridy = 4;
        add(butChooseSignal_5, constraints);
        constraints.gridy = 5;
        add(butChooseFirstLayerSignal, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(butChooseBaseSignal_1, constraints);
        constraints.gridy = 1;
        add(butChooseBaseSignal_2, constraints);
        constraints.gridy = 2;
        add(butChooseBaseSignal_3, constraints);
        constraints.gridy = 3;
        add(butChooseBaseSignal_4, constraints);
        constraints.gridy = 4;
        add(butChooseBaseSignal_5, constraints);
        constraints.gridy = 5;
        add(butCalculate, constraints);

        constraints.gridy = 6;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        add(paramPanel, constraints);

//        add(butDefault, /constraints);
//        constraints.gridy = 5;
//        constraints.gridx = 0;
    }

    private File chooseFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.changeToParentDirectory();
        fileChooser.setCurrentDirectory(new File("C:\\Users\\Home\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716"));
//        defaultPath = new File("C:\\Users\\Home\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716");
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

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }



    private JButton butChooseSignal_1;
    private JButton butChooseSignal_2;
    private JButton butChooseSignal_3;
    private JButton butChooseSignal_4;
    private JButton butChooseSignal_5;
    private JButton butChooseBaseSignal_1;
    private JButton butChooseBaseSignal_2;
    private JButton butChooseBaseSignal_3;
    private JButton butChooseBaseSignal_4;
    private JButton butChooseBaseSignal_5;
    private JButton butChooseFirstLayerSignal;
    private JButton butCalculate;
    private JButton butDefault;
    private TabParamPanel paramPanel;
}
