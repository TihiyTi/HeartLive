package com.tihiy.ecg.morph.pantom.handl;

import java.util.List;

public class DeciderPnT {
    enum SetProperties {DEFAULT, NOISE, SIGNAL}

    private List<Float> startData;

    private List<Float> originData;

    private int frequency;
    private float peak = 0;
    private float spki, npki, threshold_1, threshold_2;
    private boolean flagMoreThr = false;
    // TODO QSms adjustable
    private final int QSms = 100; // max value of QRS length, Gabriel Khan Rapid ECG interpretation, p44
    private final int Wms = 150;
    public DeciderPnT(int frequency) {
        this.frequency = frequency;
    }


    public Integer findPoint(){
        int size = startData.size();
        int last = size - 1;
        float currentPoint = startData.get(last);
        float previousPoint = startData.get(last - 1);
        if(size <= frequency){
            if(size > frequency/3){
                if(currentPoint > peak){
                    peak = currentPoint;
                }
            }else{
                if(size == frequency){
                    setThreshold(SetProperties.DEFAULT);
                }
            }
        }else{
            if(currentPoint > threshold_2){
                if(flagMoreThr){
                    if(peak < currentPoint){
                        peak = currentPoint;
                    }
                }else{
                    flagMoreThr = true;
                    peak = currentPoint;
                }
            }else{
                if(flagMoreThr){
                    flagMoreThr = false;
                    if(peak > threshold_1){
                        setThreshold(SetProperties.SIGNAL);// peak of signal
                        return findPointOnOriginECG();
                    }else{
                        setThreshold(SetProperties.NOISE);
                    }
                }
            }
        }
        return null;
    }

    private void setThreshold(SetProperties setProperties){
        if(setProperties.equals(SetProperties.SIGNAL)){
            spki = 0.125f*peak + 0.875f*spki;
        }else{
            if(setProperties.equals(SetProperties.NOISE)){
                npki = 0.125f*peak + 0.875f*spki;
            }else{
                if(setProperties.equals(SetProperties.DEFAULT)){
                    spki = peak;
                    npki = 0.125f * peak;
                }
            }
        }
        threshold_1 = npki + 0.25f*(spki - npki);
        threshold_2 = 0.5f*threshold_1;
    }

    // TODO (last - windowBegin) may be less than array size!! do something
    private int findPointOnOriginECG(){
        int size = startData.size();
        int last = size - 1;
        int windowBegin = (int)((Wms + QSms)*frequency/1000f);
        int windowEnd = (int)(Wms*frequency/1000f);
        int fromLastToR = 0;
        float amplitudePeak = 0;
        for(int i = (last - windowBegin); i < (last - windowEnd); i++){
            float currentPoint = originData.get(i);
            if(currentPoint > amplitudePeak){
                amplitudePeak = currentPoint;
                fromLastToR = last - i;
            }
        }
        return fromLastToR;
    }

    public void setData(List<Float> startData) {
        this.startData = startData;
    }

    public void setOriginData(List<Float> originData) {
        this.originData = originData;
    }
}
