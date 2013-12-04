package com.tihiy.rclint.implement.firstLayer;

import javax.swing.*;
import java.awt.*;

public class RadiusCalculator {
    private RadiusCalculator() {
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }

            private void createAndShowGUI() {
                ControllerFLayer mc = new ControllerFLayer();
                ControlPanelFLayer controlPanel = new ControlPanelFLayer(mc);
                controlPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
                TabSignalPanel signalPanel = new TabSignalPanel(mc);
                signalPanel.setBorder(BorderFactory.createLineBorder(Color.RED));


                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                panel.setPreferredSize(new Dimension(800, 600));
                panel.setLayout(new BorderLayout());
                panel.add(controlPanel, BorderLayout.EAST);
                panel.add(signalPanel, BorderLayout.CENTER);

                JFrame frame = new JFrame("RadiusCalculator");
                frame.setSize(panel.getPreferredSize());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(panel);
                frame.setVisible(true);
            }
        });
    }
}
