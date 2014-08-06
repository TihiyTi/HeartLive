package com.tihiy.fxclient;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignalPanel extends ScrollPane {
    private List<Signal> listOfSignal = new ArrayList<>();
    private int numOfSignal =0;

    public SignalPanel(List<List<Double>> list){
        int i = 1;
        for(List<Double> signal: list){
            listOfSignal.add(new Signal(signal, i));
        }
    }

    public SignalPanel(int num, boolean flag){
        numOfSignal = num;
        for(int i = 1; i <= num; i++){
            listOfSignal.add(new Signal(i));
        }
        Pane pane = new Pane();
        for(Signal signal: listOfSignal){
            pane.getChildren().addAll(signal.getPolyline());
        }
        setContent(pane);
    }

    public void addSignal(List<Double> signal, int position){
        listOfSignal.add(new Signal(signal, position));
    }

    class Signal{
        Polyline line;
        double max;
        double min;
        int position;

        Signal(double[] array, int position){
            line = fillPolyline(array);
            this.position = position;
        }
        Signal(List<Double> signal, int position){
            line = fillPolyline(signal);
            this.position = position;
        }
        Signal(int position){
            this.position = position;
            autoFillPolyline();
            settings();
        }

        Polyline getPolyline(){
            return line;
        }

        private Polyline fillPolyline(double[] array){
            double[] polyArray = new double[array.length*2];
            max = array[0];
            min = array[0];
            for(int i = 1; i < array.length; i++){
                if(max < array[i]){max = array[i];}
                if(min > array[i]){min = array[i];}
                polyArray[i*2] = i;
                polyArray[i*2 + 1] = array[i];
            }
            line = new Polyline(polyArray);
            return line;
        }
        private Polyline fillPolyline(List<Double> signal){
            double[] polyArray = new double[signal.size()];
            max = Collections.max(signal);
            min = Collections.min(signal);
            for(int i = 1; i < signal.size(); i++){
                polyArray[i*2] = i;
                polyArray[i*2 + 1] = signal.get(i);
            }
            line = new Polyline(polyArray);
            return line;
        }

        private void autoFillPolyline(){
            double[] array = new double[1000];
            for(int i = 0; i <1000; i++){
                array[i] = i%100;
            }
            max = 100;
            min = 0;
            line = fillPolyline(array);
        }

        private void settings(){
            line.scaleYProperty().bind(Bindings.divide(Bindings.add(heightProperty(), -50), numOfSignal * (max - min)));
            line.translateYProperty().bind(
                    Bindings.add(-0.5 * (max - min),
                            Bindings.multiply((double) position - 0.5,
                                    Bindings.divide(heightProperty(), numOfSignal))));
        }
    }
}
