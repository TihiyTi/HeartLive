package com.tihiy.rclint.control;

import com.tihiy.rclint.ReadingFiles;
import com.tihiy.rclint.models.SignalModel;
import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractModel;
import com.tihiy.reo.ReoPostProcessor;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

    public void addSignal(String name, File file) throws IOException {
        List<Double> list =  ReadingFiles.readFile(file);
        ((SignalModel)registeredModels.get(name)).setList(list);
    }
    public void calculate(double[] main, double[] first){
        System.out.println("Size A = " + main[0]);
        ReoPostProcessor rp = new ReoPostProcessor();
        rp.setMainMeasurement(main[0], main[1], main[2], main[3], main[4], main[5], ((SignalModel)registeredModels.get("sourceSignal")).getList());
        rp.setFirstLayerMeasurement(first[0], first[1], first[2], first[3], first[5], ((SignalModel)registeredModels.get("targetSignal")).getList());
        List<Double> result = rp.getRadiusWithRo1();
        int i = 0;
        for(Double r: result){
            System.out.println(i + " " + r);
            i++;
        }
        ((SignalModel)registeredModels.get("radiusSignal")).setList(result);
    }



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