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
        add(addPanel(mc.FIRST));
        add(addPanel(mc.PRECARD_1));
        add(addPanel(mc.PRECARD_2));
        add(addPanel(mc.PRECARD_3));
        add(addPanel(mc.PRECARD_4));
        add(addPanel(mc.PRECARD_5));
    }
    private JScrollPane addPanel(String nameSignal){
        Map<String, List<Double>> tempMap = new HashMap<>();
        tempMap.put(mc.ECG, Collections.<Double>emptyList());
        tempMap.put(nameSignal, Collections.<Double>emptyList());
        MultiSignalPanel tempPanel = new MultiSignalPanel(mc,tempMap);
        JScrollPane pane = new JScrollPane(tempPanel);
        tempPanel.setPreferredSize(new Dimension(5000, tempPanel.getHeight()));
        mc.addView(tempPanel);
        return pane;
    }

}
