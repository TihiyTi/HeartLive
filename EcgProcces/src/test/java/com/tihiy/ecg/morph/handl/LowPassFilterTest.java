package com.tihiy.ecg.morph.handl;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 01.10.12
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class LowPassFilterTest {
    @Test
    public void testToDoOperation() throws Exception {
        LowPassFilter lowPassFilter = new LowPassFilter();
        List<Float> list = new ArrayList<Float>();
        List<Float> returnList;
        List<Float> checkList = new ArrayList<>();
        lowPassFilter.setData(list);
        returnList = lowPassFilter.getData();

        checkList.add(1f);
        checkList.add(64.125f);
        checkList.add(3f/32+8-32);

        list.add(32f);
        lowPassFilter.toDoOperation();
        list.add(4f);
        lowPassFilter.toDoOperation();
        list.add(3f);
        lowPassFilter.toDoOperation();
        Assert.assertArrayEquals(checkList.toArray(), returnList.toArray());
    }
}
