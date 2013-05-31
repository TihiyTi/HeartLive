package com.tihiy.comm.serial;

import com.tihiy.comm.serial.protocols.Protocol;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 28.03.13
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSignalManager implements SignalReturn {

    private Map portMap = new HashMap();
    private Protocol protocol;

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

}
