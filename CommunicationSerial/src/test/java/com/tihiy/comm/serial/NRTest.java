package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReturn;
import gnu.io.NRSerialPort;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class NRTest {
    @Test
    public void findPort(){
        Logger.getLogger("Test").info(NRSerialPort.getAvailableSerialPorts().toString());
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

    private static class SignalManager implements SignalReturn{
        @Override
        public void getSamples(double[] samples, String flowName) { }
        @Override
        public void getSamples(byte[] samples, String flowName) {  }
        @Override
        public void getSamples(List<List<Integer>> samples) {}
        @Override
        public void createSignal(String flowName) { }
    }

}
