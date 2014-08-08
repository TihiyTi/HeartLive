package com.tihiy.fxclient;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignalPanel extends ScrollPane {
    boolean inverseSignals = true;
    private List<Signal> listOfSignal = new ArrayList<>();
    private int numOfSignal =0;

    public SignalPanel(List<List<Double>> list){
        numOfSignal = list.size();
        int i = 1;
        for(List<Double> signal: list){
            listOfSignal.add(new Signal(signal, i));
            i++;
        }
        Pane pane = new Pane();
        for(Signal signal: listOfSignal){
            pane.getChildren().addAll(signal.getPolyline());
        }
        setContent(pane);
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
            this.position = position;
            line = fillPolyline(signal);
            settings();
            layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
                @Override
                public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                    for (Signal signal : listOfSignal) {
                        Polyline line = signal.getPolyline();
                        System.out.println("Signal " + signal.position + " from " + numOfSignal +" max="+max+" min="+min +" itog=" +(numOfSignal * (max - min)));
                        System.out.println("Panel height = " + heightProperty().getValue());
                        System.out.println("Line scale = " + line.scaleYProperty().getValue());
                        System.out.println("Line translate = " + line.translateYProperty().getValue());
                    }
                }
            });
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
            double[] polyArray = new double[signal.size()*2];
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
            System.out.println("ITOG = "+ numOfSignal * (max - min));
            line.scaleYProperty().bind(Bindings.divide(Bindings.add(heightProperty(), -50), -numOfSignal * (max - min)));
            line.translateYProperty().bind(
                    Bindings.add( -0.5 * (max + min),
                            Bindings.multiply((double) position - 0.5,
                                    Bindings.divide(heightProperty(), numOfSignal))));
        }
    }
}
