import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConformityPanel  extends AbstractViewPanel{
    private List<String> inItem;
    private List<String> outItem;
    private Map<String, JComboBox<String>> comboBoxes = new HashMap<>();
    private Map<String, String> confirmMap = new HashMap<>();
    private JButton save = new JButton("ConfirmSignal");

    public ConformityPanel(ThisController mc) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.RED));
        save.addActionListener(e -> mc.confirmSignal(getMap()));
    }

    private void setInItems(List<String> usedItem){
        this.inItem = usedItem;
        initComponentsNew();
    }
    private void setOutItems(List<String> haveItem){
        this.outItem = haveItem;
        initBoxes();
    }

    private void initComponent() {
        int numOfItem = inItem.size();
        GridBagConstraints constraints = new GridBagConstraints(0,0, 1,1, 0,0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,5,0,5), 0,0);
        for (int i = 0; i < numOfItem; i++) {
            constraints.gridy = i;
            constraints.gridx = 0;
            add(new Label(inItem.get(i)), constraints);
            constraints.gridx = 1;
            if(outItem !=null){
                JComboBox<String> box = new JComboBox<>(outItem.toArray(new String[outItem.size()]));
                box.addActionListener(new BoxListener(inItem.get(i)));
                comboBoxes.put(inItem.get(i), box);
                add(box, constraints);
            }
            constraints.gridx = 2;
            add(new Label(confirmMap.get(inItem.get(i))), constraints);
        }
        constraints.gridy++;
        constraints.gridx = 0;
        add(save, constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        JComboBox<String> box = new JComboBox<>(outItem.toArray(new String[outItem.size()]));
        add(box, constraints);
        box.setSelectedItem(outItem.get(2));
    }
    private void initComponentsNew(){
        int numOfItem = inItem.size();
        GridBagConstraints constraints = new GridBagConstraints(0,0, 1,1, 0,0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,5,0,5), 0,0);
        for (int i = 0; i < numOfItem; i++) {
            constraints.gridy = i;
            constraints.gridx = 0;
            add(new Label(inItem.get(i)), constraints);
            constraints.gridx = 1;

            JComboBox<String> box = new JComboBox();
//            if(outItem.size() > 0){
//                String set = confirmMap.get(inItem.get(i));
//                box.setSelectedItem(set);
//                comboBoxes.get(inItem.get(i)).setSelectedItem();
//                System.out.println("Ob1 = "+((Object)outItem.get(i)).toString());
//                System.out.println("Ob2 = "+confirmMap.get(inItem.get(i)).toString());
//                box.setSelectedItem(outItem.get(i));
//            }

            box.addActionListener(new BoxListener(inItem.get(i)));
            comboBoxes.put(inItem.get(i), box);
            add(box, constraints);
            constraints.gridx = 2;
            add(new Label(confirmMap.get(inItem.get(i))), constraints);
        }
        constraints.gridy++;
        constraints.gridx = 0;
        add(save, constraints);
    }
    private void initBoxes(){
        comboBoxes.values().forEach(e-> {outItem.forEach(w -> e.addItem(w));});
    }

    private void settingBoxes(){
        comboBoxes.keySet().forEach(e -> {
            String item = confirmMap.get(e);
            JComboBox box = comboBoxes.get(e);
            box.setSelectedItem(item);
//                comboBoxes.get(e).setSelectedItem(map.get(e)));
        });
    }

    private Map<String, String> getMap(){
        return confirmMap;
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("inItem")){
            List<String> inItem = (List<String>)evt.getNewValue();
            setInItems(inItem);
        }
        if(evt.getPropertyName().equals("outItem")){
            List<String> outItem = (List<String>)evt.getNewValue();
            setOutItems(outItem);
        }
        if(evt.getPropertyName().equals("confirmMap")){
            System.out.println("Auto settings confirm map from database not support ");
            Map<String,String> map = (Map<String,String>)evt.getNewValue();
            confirmMap = map;
//            initComponentsNew();
            settingBoxes();
        }
    }

    class BoxListener implements ActionListener{
        String name;
        BoxListener(String name){
            this.name = name;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String item = (String) ((JComboBox)e.getSource()).getSelectedItem();
            confirmMap.put(name, item);
        }
    }
}
