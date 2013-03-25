package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReturn;
import gnu.io.NRSerialPort;
import gnu.io.PortInUseException;
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
        SerialSignalReader reader = new SerialSignalReader("COM1", new SignalManager());
    }

//    private byte[] createByteBuffer(int bufferSize){
//        byte
//        return
//    }
    private class SignalManager implements SignalReturn{

    @Override
    public void getSamples(double[] samples, String flowName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createSignal(String flowName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
}
