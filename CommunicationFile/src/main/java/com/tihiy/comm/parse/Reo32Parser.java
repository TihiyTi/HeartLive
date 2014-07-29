package com.tihiy.comm.parse;

import com.google.common.base.Splitter;
import java.util.*;

public class Reo32Parser {
    List<List<Double>> listOfList;
    int unuseLine = 1;
    int unuseCol = 0;

    public Reo32Parser() {
        listOfList = new ArrayList<>();
    }

    public List<List<Double>> parse(String string){
        List<String> strings = Splitter.on("\n").trimResults().omitEmptyStrings().splitToList(string);
        checkColumn(strings.get(unuseLine));
        for(int i = unuseLine + 1; i < strings.size(); i++){
            List<String> st = Splitter.on("\t").trimResults().omitEmptyStrings().splitToList(strings.get(i));
            int currentColumn = unuseCol;
            for(List<Double> list: listOfList){
                list.add(Double.valueOf(st.get(currentColumn)));
                currentColumn++;
            }
        }
        return listOfList;
    }

    public Reo32Parser skipLine(int unuseLine){
        this.unuseLine = unuseLine;
        return this;
    }
    public Reo32Parser skipColumn(int unuseCol){
        this.unuseCol = unuseCol;
        return this;
    }

    private void checkColumn(String string){
        int size = Splitter.on("\t").trimResults().omitEmptyStrings().splitToList(string).size();
        for(int i = 0; i < size - unuseCol; i++){
            listOfList.add(new ArrayList<Double>());
        }
    }

    enum Signal {
        Pulse_1,Base_1,ECG,P_TRCG;
        List<Number> list;
        Signal(){
            list = new ArrayList<>();
        }
    }
}
