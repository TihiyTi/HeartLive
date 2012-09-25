package com.tihiy.comm.parse;

import com.tihiy.comm.SignalReader;
import junit.framework.TestCase;
import org.junit.Test;

import javax.activation.URLDataSource;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 25.09.12
 * Time: 0:31
 * To change this template use File | Settings | File Templates.
 */
public class FileSignalReaderTest extends TestCase {
    private  BlockingQueue<Float> data;
    private File file;

    @Test(timeout = 1000, expected = NullPointerException.class)
    public void testReader() throws Exception {
        FileSignalReader signalReader = new FileSignalReader();
        file = new File(FileSignalReaderTest.class.getResource("signal.txt").getFile());
        data = signalReader.getData(file);
        System.out.println(" " + data.size());
        for(int i = 0; i< data.size(); i++){
            switch (i){
                case 15:
                    assertTrue(Float.compare(data.peek(), -0.175f) == 0);
                    break;
                case 21596:
                    assertTrue(Float.compare(data.peek(), -0.210f) == 0);
                    break;
            }
            data.take();
        }
    }

}
