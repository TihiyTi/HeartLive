package com.tihiy.fxclient.element;

import com.tihiy.fxclient.element.interfaces.AbstractSignalController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class LineChartController extends AbstractSignalController implements Initializable{
    public LineChart<Number,Number> chart;
    public NumberAxis xAxis;
    public NumberAxis yAxis;
    public String id;
    ListProperty<Double> listXProperty = new SimpleListProperty<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            bindAll();
    }

    @Override
    public void bindSignal() {
        if (models.size()!=0){
            models.forEach(e -> {
                e.getSignal().addListener((observable, oldValue, newValue) -> {
                    XYChart.Series<Number, Number> series = new XYChart.Series<>();
                    series.setName(e.getName().getValue());
                    for (int i = 0; i < newValue.size(); i++) {
                        series.getData().add(new XYChart.Data<>(1. * i / e.getSampleFreq().getValue(), newValue.get(i)));
                    }
                    chart.getData().add(series);
                });

            });
        }
    }

    @Override
    public void bindName() {
        chart.setTitle("Test title");
    }

    @Override
    public void bindSampleFreq() {
    }
}
