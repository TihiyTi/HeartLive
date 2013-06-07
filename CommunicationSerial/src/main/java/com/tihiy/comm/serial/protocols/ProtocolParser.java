package com.tihiy.comm.serial.protocols;

import com.tihiy.comm.serial.SignalManagerInterface;

import java.io.PushbackInputStream;

public interface ProtocolParser {

    <T extends Number> void  sendFormattedData(PushbackInputStream stream, SignalManagerInterface<T> manager, String flowName);
    int getNumber();

}
