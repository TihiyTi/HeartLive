package com.tihiy.jfreeclient;

import com.tihiy.jfreeclient.jfreeutil.SignalJFreeSetting;
import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignalJFreePanelMvcStatic extends AbstractViewPanel{
//    private String signalName;
    private List<String> signalNames;
    private Map<String, SignalJFreeSetting> signalMap = new HashMap<>();

    private ChartPanel chartPanel;

    public SignalJFreePanelMvcStatic(SignalJFreeSetting signal) {
        signalMap.put(signal.getName(), signal);

    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

    }

    private void setChartPanel(){
        List<XYSeries> listOfseries = bindSeries();
        XYSeriesCollection xydataset = new XYSeriesCollection();
//        xydataset.setAutoWidth(true);
        listOfseries.forEach(xydataset::addSeries);
        JFreeChart chartProc = ChartFactory.createXYLineChart("", "i", "", xydataset, PlotOrientation.VERTICAL, true, true, true);//построение

        chartPanel = new ChartPanel(chartProc);
    }
    private List<XYSeries> bindSeries(){
        List<XYSeries> listOfSeies = new ArrayList<>();
        for (SignalJFreeSetting signal: signalMap.values()) {
            List<Double> data = signal.getSignalData();
            List<Double> args = signal.getSignalArgs();
            XYSeries series = new XYSeries(signal.getName());
            for (int i = 0; i < data.size(); i++) {
                series.add(data.get(i), args.get(i));
            }
        }
        return listOfSeies;
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }
}
