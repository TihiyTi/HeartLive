package com.tihiy.comm.parse;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reo32Parser {
    Map<Signal, List<Double>> map;

    public Reo32Parser() {
        map = new HashMap<>();
        map.put(Signal.Pulse_1, new ArrayList<Double>());
        map.put(Signal.Pulse_2, new ArrayList<Double>());
        map.put(Signal.Pulse_3, new ArrayList<Double>());
        map.put(Signal.Pulse_4, new ArrayList<Double>());
        map.put(Signal.Pulse_5, new ArrayList<Double>());
        map.put(Signal.Pulse_6, new ArrayList<Double>());
        map.put(Signal.Base_1, new ArrayList<Double>());
        map.put(Signal.Base_2, new ArrayList<Double>());
        map.put(Signal.Base_3, new ArrayList<Double>());
        map.put(Signal.Base_4, new ArrayList<Double>());
        map.put(Signal.Base_5, new ArrayList<Double>());
        map.put(Signal.Base_6, new ArrayList<Double>());
        map.put(Signal.ECG, new ArrayList<Double>());
        map.put(Signal.P_TRCG, new ArrayList<Double>());
    }


    public Map<Signal,List<Double>> parse(String string){
//        CharMatcher trimmer = CharMatcher.JAVA_LETTER;
        Splitter splitter = Splitter.on("\n").trimResults().omitEmptyStrings();
        List<String> list = splitter.splitToList(string);
        for(int i = 2 ; i < list.size(); i++){
            splitter = Splitter.on("\t").trimResults().omitEmptyStrings();
            List<String> st = splitter.splitToList(list.get(i));

            map.get(Signal.Pulse_1).add(Double.valueOf(st.get(0)));
            map.get(Signal.Pulse_2).add(Double.valueOf(st.get(2)));
            map.get(Signal.Pulse_3).add(Double.valueOf(st.get(4)));
            map.get(Signal.Pulse_4).add(Double.valueOf(st.get(6)));
            map.get(Signal.Pulse_5).add(Double.valueOf(st.get(8)));
            map.get(Signal.Pulse_6).add(Double.valueOf(st.get(10)));
            map.get(Signal.Base_1).add(Double.valueOf(st.get(1)));
            map.get(Signal.Base_2).add(Double.valueOf(st.get(3)));
            map.get(Signal.Base_3).add(Double.valueOf(st.get(5)));
            map.get(Signal.Base_4).add(Double.valueOf(st.get(7)));
            map.get(Signal.Base_5).add(Double.valueOf(st.get(9)));
            map.get(Signal.Base_6).add(Double.valueOf(st.get(11)));

            map.get(Signal.ECG).add(Double.valueOf(st.get(st.size()-1)));
        }
        return map;
    }

    public enum Signal {
        Pulse_1,Base_1,Pulse_2,Base_2,Pulse_3,Base_3,
            Pulse_4,Base_4,Pulse_6,Base_6,Pulse_5,Base_5,
            ECG,P_TRCG;
        List<Number> list;
        Signal(){
            list = new ArrayList<>();
        }
    }
}
