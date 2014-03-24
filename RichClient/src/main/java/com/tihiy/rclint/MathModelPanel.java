package com.tihiy.rclint;

import java.awt.*;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MathModelPanel extends JPanel{
    private Image image;

    public MathModelPanel(){
        URL url = getClass().getResource("img.jpg");
        String st = url.getFile();
        File file = new File(st);
        image = new ImageIcon(file.getPath()).getImage();
        Dimension size = new Dimension(
                image.getWidth(null), image.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}