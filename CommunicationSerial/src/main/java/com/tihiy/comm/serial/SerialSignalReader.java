package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReturn;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;

public class SerialSignalReader implements SerialPortEventListener {

    private RXTXPort serial;
    private boolean connected = false;
    private int baud = 9600;
    private String portName;
    private SignalReturn signalManager;

    public SerialSignalReader(String portName, SignalReturn signalManager) {
        this.portName = portName;
        this.signalManager = signalManager;
        if(!portName.equals("test")){
            connect(portName);
        }else{
            TestGenerator generator = new TestGenerator();
            (new Thread(generator)).start();
        }
    }

    private boolean connect(String portName){
        try
        {
            RXTXPort comm = null;
            CommPortIdentifier ident = null;

            ident = CommPortIdentifier.getPortIdentifier(portName);

            try{
                System.out.println("Try open port" + portName);
                comm = ident.open("SerialSignalReader", 2000);
                System.out.println("Port opened" + portName);
            }catch (PortInUseException e) {
                System.err.println("This is a bug, passed the ownership test above: " + e.getMessage());
                return false;
            }

            if ( !(comm instanceof RXTXPort) ) {
                throw new UnsupportedCommOperationException("Non-serial connections are unsupported.");
            }

            serial = (RXTXPort) comm;
            serial.enableReceiveTimeout(100);
            serial.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            connected = true;
        }catch(NativeResourceException e){
            throw new NativeResourceException(e.getMessage());
        }catch (Exception e) {
            System.err.println("Failed to connect on port: "+portName+" exception: ");
            e.printStackTrace();
            connected =false;
        }

        try {
            serial.addEventListener(new SerialReader(serial.getInputStream()));
        } catch (TooManyListenersException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(isConnected()) {
            serial.notifyOnDataAvailable(true);
        }
        return isConnected();
    }

    public boolean isConnected(){
        return connected;
    }

    @Override
    public void serialEvent(SerialPortEvent ev) {
        if(ev.getEventType()==SerialPortEvent.DATA_AVAILABLE){

        }
    }

    private static class SerialReader implements SerialPortEventListener{
        private InputStream in;
        private byte[] buffer = new byte[1024];

        public SerialReader ( InputStream in ){
            this.in = in;
        }

        public void serialEvent(SerialPortEvent arg0) {
            int data;
            byte[] buffer = new byte[1024];
            int len = -1;
            try{
                while ( ( len = this.in.read(buffer)) > -1 ){
                    System.out.print(new String(buffer,0,len));
                }
            }
            catch ( IOException e ){
                e.printStackTrace();
            }
        }

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

    public void closePort(){
        serial.close();
    }
}
