package com.tihiy.ecg.morph.pantom;

import com.tihiy.ecg.AbstractHandler;
import com.tihiy.ecg.morph.AbstractRFinder;
import com.tihiy.ecg.morph.pantom.handl.*;

import java.util.ArrayList;
import java.util.List;

public class PanTompkinsAlgorithm extends AbstractRFinder {
    public DeciderPnT deciderPnT =  new DeciderPnT(frequency);
    List<Float> list = new ArrayList<Float>();

    public PanTompkinsAlgorithm(List<Float> data, int frequency) {
        super(data, frequency);
        initPanTompkins();
    }

    public final void initPanTompkins(){
        addHandler(new LowPassFilter());
        addHandler(new HighPassFilter());
        addHandler(new DirivativeFilter());
        addHandler(new Squaring());
        addHandler(new IntegrationWindow());
        // TODO add decider and set input and origin data
    }

    public void toDoOperation(){
        for (AbstractHandler handler : handlers) {
            handler.toDoOperation();
            deciderPnT.findPoint();
        }
        // ToDo Develop some object that POINT on the ECG.
        // In this place create POINT and init thought method createPoint of DeciderPnT
    }

//    public void stopPanTompkins(){
//
//    }
}