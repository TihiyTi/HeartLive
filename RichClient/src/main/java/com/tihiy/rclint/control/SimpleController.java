package com.tihiy.rclint.control;

import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractModel;

import java.util.HashMap;
import java.util.Map;

public class SimpleController extends AbstractController {
    private final Map<String, AbstractModel> registeredModels = new HashMap<>();

    public void addModel(String flowName, AbstractModel model){
        registeredModels.put(flowName, model);
        model.addPropertyChangeListener(this);
    }
}
