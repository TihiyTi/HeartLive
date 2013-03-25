package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReturn;
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
    public void testTryToNrJavaSerial() throws PortInUseException {
        ComPortListener comPortListener =  ComPortListener.getInstance();
        comPortListener.run();
//        (new Thread(comPortListener)).start();
        //SerialSignalReader reader = new SerialSignalReader("COM1", new SignalManager());
    }

    @Test
    public void testGenerator() throws InterruptedException {
        SerialSignalReader reader = new SerialSignalReader("test", new SignalManager());
        Thread.sleep(1000);
    }

    private class SignalManager implements SignalReturn{
        @Override
        public void getSamples(double[] samples, String flowName) { }
        @Override
        public void getSamples(byte[] samples, String flowName) {  }
        @Override
        public void createSignal(String flowName) { }
    }

}
