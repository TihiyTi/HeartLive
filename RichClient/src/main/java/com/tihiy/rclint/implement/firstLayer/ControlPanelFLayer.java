package com.tihiy.rclint.implement.firstLayer;

import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

public class ControlPanelFLayer  extends AbstractViewPanel{

    private final ControllerFLayer mc;

    public ControlPanelFLayer(ControllerFLayer mc) {
        this.mc = mc;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        initComponent();
        initListeners();
    }

    private void initListeners() {
        butCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.calculate(paramPanel.getParam());
            }
        });
    }

    private void initComponent() {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        //To change body of implemented methods use File | Settings | File Templates.
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
