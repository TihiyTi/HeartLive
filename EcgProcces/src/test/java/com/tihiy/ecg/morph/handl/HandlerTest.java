package com.tihiy.ecg.morph.handl;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 01.10.12
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class HandlerTest {
    List<Float> startList = new ArrayList<Float>();
    List<Float> checkLowPassFilter = Arrays.asList(1f/32, 2.0625f, 3.09375f, 4.125f, 5.15625f);
    List<Float> checkHighPassFilter = ArrayList.asList();
    List<Float> returnList;

    @Test
    public void testLowPassFilter() throws Exception {
        Handler lowPassFilter = new LowPassFilter();
        lowPassFilter.setData(startList);
        procces(lowPassFilter);
        Assert.assertArrayEquals(checkLowPassFilter.toArray(), returnList.toArray());
    }

    private void procces(Handler filter){
        filter.setData(startList);
        returnList = filter.getData();
        for (int i = 1; i < 6; i++) {
             startList.add((float)i);
             filter.toDoOperation();
        }
    }

}
