package com.tihiy.comm;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public interface SignalReader {
    public BlockingQueue getData(File file);
}
