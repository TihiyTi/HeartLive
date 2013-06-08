package com.tihiy.comm.serial;

import com.tihiy.comm.serial.protocols.Protocol;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public abstract class AbstractSignalManager<T extends Number> implements SignalManagerInterface<T> {

    private final Logger log = Logger.getLogger(getClass().getName());
    private final Map<String, LinkedBlockingQueue<T>> portMap = new HashMap<>();
    private Protocol protocol;

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void createSignal(String flowName) {
        portMap.put(flowName, (new LinkedBlockingQueue<T>()));
        log.info("Created signal " + flowName);
    }
    @Override
    public Queue<T> getQueue(String flowName){
        return portMap.get(flowName);
    }
}
