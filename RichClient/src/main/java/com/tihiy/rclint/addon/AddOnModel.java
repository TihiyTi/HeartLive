package com.tihiy.rclint.addon;

import com.tihiy.rclint.mvcAbstract.AddOnModelInterface;

import java.util.ArrayList;
import java.util.List;

public class AddOnModel implements AddOnModelInterface<double[]> {

    List<Integer> max;

    @Override
    public void prepareState(double[] dataForState) {
        if(dataForState.length > 2){
            max = new ArrayList<>();
            for(int i = 1; i < dataForState.length -1; i++){
                if((dataForState[i-1] < dataForState[i]) && (dataForState[i+1] < dataForState[i])){
                    max.add(i);
                }
            }
        }
    }

    @Override
    public Object getState() {
        return max;
    }
}
