package com.tihiy.comm.parse;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.*;

public class Reo32Parser {
    Map<Signal, List<Number>> map;

    public Reo32Parser() {
        map = new HashMap<>();
        map.put(Signal.ECG, new ArrayList<Number>());
        map.put(Signal.P_TRCG, new ArrayList<Number>());
    }


    public Map<Signal,List<Number>> parse(String string){
//        CharMatcher trimmer = CharMatcher.JAVA_LETTER;
        Splitter splitter = Splitter.on("\n").trimResults().omitEmptyStrings();
        List<String> list = splitter.splitToList(string);
        for(int i = 2 ; i < list.size(); i++){
            splitter = Splitter.on("\t").trimResults().omitEmptyStrings();
            List<String> st = splitter.splitToList(list.get(i));
            map.get(Signal.ECG).add(Double.valueOf(st.get(1)));
            map.get(Signal.P_TRCG).add(Double.valueOf(st.get(2)));
        }
        return new HashMap<Signal,List<Number>>();
    }

    enum Signal {
        Pulse_1,Base_1,ECG,P_TRCG;
        List<Number> list;
        Signal(){
            list = new ArrayList<>();
        }
    }
}
