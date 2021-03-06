package com.tihiy.comm.serial;

import com.tihiy.comm.serial.protocols.Protocol;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.PushbackInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialSignalReader {

    private final SerialPort serial;
    private Protocol protocol;
    Logger log = Logger.getLogger(getClass().getName());

    public SerialSignalReader(final String portName, final SignalManagerInterface signalManager) throws Exception {
        int baud = 9600;
        if (!"test".equals(portName)) {
            serial = (new SimpleSerialPort()).open(portName);
            log.info("Port" + portName + " opened!");
            serial.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serial.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                        try {
                            PushbackInputStream bufStream = new PushbackInputStream(serial.getInputStream());
                            if ((signalManager.getProtocol() != null) && (protocol == null)) {
                                if (signalManager.getProtocol().isProtocol(bufStream)) {
                                    log.info("Protocol  checked" + signalManager.getProtocol().name() );

                                }
                                protocol = signalManager.getProtocol();
                                signalManager.createSignal(portName);
                            }
                            if (protocol == null){
                                for (Protocol p : Protocol.values()) {
                                    if (p.getNumber() * 2 <= bufStream.available()) {
                                        if (p.isProtocol(bufStream)) {
                                            log.info("Find protocol " + p.name());
                                            protocol = p;

                                        }
                                        signalManager.createSignal(portName);
                                    }
                                }
                            } else {
                                protocol.getProtocolParser().sendFormattedData(bufStream, signalManager, portName);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(getClass().getName()).log(Level.WARNING, toString(), ex);
                        }
                    }
                }
            });
        } else {
            serial = null;
            TestGenerator generator = new TestGenerator();
            (new Thread(generator)).start();
        }
    }

    public void closePort() {
        serial.close();
    }

    // ToDo If SignalManager local variable, then it can't use in TestGenerator.
    private static class TestGenerator implements Runnable {

        @Override
        public void run() {
            while (true) {
                byte[] b = {(byte) (Math.random() * 255)};
                System.out.println(b[0]);
//                signalManager.putSamples(b, "test");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
