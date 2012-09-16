package ticom;

import gnu.io.*;
import service.Settings;

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
    private Settings config;
    private SerialPort serialPort;

    public TiComPort(Settings config) {
        this.config = config;
    }

    public void findPorts() throws PortInUseException, UnsupportedCommOperationException, IOException {
        Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        if(config.getComPortFinder().equals("sorting")){
            // Find port with specific protocol

        }else{
            // Try to open concrete port
            int numOfOpenPorts = 0;
            while(portEnum.hasMoreElements()){
                CommPortIdentifier portIdentifier = portEnum.nextElement();
                if(portIdentifier.getPortType()==CommPortIdentifier.PORT_SERIAL){
                    if(portIdentifier.getName().equals(config.getComPortFinder())){
                        serialPort = (SerialPort)portIdentifier.open(this.getClass().getName(), 2000);
                        serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                        InputStream in = serialPort.getInputStream();
                        (new Thread(new SerialReader(in))).start();
                        System.out.println(""+ config.getComPortFinder()+ " port was opened");
                        numOfOpenPorts++;
                    }
                }
            }
            if(numOfOpenPorts == 0){
                System.out.println("No port with name " + config.getComPortFinder());
            }
        }

        while (portEnum.hasMoreElements()){
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            if (portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL){
                if(config.getComPortFinder().equals("sorting")){

                }else{
                    serialPort = (SerialPort)portIdentifier.open(this.getClass().getName(), 2000);
                    serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                    InputStream in = serialPort.getInputStream();
                    (new Thread(new SerialReader(in))).start();
                }

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
