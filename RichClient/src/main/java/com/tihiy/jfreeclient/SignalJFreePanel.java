package com.tihiy.jfreeclient;


import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignalJFreePanel extends AbstractViewPanel {
//    private int fDiscret = 500;
    public SignalJFreePanel(List<List<Double>> data, List<String> names){
        List<XYSeries> listOfseries = bindSeries(data, names);
        ChartPanel chartPanel = new ChartPanel(getChart(listOfseries));
        chartPanel.getChart().getXYPlot().getRangeAxis().setRange(autoRange(data));
        add(chartPanel);
    }
    public SignalJFreePanel(List<List<Double>> data, List<List<Double>> args, List<String> names){
        List<XYSeries> listOfseries = bindSeries(data, args, names);
        ChartPanel chartPanel = new ChartPanel(getChart(listOfseries));
        chartPanel.getChart().getXYPlot().getRangeAxis().setRange(autoRange(data));
        add(chartPanel);
    }

    private void initPanel(){

    }

    private JFreeChart getChart(List<XYSeries> series){
        XYSeriesCollection xydataset = new XYSeriesCollection(); // добавление контейнера для построение 1 графика
        xydataset.setAutoWidth(true);
        series.forEach(xydataset::addSeries);
        JFreeChart chartProc = ChartFactory.createXYLineChart("", "i", "mV", xydataset, PlotOrientation.VERTICAL, true, true, true);//построение
        XYPlot plot = (XYPlot)chartProc.getPlot();
        NumberAxis axis = (NumberAxis)plot.getRangeAxis();
        return chartProc;
    }

    private List<XYSeries> bindSeries(List<List<Double>> data, List<String> names){
        List<XYSeries> listOfSeies = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            XYSeries series = new XYSeries(names.get(i));
            int j = 0;
            for (Double d : data.get(i)) {
                series.add(j++, d);
            }
            listOfSeies.add(series);
        }
        return listOfSeies;
    }
    private List<XYSeries> bindSeries(List<List<Double>> data, List<List<Double>> args,  List<String> names){
        List<XYSeries> listOfSeies = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            XYSeries series = new XYSeries(names.get(i));
            for (int i1 = 0; i1 < data.get(i).size(); i1++) {
                series.add((args.get(i)).get(i1), (data.get(i)).get(i1));
            }
            listOfSeies.add(series);
        }
        return listOfSeies;
    }

    private Range autoRange(List<List<Double>> data){
        List<Double> listOfLocalMax = new ArrayList<>();
        List<Double> listOfLocalMin = new ArrayList<>();
        data.forEach(list -> {
            listOfLocalMax.add(Collections.max(list));
            listOfLocalMin.add(Collections.min(list));
        });
        return new Range(Collections.min(listOfLocalMin), Collections.max(listOfLocalMax));
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }
}
