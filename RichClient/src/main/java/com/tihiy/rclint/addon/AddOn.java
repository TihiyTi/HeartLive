package com.tihiy.rclint.addon;
import com.tihiy.rclint.mvcAbstract.AbstractModel;

public class AddOn extends AbstractModel{
    private String addOnName;
    private Integer value = null;
    public AddOn(String parentModelName){
        this.addOnName = parentModelName + "addOn";
    }
    public void setValue(int value){
        firePropertyChange(addOnName, 0, value);
    }
}
