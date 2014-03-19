package com.tihiy.rclint.mvcAbstract;

import javax.swing.*;
import java.awt.*;

public interface ViewAddOnInterface<T> {
    public void paint(Graphics g, JComponent component);
    public void setState(T state);
}
