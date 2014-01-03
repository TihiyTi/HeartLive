package com.tihiy.rclint.addon;

import javax.swing.*;
import java.awt.*;

public interface AddOnInterface <T> {
    public void paint(Graphics g, JComponent component);
    public void setState(T state);
}
