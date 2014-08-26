import com.tihiy.rclint.view.MultiSignalPanel;
import com.tihiy.rclint.view.MultiSignalPanel2;

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
        String[] signalsArray = new String[]{ThisController.ECG, ThisController.FIRST,/*ThisController.FIRST_OLD, */
            ThisController.PRECARD_1, ThisController.PRECARD_2,ThisController.PRECARD_3, ThisController.PRECARD_4, ThisController.PRECARD_5,
//                ThisController.RADIUS_1, ThisController.RADIUS_2,ThisController.RADIUS_3, ThisController.RADIUS_4, ThisController.RADIUS_5,
                ThisController.CLEAR_1,ThisController.CLEAR_2,ThisController.CLEAR_3,ThisController.CLEAR_4,ThisController.CLEAR_5
        };
        Map<String, Integer>  signalMap = new HashMap<String, Integer>(){{
            put(ThisController.ECG, 1);
            put(ThisController.FIRST, 1);
//            put(ThisController.FIRST_OLD, 1);
            put(ThisController.PRECARD_1, 2);
            put(ThisController.PRECARD_2, 3);
            put(ThisController.PRECARD_3, 4);
            put(ThisController.PRECARD_4, 5);
            put(ThisController.PRECARD_5, 6);
//            put(ThisController.RADIUS_1, 2);
//            put(ThisController.RADIUS_2, 3);
//            put(ThisController.RADIUS_3, 4);
//            put(ThisController.RADIUS_4, 5);
//            put(ThisController.RADIUS_5, 6);
            put(ThisController.CLEAR_1, 2);
            put(ThisController.CLEAR_2, 3);
            put(ThisController.CLEAR_3, 4);
            put(ThisController.CLEAR_4, 5);
            put(ThisController.CLEAR_5, 6);

        }};
        Map<String, Color>  colorMap = new HashMap<String, Color>(){{
            put(ThisController.ECG, Color.RED);
            put(ThisController.FIRST, Color.BLUE);
//            put(ThisController.FIRST_OLD, Color.CYAN.darker());
//            put(ThisController.PRECARD_1, 2);
//            put(ThisController.PRECARD_2, 3);
//            put(ThisController.PRECARD_3, 4);
//            put(ThisController.PRECARD_4, 5);
//            put(ThisController.PRECARD_5, 6);
//            put(ThisController.RADIUS_1, Color.GREEN.darker());
//            put(ThisController.RADIUS_2, Color.GREEN.darker());
//            put(ThisController.RADIUS_3, Color.GREEN.darker());
//            put(ThisController.RADIUS_4, Color.GREEN.darker());
//            put(ThisController.RADIUS_5, Color.GREEN.darker());
            put(ThisController.CLEAR_1, Color.ORANGE.darker());
            put(ThisController.CLEAR_2, Color.ORANGE.darker());
            put(ThisController.CLEAR_3, Color.ORANGE.darker());
            put(ThisController.CLEAR_4, Color.ORANGE.darker());
            put(ThisController.CLEAR_5, Color.ORANGE.darker());

        }};
        add(addMultiPanel2(signalsArray, signalMap, colorMap));
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
    private JScrollPane addMultiPanel2(String[] signals, Map<String, Integer> tempMapPosition, Map<String, Color> mapColor){
        Map<String, List<Double>> tempMap = new HashMap<>();
        tempMap.put(mc.ECG, Collections.<Double>emptyList());
        for(int i = 0; i < signals.length; i++){
            tempMap.put(signals[i], Collections.<Double>emptyList());
        }

        MultiSignalPanel2 tempPanel = new MultiSignalPanel2(mc,tempMap,tempMapPosition);
        tempPanel.colorSignal(mapColor);
        JScrollPane pane = new JScrollPane(tempPanel);
        tempPanel.setScrollPane(pane);
        tempPanel.setPreferredSize(new Dimension(5000, tempPanel.getHeight()));
        mc.addView(tempPanel);
        return pane;
    }

}
