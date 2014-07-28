import com.tihiy.rclint.view.MultiSignalPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.14
 * Time: 13:53
 */
public class GroupSignalPanel extends JPanel{
    ThisController mc;

    public GroupSignalPanel(ThisController mc) {
        this.mc = mc;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponent();
    }

    private void initComponent() {
        add(addPanel(mc.FIRST, null));
        add(addPanel(mc.PRECARD_1, mc.RADIUS_1));
        add(addPanel(mc.PRECARD_2, mc.RADIUS_2));
        add(addPanel(mc.PRECARD_3, mc.RADIUS_3));
        add(addPanel(mc.PRECARD_4, mc.RADIUS_4));
        add(addPanel(mc.PRECARD_5, mc.RADIUS_5));
    }
    private JScrollPane addPanel(String nameSignal, String nameSignal_2){
        Map<String, List<Double>> tempMap = new HashMap<>();
        tempMap.put(mc.ECG, Collections.<Double>emptyList());
        tempMap.put(nameSignal, Collections.<Double>emptyList());
        if(nameSignal_2!=null){
            tempMap.put(nameSignal_2, Collections.<Double>emptyList());
        }
        MultiSignalPanel tempPanel = new MultiSignalPanel(mc,tempMap);
        JScrollPane pane = new JScrollPane(tempPanel);
        tempPanel.setPreferredSize(new Dimension(5000, tempPanel.getHeight()));
        mc.addView(tempPanel);
        return pane;
    }

}
