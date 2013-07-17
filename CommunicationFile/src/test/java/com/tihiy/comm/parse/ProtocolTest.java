package com.tihiy.comm.parse;

import com.tihiy.comm.sig.Signal;
import org.junit.Test;

import java.nio.ByteBuffer;

public class ProtocolTest {
    @Test
    public void testParse() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byte a = 127;

        byte b = 45;
        byteBuffer.put(4, a);
        byteBuffer.put(5, b);
        byteBuffer.put(6, a);
        Signal s = new Signal();
        Protocol.Reo32.parse(s, byteBuffer);
    }
}
