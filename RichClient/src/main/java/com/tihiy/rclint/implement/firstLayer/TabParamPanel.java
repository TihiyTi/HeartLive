package com.tihiy.rclint.implement.firstLayer;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class TabParamPanel extends JTabbedPane{

    public TabParamPanel() {
        initComponent();
    }

    private void initComponent() {
        for(int i=0; i <5; i++){
            addTab(""+(i+1), new ParamPanel());
        }
    }

    protected Map getParam(){
        Map map = new HashMap<Integer, double[]>();
        for(int i = 0; i < 5; i++){
            map.put((i+1), ((ParamPanel)getTabComponentAt(i)).getParam());
        }
        return map;
    }
}
