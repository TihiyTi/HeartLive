package com.tihiy.reo;

import junit.framework.TestCase;
import org.junit.Test;

public class ReoModelTest extends TestCase {
    @Test
    public void testMainTest(){
        ElectrodeSystem e = new ElectrodeSystem(60,30, 0, 0);
        InterfaceModel reoModel = new ReoModel(40, 20);
        Measurement measure = new Measurement(reoModel, e);

    }
}
