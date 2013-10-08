package com.tihiy.rclint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Home
 * Date: 08.10.13
 * Time: 15:23
 * To change this template use File | Settings | File Templates.
 */
public class Mouse {


    private static void addComponentsToPane(Container pane){
        JPanel panel = new JPanel();
        final Label label = new Label();
        panel.setPreferredSize(new Dimension(300,300));
        panel.add(label,BorderLayout.SOUTH );
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                label.setText(""+ e.getPoint().getX());
            }
        });

        pane.add(panel);
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Test mouse listener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        JPanel panel = new JPanel();

        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
