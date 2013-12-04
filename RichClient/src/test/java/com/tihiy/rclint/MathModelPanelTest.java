package com.tihiy.rclint;
import org.junit.Test;
import javax.swing.*;

public class MathModelPanelTest {

    @Test
    public void test(){
        //Create and set up the window.
        JFrame frame = new JFrame("Test mouse listener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.

//        File file = new File(getClass().getResource("img1.jpg").getFile());
        JPanel panel = new MathModelPanel();

        //Display the window.
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        while(frame.isVisible()){};
    }
}