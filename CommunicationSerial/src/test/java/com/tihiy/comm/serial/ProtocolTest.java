package com.tihiy.comm.serial;

import org.junit.Test;

import java.util.logging.Logger;

public class ProtocolTest {
    @Test
    public void isProtocolTest(){
        byte[] buf = {0b1111111, 0b0000000};
        int i = (buf[0] > 0 ? buf[0] : buf[0] + 128)+ (buf[1]<<8);
        Logger.getLogger("ProtocolTest").info("i = "+i+" b1 "+buf[0]+ " b2 " +buf[1]);
        int i2 = (buf[0] > 0 ? buf[0] : buf[0] + 128);
        Logger.getLogger("ProtocolTest").info("i2 = "+i2);

        byte[] buf3 = {-0b1111111, 0b0000000};
        int i3 = (buf3[0] > 0 ? buf3[0] : buf3[0] + 128)+ (buf3[1]<<8);
        Logger.getLogger("ProtocolTest").info("i3 = "+i3+" b1 "+buf3[0]+ " b2 " +buf3[1]);
        int i4 = (buf3[0] > 0 ? buf3[0] : buf3[0] + 128);
        Logger.getLogger("ProtocolTest").info("i4 = "+i4);
//        int j = 0;
//        System.out.println("" + (buf3[0] << 8));
//        System.out.println("" + ((buf3[0] << 8)>>8));
//        System.out.println("" + (buf3[0] > 0 ? buf3[0] : (0b1<<8)+(buf3[0])));


//        byte[] buf2 = {0b1111111, 0b0000001};
//        int i2 = buf2[0] + (buf2[1]<<8);
//        Logger.getLogger("ProtocolTest").info("i2 = "+i2);
    }

    @Test
    public void pushBackStreamTest(){

    }
}
