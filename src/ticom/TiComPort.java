package ticom;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 12.09.12
 * Time: 19:57
 */
public class TiComPort {
    private SerialPort serialPort;

    public void findPorts() throws PortInUseException, UnsupportedCommOperationException, IOException {
        Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()){
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            if (portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL){
                System.out.println("Found port" + portIdentifier.getName());
            }
            if (portIdentifier.getName().equals("COM3")){
                System.out.println("Port was found");
                serialPort = (SerialPort)portIdentifier.open(this.getClass().getName(), 2000);
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                InputStream in = serialPort.getInputStream();


                (new Thread(new SerialReader(in))).start();
            }
        }
    }

    public static class SerialReader implements Runnable{
        InputStream in;

        public SerialReader(InputStream in) {
            this.in = in;
        }

        @Override
          public void run() {
            //To change body of implemented methods use File | Settings | File Templates.
            byte[] buffer =  new byte[1024];
            int len = -1;
            try {
                while((len =  this.in.read(buffer))>-1){
                    for(int i=0; i<len; i++){
                        int cur = buffer[i];
                        System.out.print(" "+ (cur));
                        System.out.println("  "+ (cur & 255));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

}
