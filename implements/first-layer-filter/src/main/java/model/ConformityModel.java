package model;

import com.tihiy.rclint.mvcAbstract.AbstractModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConformityModel extends AbstractModel {
    private final String name;
    private List<String> inItem;
    private List<String> outItem;

    private Map dataMap;

    private Map<String, String> confirmMap = new HashMap<>();

    public ConformityModel(String name) {
        this.name = name;
    }

    public void setInItem(List<String> inItem) {
        this.inItem = inItem;
        firePropertyChange("inItem", Collections.emptyList(), inItem);
    }

    public void setOutItem(List<String> outItem) {
        this.outItem = outItem;
        firePropertyChange("outItem", Collections.emptyList(), outItem);
    }

    public void setDataMap(Map dataMap) {
        this.dataMap = dataMap;
    }

    public Map<String,List<Double>> getDataMap() {
        return dataMap;
    }

    public void setConfirmMap(Map<String, String> confirmMap) {
        this.confirmMap = confirmMap;
        firePropertyChange("confirmMap", Collections.emptyMap(), confirmMap);
    }

    public Map<String, String> getConfirmMap() {
        return confirmMap;
    }

}
