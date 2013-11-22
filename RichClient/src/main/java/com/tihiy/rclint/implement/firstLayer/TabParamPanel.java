package com.tihiy.rclint.implement.firstLayer;

import javax.swing.*;
import java.util.*;

public class TabParamPanel extends JTabbedPane{

    public TabParamPanel() {
        initComponent();
    }

    private void initComponent() {
        for(int i=0; i <5; i++){
            addTab(""+(i+1), new ParamPanel());
        }
    }

    protected List getParam(){
        List<double[]> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            list.add(((ParamPanel) getTabComponentAt(i)).getParam());
        }
        return list;
    }
}
