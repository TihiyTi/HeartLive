package com.tihiy.ecg.morph.pantom.handl;

import com.tihiy.ecg.AbstractHandler;
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
    List<Float> checkHighPassFilter = Arrays.asList(-0.03125f, 0.9375f, 1.90625f,2.875f, 3.84375f);
    List<Float> checkDerivativeFilter = Arrays.asList(0.2f, 0.5f, 0.8f, 1f, 1f);
    List<Float> returnList;

    @Test
    public void testLowPassFilter() throws Exception {
        AbstractHandler lowPassFilter = new LowPassFilter();
        procces(lowPassFilter);
        Assert.assertArrayEquals(checkLowPassFilter.toArray(), returnList.toArray());
    }
    @Test
    public void testHighPassFilter() throws  Exception{
        AbstractHandler highPassFilter = new HighPassFilter();
        procces(highPassFilter);
        Assert.assertArrayEquals(checkHighPassFilter.toArray(), returnList.toArray());
    }
    @Test
    public void testDerivativeFilter() throws Exception{
        AbstractHandler derivativeFilter =  new DirivativeFilter();
        procces(derivativeFilter);
        Assert.assertArrayEquals(checkDerivativeFilter.toArray(),returnList.toArray());
    }

    private void procces(AbstractHandler filter){
        filter.setData(startList);
        returnList = filter.getData();
        for (int i = 1; i < 6; i++) {
             startList.add((float)i);
             filter.toDoOperation();
        }
    }

}
