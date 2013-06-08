package com.tihiy.comm.serial;

import com.tihiy.comm.serial.protocols.Protocol;
import java.util.Queue;

public interface SignalManagerInterface<T extends Number> {

    Protocol getProtocol();
    void createSignal(String flowName);
    Queue<T> getQueue(String flowName);
}
