package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReturn;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialSignalReader{

    private final SerialPort serial;
    private boolean connected = false;
    private int baud = 9600;
    private String portName;
    private SignalReturn signalManager;

    public SerialSignalReader(String portName, SignalReturn signalManager) throws Exception {
        this.portName = portName;
        this.signalManager = signalManager;
        if(!"test".equals(portName)){
            serial = (new SimpleSerialPort()).open(portName);
            serial.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serial.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                        try {
                            if (serial.getInputStream() != null) {
                                byte[] bytes = new byte[serial.getInputStream().available()];
                                if (serial.getInputStream().read(bytes) > 0) {

                                    //do something with bytes[]
                                }
                            }
                        }
                        catch (Exception ex) {
                            Logger.getLogger(getClass().getName()).log(Level.WARNING, toString(), ex);
                        }
                    }
                }
            });
        }else{
            serial = null;
            TestGenerator generator = new TestGenerator();
            (new Thread(generator)).start();
        }
    }

    public void closePort(){
        serial.close();
    }

    private class TestGenerator implements Runnable{
        @Override
        public void run() {
            while(true){
                byte[] b  = new byte[]{(byte)(Math.random()*255)};
                System.out.println(b[0]);
                signalManager.getSamples(new byte[]{(byte)(Math.random()*255)}, "test");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }
}
