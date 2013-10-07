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
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControlPanel extends AbstractViewPanel {
    private final Controller mc;
    private File defaultPath;
    private File sourceFile;

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
        butChooseBaseSignal = new JButton("Base signal");
        butCalculate =  new JButton("Calculate");
        butDefault = new JButton("Default signal");
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
                    sourceFile = chooseFile();
                    mc.addSignal("sourceSignal", sourceFile);
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
        butChooseBaseSignal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mc.addSignal("baseSignal", chooseFile());
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
                main[0] = 0.04;
                main[1] = 0.02;
                main[2] = 0;
                main[3] = 0.037;
                main[4] = 0.045;
                main[5] = 0.019;
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
                File radiusFile = new File(defaultPath, "radius"+ getDate()+sourceFile.getName());
                mc.calculate(main, first, radiusFile, null);
            }
        });
        butDefault.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sourceFile = new File("C:\\Users\\Home\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716\\P1.txt");
                File roFile = new File("C:\\Users\\Home\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716\\Ro.txt");
                File baseFile = new File("C:\\Users\\Home\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716\\PB1.txt");
                try {
                    mc.addSignal("sourceSignal", sourceFile);
                    mc.addSignal("targetSignal", roFile);
                    mc.addSignal("baseSignal", baseFile);
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                double[] main = {0.04,0.02,0,0.037,0.045,0.019};
                double[] first = {0.06, 0.03,0,0,0,0};
                defaultPath = new File("C:\\Users\\Home\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716");
                File radiusFile = new File(defaultPath, "radius"+getDate()+sourceFile.getName());
                mc.calculate(main, first, radiusFile, getComment(roFile, baseFile, main, first));
            }
        });


        add(butChooseSignal);
        add(butChooseFirstLayerSignal);
        add(butChooseBaseSignal);
        add(butCalculate);
        add(butDefault);
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
    private JButton butChooseBaseSignal;
    private JButton butCalculate;
    private JButton butDefault;
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

    private File chooseFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.changeToParentDirectory();
//        fileChooser.setCurrentDirectory(new File("C:\\Users\\Home\\Documents\\My Box Files\\Asp\\RoChange\\Rad 20130716"));
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

    private static String getDate(){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("_yyyy_MM_dd_hh_mm_ss");
        return ft.format(dNow);
    }
    private String getComment(File roFile, File baseFile, double[] main, double[] first){
        String comment = "Pulse="+sourceFile.getName()+" Ro="+roFile.getName()+" Base="+baseFile.getName()+" ";
        comment += "pulseES:";
        for(double d:main){
            comment += " "+d;
        }
        comment += " baseES" + first[0]+" "+first[1]+" ";
        return comment;
    }
}
