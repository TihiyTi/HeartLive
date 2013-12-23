package com.tihiy.rclint;

import javax.swing.*;
import java.awt.*;

public class TestBoxLayout {
    private static void addComponentsToPane(Container pane){
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.ORANGE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
//        panel.setMinimumSize(new Dimension(100, 100));

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.BLUE);
        panel2.setSize(new Dimension(100,100));

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.GREEN);
        panel3.setMinimumSize(new Dimension(100, 100));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(panel);
        mainPanel.add(Box.createGlue());
        mainPanel.add(panel2);
        mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
        mainPanel.add(panel3);

        JButton but1 = new JButton("Button1");
        JButton but2 = new JButton("Button2");
        JButton but3 = new JButton("Button3");

        panel.add(but1);
        panel.add(but2);
        panel.add(but3);

        Box box = Box.createHorizontalBox();
        box.add(new JLabel("Test"));
        box.add(new JTextField());
        Box box2 =  Box.createHorizontalBox();
        box2.add(new JLabel("Test2"));
        box2.add(new JTextArea());

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel2.add(box);
        panel2.add(Box.createVerticalGlue());
        panel2.add(box2);

        pane.add(mainPanel);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Test mouse listener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
//        JPanel panel = new JPanel();
//        addComponentsToPane(frame.getContentPane());

//        JPanel panel = new ParamPanel();
//        frame.getContentPane().add(panel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    class MyPanel extends JPanel{
        MyPanel(){
            super();
            setPreferredSize(new Dimension(100,200));
        }
    }
}
