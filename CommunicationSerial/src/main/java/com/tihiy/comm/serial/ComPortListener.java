package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReturn;
import gnu.io.NRSerialPort;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 25.03.13
 * Time: 3:01
 */
public class ComPortListener implements Runnable {
    private static ComPortListener ourInstance = new ComPortListener();
    private Logger log = Logger.getLogger(this.getClass().getName());

    private Map<String, SerialSignalReader> portMap = new HashMap();
    private SignalReturn signalManager;

    public static ComPortListener getInstance(SignalReturn signalManager) {
        ourInstance.setSignalManager(signalManager);
        return ourInstance;
    }

    private ComPortListener() {

    }

    @Override
    public void run() {
        Set<String> setOfPorts = NRSerialPort.getAvailableSerialPorts();
        log.info(setOfPorts.toString());
        for (String portName: setOfPorts){
            if(!portMap.containsKey(portName)){
                try {
                    portMap.put(portName, new SerialSignalReader(portName, signalManager));
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }else{
                if(portMap.get(portName)== null){
                    try {
                        portMap.put(portName, new SerialSignalReader(portName, signalManager));
                    } catch (Exception e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }
    }

    public void setSignalManager(SignalReturn signalManager) {
        this.signalManager = signalManager;
    }

    public void closeAllPorts(){
        for(String s: portMap.keySet()){
            if(portMap.get(s)!= null){
                portMap.get(s).closePort();
            }
        }

    }

}
