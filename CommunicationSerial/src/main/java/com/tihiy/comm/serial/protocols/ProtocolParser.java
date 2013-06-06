package com.tihiy.comm.serial.protocols;

import com.tihiy.comm.serial.SignalReturn;

import java.io.PushbackInputStream;
import java.util.List;

public interface ProtocolParser {
//    List<List<Integer>> getFormattedData(PushbackInputStream stream);

    void sendFormattedData(PushbackInputStream stream, SignalReturn manager, String flowName);
    int getNumber();
}
