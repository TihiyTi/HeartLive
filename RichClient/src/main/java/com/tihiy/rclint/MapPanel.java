package com.tihiy.rclint;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

import javax.swing.*;
import java.awt.*;

public class MapPanel{
    double [][] potentialMap = new double[3][0];

    public JPanel createDemoPanel()
    {
        return new ChartPanel(createChart(createMapDataset()));
    }

    private XYZDataset createMapDataset(){
        XYZDataset dataset = new DefaultXYZDataset();
        ((DefaultXYZDataset)dataset).addSeries(2, potentialMap);
        return dataset;
    }

    public void setPotentialMap(double[][] potentialMap) {
        this.potentialMap = potentialMap;
    }

    public void addPoint(double vertPlace, double gorizPlace, double level){
        int size = potentialMap[0].length;
        potentialMap[0][size] = vertPlace;
        potentialMap[1][size] = gorizPlace;
        potentialMap[2][size] = level;
    }

    private static JFreeChart createChart(XYZDataset xyzdataset)
    {
        NumberAxis numberaxis = new NumberAxis("X");
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setLowerMargin(0.0D);
        numberaxis.setUpperMargin(0.0D);
        numberaxis.setAxisLinePaint(Color.white);
        numberaxis.setTickMarkPaint(Color.white);
        NumberAxis numberaxis1 = new NumberAxis("Y");
        numberaxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis1.setLowerMargin(0.0D);
        numberaxis1.setUpperMargin(0.0D);
        numberaxis1.setAxisLinePaint(Color.white);
        numberaxis1.setTickMarkPaint(Color.white);
        XYBlockRenderer xyblockrenderer = new XYBlockRenderer();
        PaintScale graypaintscale = new GrayPaintScale(-2.0D, 1.0D);
        xyblockrenderer.setPaintScale(graypaintscale);
        XYPlot xyplot = new XYPlot(xyzdataset, numberaxis, numberaxis1, xyblockrenderer);
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinesVisible(false);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5.0D, 5.0D, 5.0D, 5.0D));
        xyplot.setOutlinePaint(Color.blue);
        JFreeChart jfreechart = new JFreeChart("XYBlockChartDemo1", xyplot);
        jfreechart.removeLegend();
        NumberAxis numberaxis2 = new NumberAxis("Scale");
        numberaxis2.setAxisLinePaint(Color.white);
        numberaxis2.setTickMarkPaint(Color.white);
        numberaxis2.setTickLabelFont(new Font("Dialog", 0, 7));
        PaintScaleLegend paintscalelegend = new PaintScaleLegend(new GrayPaintScale(), numberaxis2);
        paintscalelegend.setAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        paintscalelegend.setAxisOffset(5.0D);
        paintscalelegend.setMargin(new RectangleInsets(5.0D, 5.0D, 5.0D, 5.0D));
        paintscalelegend.setFrame(new BlockBorder(Color.red));
        paintscalelegend.setPadding(new RectangleInsets(10.0D, 10.0D, 10.0D, 10.0D));
        paintscalelegend.setStripWidth(10.0D);
        paintscalelegend.setPosition(RectangleEdge.RIGHT);
        paintscalelegend.setBackgroundPaint(new Color(120, 120, 180));
        jfreechart.addSubtitle(paintscalelegend);
        jfreechart.setBackgroundPaint(new Color(180, 180, 250));
        return jfreechart;
    }
}
