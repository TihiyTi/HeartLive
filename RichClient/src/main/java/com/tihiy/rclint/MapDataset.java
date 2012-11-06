package com.tihiy.rclint;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYZDataset;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 06.11.12
 * Time: 23:13
 */
public class MapDataset implements XYZDataset {
    double [][] potentialMap;

    public void setPotentialMap(double[][] potentialMap) {
        this.potentialMap = potentialMap;
    }

    @Override
    public int getSeriesCount() {
        return 1;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getItemCount(int i) {
        return 10000;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getXValue(int i, int i1) {
        return i;
    }

    @Override
    public double getYValue(int i, int i1) {
        return i1;
    }

    @Override
    public Number getZ(int i, int i1) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Number getX(int i, int i1) {
        return null;
    }

    @Override
    public Number getY(int i, int i1) {
        return null;
    }

    @Override
    public double getZValue(int i, int i1) {
        return 0;
    }

    @Override
    public DomainOrder getDomainOrder() {
        return DomainOrder.ASCENDING;
    }

    @Override
    public Comparable getSeriesKey(int i) {
        return null;
    }

    @Override
    public void addChangeListener(DatasetChangeListener datasetChangeListener) {
    }

    @Override
    public int indexOf(Comparable comparable) {
        return 0;
    }

    @Override
    public void removeChangeListener(DatasetChangeListener datasetChangeListener) {
    }

    @Override
    public DatasetGroup getGroup() {
        return null;
    }

    @Override
    public void setGroup(DatasetGroup datasetGroup) {
    }
}
