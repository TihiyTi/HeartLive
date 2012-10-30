package com.tihiy.ecg.morph.pantom;

import com.tihiy.ecg.AbstractHandler;
import com.tihiy.ecg.morph.AbstractRFinder;
import com.tihiy.ecg.morph.pantom.handl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 28.09.12
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class PanTompkinsAlgorithm extends AbstractRFinder {
    public DeciderPnT deciderPnT =  new DeciderPnT(frequency);
    List list = new ArrayList();

    public PanTompkinsAlgorithm(List<Float> data, int frequency) {
        super(data, frequency);
    }

    public void initPanTompkins(){
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
        }
        // ToDo Develop some object that POINT on the ECG.
        // In this place create POINT and init thought method createPoint of DeciderPnT
    }

    public void stopPanTompkins(){

    }
}