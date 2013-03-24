package com.tihiy.comm.serial;

import gnu.io.NRSerialPort;
import gnu.io.PortInUseException;
import gnu.io.RXTXPort;
import org.junit.Test;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 23.03.13
 * Time: 0:40
 * To change this template use File | Settings | File Templates.
 */
public class NRTest {
    @Test
    public void testTryToNrJavaSerial() throws PortInUseException {
        //NRSerialPort port = new NRSerialPort("COM3", 9600);
        Set<String> set = NRSerialPort.getAvailableSerialPorts();
        System.out.println(set.toString());
        //RXTXPort port = new RXTXPort("Com3");
    }

    @Test
    public void someUnImportantTest(){

    }

    private byte[] createByteBuffer(int bufferSize){
        byte
        return
    }
}
