package com.tihiy.comm.serial;

import com.tihiy.comm.SignalReturn;
import gnu.io.NRSerialPort;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 25.03.13
 * Time: 3:01
 */
public class ComPortListener implements Runnable {
    private static ComPortListener ourInstance = new ComPortListener();

    private Map<String, SerialSignalReader> portMap = new HashMap<>();
    private SignalReturn signalManager;

    public static ComPortListener getInstance() {
        return ourInstance;
    }

    private ComPortListener() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

            }
        }, 0L, 2L, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        Set<String> setOfPorts = NRSerialPort.getAvailableSerialPorts();
        System.out.println(setOfPorts.toString());
        for (String portName: setOfPorts){
            if(!portMap.containsKey(portName)){
                portMap.put(portName, new SerialSignalReader(portName, signalManager));
            }else{
                if(!portMap.get(portName).isConnected()){
//                    portMap.get(portName).checkProtocol();
                }
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void createSignal(String portName){
        signalManager.createSignal(portName);
    }

    public void setSignalManager(SignalReturn signalManager) {
        this.signalManager = signalManager;
    }

    public void createTestFlow(){

    }

}
