package com.tihiy.rclint.implement.firstLayer;

import javax.swing.*;
import java.awt.*;

public class ParamPanel extends JPanel{

    private final JTextField mainSizeA = new JTextField();
    private final JTextField mainSizeB = new JTextField();
    private final JTextField mainXShift = new JTextField();
    private final JTextField mainYShift = new JTextField();
    private final JTextField mainRSphere = new JTextField();
    private final JTextField mainH = new JTextField();
    private final JTextField firstSizeA = new JTextField();
    private final JTextField firstSizeB = new JTextField();

    public ParamPanel() {
        setBorder(BorderFactory.createLineBorder(Color.RED));
        setLayout(new GridBagLayout());
        initComponent();
    }

    private void initComponent() {
        mainSizeA.setPreferredSize(new Dimension(50, 0));

        GridBagConstraints constraints = new GridBagConstraints(0,0, 1,1, 0,0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,5,0,5), 0,0);
        constraints.gridx = 0;
        add(new Label("Size A(main)"), constraints);
        constraints.gridx = 1;
        add(mainSizeA, constraints);
        constraints.gridy = 2;
        constraints.gridx = 0;
        add(new Label("Size B(main)"), constraints);
        constraints.gridx = 1;
        add(mainSizeB, constraints);
        constraints.gridy = 3;
        constraints.gridx = 0;
        add(new Label("Shift X(main)"), constraints);
        constraints.gridx = 1;
        add(mainXShift, constraints);
        constraints.gridy = 4;
        constraints.gridx = 0;
        add(new Label("Shift Y(main)"), constraints);
        constraints.gridx = 1;
        add(mainYShift, constraints);
        constraints.gridy = 5;
        constraints.gridx = 0;
        add(new Label("R sphere(main)"), constraints);
        constraints.gridx = 1;
        add(mainRSphere, constraints);
        constraints.gridy = 6;
        constraints.gridx = 0;
        add(new Label("H (main)"), constraints);
        constraints.gridx = 1;
        add(mainH, constraints);
        constraints.gridy = 7;
        constraints.gridx = 0;
        add(new Label("Size A(FL)"), constraints);
        constraints.gridx = 1;
        add(firstSizeA, constraints);
        constraints.gridy = 8;
        constraints.gridx = 0;
        add(new Label("Size B(FL)"), constraints);
        constraints.gridx = 1;
        add(firstSizeB, constraints);

    }

    protected double[] getParam(){
        return new double[]{Double.valueOf(mainSizeA.getText()), Double.valueOf(mainSizeB.getText()),
                Double.valueOf(mainXShift.getText()), Double.valueOf(mainYShift.getText()),
                Double.valueOf(mainRSphere.getText()), Double.valueOf(mainH.getText()),
                Double.valueOf(firstSizeA.getText()), Double.valueOf(firstSizeB.getText())};
    }
}
