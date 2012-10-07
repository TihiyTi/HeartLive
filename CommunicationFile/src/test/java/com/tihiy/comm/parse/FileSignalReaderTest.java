package com.tihiy.comm.parse;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 25.09.12
 * Time: 0:31
 * To change this template use File | Settings | File Templates.
 */
public class FileSignalReaderTest extends TestCase {
    private List<BlockingQueue<Float>> listOfData;
    private File file;

    @Test(timeout = 1000, expected = NullPointerException.class)
    public void testReader() throws Exception {
        FileSignalReader signalReader = new FileSignalReader();
        file = new File(FileSignalReaderTest.class.getResource("signal.txt").getFile());
        listOfData = signalReader.getAllData(file);
        while(!signalReader.isReadComplete()) {}
        int size = listOfData.get(1).size();
        for(int i = 0; i< size; i++){
            switch (i){
                case 15:
                    assertTrue(Float.compare(listOfData.get(0).peek(), -0.175f) == 0);
                    break;
                case 21596:
                    assertTrue(Float.compare(listOfData.get(0).peek(), -0.21f) == 0);
                    break;
            }
            listOfData.get(0).take();
            listOfData.get(1).take();
        }
    }
}
