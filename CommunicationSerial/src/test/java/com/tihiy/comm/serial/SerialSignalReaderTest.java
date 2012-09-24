package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReader;
import junit.framework.Assert;
import org.junit.Test;

public final class SerialSignalReaderTest {
    @Test
    public void testSerialReader() {
        SignalReader signalReader =  new SerialSignalReader();
        Assert.assertTrue(true);
    }
}
