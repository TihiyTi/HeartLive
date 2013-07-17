package com.tihiy.comm.parse;

import com.google.common.base.Splitter;
import com.tihiy.comm.sig.Signal;
import com.tihiy.comm.sig.SignalInterface;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileSignalReader implements Runnable{
    private final SignalManager manager;
    private final SignalInterface signal;
    private final File file;

    private Protocol protocol;

    // todo probably change file and manager for SIGNAL
    public FileSignalReader(File file, SignalManager manager) {
        this.manager = manager;
        this.file = file;
        signal = manager.getSignal(file.getName());
    }


    @Override
    public void run() {
        try {
            FileChannel fci = new FileInputStream(file).getChannel();
            long size = fci.size();
            MappedByteBuffer byteBuffer = fci.map(FileChannel.MapMode.READ_ONLY, 0, size);
            if(protocol == Protocol.Reo32){
                protocol.parse(signal, byteBuffer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void phisioNetParser(BufferedReader buffer) throws IOException, InterruptedException {
        String s = buffer.readLine();
        while((s = buffer.readLine())!=null){
            Splitter splitter = Splitter.on('\t').trimResults().omitEmptyStrings();
            Iterable listOfPoints = splitter.split(s);
            int numOfSignal = 0;
            for (Object point : listOfPoints) {
                String pointInString = (String)point;
                if(!pointInString.contains(":")){
                    float pointInFloat =  Float.parseFloat(pointInString);
//                    listOfSignal.get(numOfSignal).add(pointInFloat);
                    numOfSignal++;
                }
            }
        }
//        readComplete = true;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

}
