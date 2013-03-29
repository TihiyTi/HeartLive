package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReturn;
import gnu.io.NRSerialPort;
import gnu.io.PortInUseException;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 23.03.13
 * Time: 0:40
 * To change this template use File | Settings | File Templates.
 */
public class NRTest {
    @Test
    public void testTryToNrJavaSerial() throws PortInUseException, InterruptedException {
//        System.out.println(NRSerialPort.getAvailableSerialPorts().toString());
        ComPortListener comPortListener =  ComPortListener.getInstance();
        comPortListener.run();
//        (new Thread(comPortListener)).start();
        Thread.sleep(10000);
    }
//
//    @Test
//    public void testGenerator() throws InterruptedException {
//        SerialSignalReader reader = new SerialSignalReader("test", new SignalManager());
//        Thread.sleep(1000);
//    }

//    @Test
//    public void testCom4() throws InterruptedException {
//        SignalManager signalManager = new SignalManager();
//        SerialSignalReader reader = new SerialSignalReader("COM6", signalManager);
//        Thread.sleep(10000);
//        reader.closePort();
//    }

    private class SignalManager implements SignalReturn{
        @Override
        public void getSamples(double[] samples, String flowName) { }
        @Override
        public void getSamples(byte[] samples, String flowName) {  }
        @Override
        public void createSignal(String flowName) { }
    }

}
