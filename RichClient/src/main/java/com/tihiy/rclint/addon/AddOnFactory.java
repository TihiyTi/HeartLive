package com.tihiy.rclint.addon;

import com.tihiy.rclint.mvcAbstract.AbstractController;

public class AddOnFactory {
    private static AddOnFactory ourInstance = new AddOnFactory();
    private AbstractController mc;

    public static AddOnFactory getInstance(AbstractController mc) {
        ourInstance.mc = mc;
        return ourInstance;
    }

    private AddOnFactory() {
    }

    public void addSimpleAddOn(String modelName){
        mc.addAddOn(modelName, new AddOn(), new AddOnModel());
    }
}
