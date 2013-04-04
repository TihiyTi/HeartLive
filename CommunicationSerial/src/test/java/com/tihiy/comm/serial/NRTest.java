package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReturn;
import gnu.io.NRSerialPort;
import gnu.io.PortInUseException;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 23.03.13
 * Time: 0:40
 * To change this template use File | Settings | File Templates.
 */
public class NRTest {
    @Test
    public void findPort(){
        System.out.println(NRSerialPort.getAvailableSerialPorts().toString());
    }

    @Test
    public void testTryToNrJavaSerial() throws InterruptedException {
        ComPortListener comPortListener =  ComPortListener.getInstance(new SignalManager());
//        comPortListener.run();
//        (new Thread(comPortListener)).start();
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(comPortListener, 0L, 2L, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(40L);
        comPortListener.closeAllPorts();

        //SerialSignalReader reader = new SerialSignalReader("COM1", new SignalManager());
    }

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
