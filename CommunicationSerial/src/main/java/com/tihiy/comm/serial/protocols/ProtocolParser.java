package com.tihiy.comm.serial.protocols;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

public interface ProtocolParser {
    List<List<Integer>> getFormattedData(PushbackInputStream stream);
}
