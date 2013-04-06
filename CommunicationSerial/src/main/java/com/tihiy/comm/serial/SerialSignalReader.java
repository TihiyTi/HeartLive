package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReturn;
import com.tihiy.comm.serial.protocols.Protocol;
import gnu.io.*;

import java.io.PushbackInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialSignalReader{

    private final SerialPort serial;
    private Protocol protocol;

    public SerialSignalReader(final String portName, final SignalReturn signalManager) throws Exception {
        int baud = 9600;
        if(!"test".equals(portName)){
            serial = (new SimpleSerialPort()).open(portName);
            serial.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serial.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                        try {
                            PushbackInputStream bufStream = new PushbackInputStream(serial.getInputStream());
                            if(protocol == null){
                                for(Protocol p: Protocol.values()){
                                    if(p.isProtocol(bufStream))
                                        protocol = p;
                                        signalManager.createSignal(portName);
                                }
                            }else{
                                signalManager.getSamples(protocol.getFormattedData(bufStream));
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

    // ToDo If SignalManager local varieble, then it can't use in TestGenerator.
    private static class TestGenerator implements Runnable{

        @Override
        public void run() {
            while(true){
                byte[] b  = {(byte)(Math.random()*255)};
                System.out.println(b[0]);
//                signalManager.getSamples(b, "test");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
