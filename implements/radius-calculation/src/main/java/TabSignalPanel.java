import com.tihiy.rclint.control.Controller;
import com.tihiy.rclint.view.SignalPanel;
import com.tihiy.rclint.view.SignalPanelLite;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class TabSignalPanel extends JTabbedPane {
    ControllerFLayer mc;
    SignalMapper mapper;
    private int numOfTabs = 5;
    private int numOfPanels = 3;

    public TabSignalPanel(ControllerFLayer mc, SignalMapper mapper) {
        this.mc = mc;
        this.mapper = mapper;
        initComponent();
    }

    private void initComponent() {
        for(int tab = 0; tab < numOfTabs; tab++){
            addTab(""+(tab+1), addSingleTab(mapper.getColumn(tab)));
        }
    }

    private Component addSingleTab(String[] signals){
        JPanel panel = new JPanel(new BorderLayout());
//        for(int pan = 0; pan < numOfPanels; pan++){
//            SignalPanelLite s = addPanel(signals[pan]);
//            panel.add(s, BorderLayout.NORTH);
//        }
        SignalPanelLite s1 = addPanel(signals[0]);
        SignalPanelLite s2 = addPanel(signals[1]);
        SignalPanelLite s3 = addPanel(signals[2]);
        panel.add(s1, BorderLayout.NORTH);
        panel.add(s2, BorderLayout.CENTER);
        panel.add(s3, BorderLayout.SOUTH);
        return panel;
    }

    private Component __addSingleTab(String precardio, String first, String radius){
        JPanel panel = new JPanel(new BorderLayout());
        SignalPanelLite s1 = addPanel(precardio);
        SignalPanelLite s2 = addPanel(first);
        SignalPanelLite s3 = addPanel(radius);
        panel.add(s1, BorderLayout.NORTH);
        panel.add(s2, BorderLayout.CENTER);
        panel.add(s3, BorderLayout.SOUTH);
        return panel;
    }

    private SignalPanelLite addPanel(String nameSignal){
//        SignalPanel tempPanel = new SignalPanel(mc, Collections.<Double>emptyList(), nameSignal);
        SignalPanelLite tempPanel = new SignalPanelLite(nameSignal);
        mc.addView(tempPanel);
        return tempPanel;
    }

    private void setNumOfTabs(int tabs){
        numOfTabs = tabs;
    }
    private void setNumOfPanel(int panels){
        numOfPanels = panels;
    }
}
