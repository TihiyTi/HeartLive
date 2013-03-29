package com.tihiy.comm;

import com.tihiy.rclint.mvcAbstract.AbstractModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 28.03.13
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSignalManager extends AbstractModel implements SignalReturn {

    private Map portMap = new HashMap();

    @Override
    public void getSamples(double[] samples, String flowName){
        if(!portMap.containsKey(flowName)){

        }
    }

}
