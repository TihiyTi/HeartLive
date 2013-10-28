package com.tihiy.rclint;

import com.tihiy.rclint.view.SignalPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Home
 * Date: 04.10.13
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public class RadCalcFrame extends JFrame {
    JPanel controlPanel;
    JPanel signalsPanel;

    RadCalcFrame(String name, JPanel controlPanel, JPanel signalPanel){
        super("name");
        this.controlPanel = controlPanel;
        this.signalsPanel = signalPanel;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponent();
    }

    public void initComponent(){

        controlPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 204)));

        BoxLayout panelLayout = new BoxLayout(signalsPanel, BoxLayout.Y_AXIS);
        signalsPanel.setLayout(panelLayout);
//        GroupLayout jPanel1Layout = new GroupLayout(controlPanel);
//        controlPanel.setLayout(jPanel1Layout);
//        jPanel1Layout.setHorizontalGroup(
//                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGap(0, 262, Short.MAX_VALUE)
//        );
//        jPanel1Layout.setVerticalGroup(
//                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGap(0, 373, Short.MAX_VALUE)
//        );
//
//        GroupLayout jPanel2Layout = new GroupLayout(signalsPanel);
//        signalsPanel.setLayout(jPanel2Layout);
//        jPanel2Layout.setHorizontalGroup(
//                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGap(0, 132, Short.MAX_VALUE)
//        );
//        jPanel2Layout.setVerticalGroup(
//                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGap(0, 376, Short.MAX_VALUE)
//        );


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(signalsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(signalsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RadCalcFrame("TEst",new JPanel(),new JPanel()).setVisible(true);
            }
        });
    }

    public void addSignalPanel(SignalPanel panel){
//        BoxLayout layout = new BoxLayout(signalsPanel, BoxLayout.X_AXIS);
//        signalsPanel.setLayout(layout);
        signalsPanel.add(panel);

    }
}
