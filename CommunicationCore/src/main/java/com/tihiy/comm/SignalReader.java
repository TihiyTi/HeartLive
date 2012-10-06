package com.tihiy.comm;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public interface SignalReader {
    // Get FIRST signal in file
    public BlockingQueue getData(File file);

    // Get all Signals in file
    public List getAllData (File file);

    // Return true if reading completed
    // Flag reading complete set in SignalReader
    public Boolean isReadComplete();
}
