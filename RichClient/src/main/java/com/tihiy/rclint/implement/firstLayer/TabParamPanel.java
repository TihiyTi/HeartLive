package com.tihiy.rclint.implement.firstLayer;

import com.ak.util.GenericStorage;
import sun.net.www.content.text.Generic;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TabParamPanel extends JTabbedPane{

    public TabParamPanel() {
        initComponent();
    }

    private void initComponent() {
        for(int i=0; i <5; i++){
            addTab(""+(i+1), new ParamPanel());
        }
        loadParam();
    }

    protected List<double[]> getParam(){
        List<double[]> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            setSelectedIndex(i);
            ParamPanel panel = (ParamPanel) getSelectedComponent();
            double[] array = panel.getParam();
            list.add(array);
        }
        GenericStorage.newMapStorage("param", "").save((Map<String, List<double[]>>) (new HashMap()).put("param",list));
        return list;
    }
    protected void loadParam(){
        Map<String, List<double[]>> map = GenericStorage.newMapStorage("param", "").load();
        List<double[]> list = (List<double[]>) GenericStorage.newMapStorage("param", "").load().get("param");
        if(list!=null){
            for(int i = 0; i < 5; i++){
                ((ParamPanel)getTabComponentAt(i)).loadParam(list.get(i));
            }
        }
    }
}
