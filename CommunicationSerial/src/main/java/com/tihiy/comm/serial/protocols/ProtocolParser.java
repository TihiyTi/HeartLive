package com.tihiy.comm.serial.protocols;

import java.io.PushbackInputStream;
import java.util.List;

public interface ProtocolParser {
    List<List<Integer>> getFormattedData(PushbackInputStream stream);
    int getNumber();
}
