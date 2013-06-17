package com.tihiy.rclint.control;

import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Controller extends AbstractController {

    private final Map<String, AbstractModel> registeredModels = new HashMap<>();
    private final Map<String, BlockingQueue<Integer>> signalMap = new HashMap<>();

//    public static final String COMMAND_RANDOM_LIST = "randomList";
//
//    public void command(String command){
//        applyCommand(command);
//    }

    public void createSignalModel(String flowName, AbstractModel model){
        addModel(model);
    }

    public void addModel(String flowName, AbstractModel model){
        registeredModels.put(flowName, model);
        model.addPropertyChangeListener(this);
    }

    public void modifyModel(String flowName){
//        registeredModels.get(flowName).
    }
}