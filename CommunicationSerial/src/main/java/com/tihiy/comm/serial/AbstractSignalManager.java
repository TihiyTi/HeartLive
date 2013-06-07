package com.tihiy.comm.serial;

import com.tihiy.comm.serial.protocols.Protocol;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 28.03.13
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSignalManager<T extends Number> implements SignalReturn<T> {

    private Logger log = Logger.getLogger(this.getClass().getName());
    private Map portMap = new HashMap();
    private Protocol protocol;

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public void getSamples(double[] samples, String flowName){
        log.info("Protocol not supported - double[], String");
    }

    @Override
    public void getSamples(byte[] samples, String flowName) {
        log.info("Protocol not supported - byte[], String");
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void createSignal(String flowName) {
        log.info("Created signal not supported");
    }

}
