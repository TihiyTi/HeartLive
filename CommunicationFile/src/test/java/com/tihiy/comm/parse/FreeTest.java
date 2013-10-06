package com.tihiy.comm.parse;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.tihiy.comm.ListWriter;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 05.10.12
 * Time: 18:02
 * To change this template use File | Settings | File Templates.
 */
public class FreeTest {
    @Test
    public void testFirst(){
//       Test GUAVA function
        Splitter splitter;
        String s = "       0:00.011\t -0.145\t -0.065";
        //System.out.println(s.split("."));
        Splitter MY_SPLITTER = Splitter.on('\t').trimResults().omitEmptyStrings();
        Iterable list = MY_SPLITTER.split(s);
        for (Object s1 : list) {
            System.out.println("{"+s1+"}");
        }
    }
    @Test
    public void testTwo(){
        System.out.println("Test TWO");
        CharMatcher trimmer = CharMatcher.anyOf("-.0123456789");
        Splitter splitter = Splitter.on('\t').trimResults().omitEmptyStrings().trimResults(trimmer);
        Iterable list = splitter.split("       0:00.011\t -0.145\t -0.065");
        for (Object s1 : list) {
            System.out.println("{"+s1+"}");
        }
    }
    @Test
    public void testWriteChannel() throws IOException {
//        File outFile = new File("outfile.dat");
        String destination = "out.txt";

        FileOutputStream fos = new FileOutputStream(destination);

        FileChannel fco = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        buffer.putInt(1111);
        buffer.putDouble(2.3);
        buffer.limit(buffer.position()); // cut buffer
        buffer.rewind(); // set buffer's position to 0
        fco.write(buffer);
        System.out.println(" byte written");
        for(int i = 0; i < 100;  i++){
            for(int j = 0; j<10; j++){
//                buffer.put((byte)j);
            }
//            buffer.limit(buffer.position()); // cut buffer
//            buffer.rewind(); // set buffer's position to 0
//            fco.write(buffer);
//            buffer.clear();
        }
        fco.close();

//        FileChannel fco = new FileOutputStream(outFile).getChannel();
//        ByteBuffer bb = ByteBuffer.allocate(100);
//        byte[] array = new byte[100];
//        for(int i = 0; i < array.length; i++){
//            array[i] = (byte) i;
//        }
//        bb.put(array);
//        fco.write(bb);
//        fco.close();
//        File inFile = new File("outFile.dat");
//        System.out.println(""+ inFile.getPath());
//        new File(FileSignalReaderTest.class.getResource("signal.txt").getFile());
    }
    @Test
    public void testWrite_1() throws IOException {
        File out =  new File("test_out1.txt");
        FileOutputStream fos = new FileOutputStream(out, true);
        byte[] b = {0x1,2,3,4,5,6};
        fos.write(b);
        fos.close();
    }
    @Test
    public void testWrite_2() throws IOException {
        File out =  new File("test_out2.txt");
        FileWriter fos = new FileWriter(out, true);
        fos.write(12);
        fos.write("String write in this file");
        fos.close();
    }
    @Test
    public void testWrite_3() throws IOException {
        FileChannel fco = new FileOutputStream("test_out3.txt").getChannel();
        ByteBuffer bb = ByteBuffer.allocate(100);
        byte[] array = new byte[100];
        for(int i = 0; i < array.length; i++){
            array[i] = (byte) i;
        }
        bb.put(array);
        bb.rewind();
        fco.write(bb);
        fco.close();
    }
    @Test
    public void testRead() throws IOException {
        FileChannel fci = new FileInputStream("test_out3.txt").getChannel();
        ByteBuffer bb = ByteBuffer.allocate(10);
        int read;
        while ((read = fci.read(bb)) != -1){
            byte[] ar = bb.array();
            for(byte b: ar){
                System.out.print(" " + b);
            }
            bb.clear();
        }
        fci.close();
    }
    @Test
    public void testWrite_4() throws IOException {
        FileChannel fco = new RandomAccessFile("test_out4.txt", "rw").getChannel();
        MappedByteBuffer bb = fco.map(FileChannel.MapMode.READ_WRITE, 0, 10);
        bb.put((byte)95);
        bb.put((byte)105);
        bb.put((byte) 105);
        bb.put((byte) 105);
        bb.put((byte)107);
        bb.put((byte)100);
        bb = fco.map(FileChannel.MapMode.READ_WRITE, 10, 10);
        bb.put((byte)96);
        bb.put((byte) 106);
        bb.put((byte) 106);

        bb = fco.map(FileChannel.MapMode.READ_WRITE, 20, 10);
        bb.put((byte)106);
        bb.put((byte) 106);
        bb.put((byte) 106);

        bb = fco.map(FileChannel.MapMode.READ_WRITE, 30, 10);
        bb.put((byte)106);
        bb.put((byte) 106);
        bb.put((byte) 106);

        fco.close();

//        Arrays.copyOfRange()
    }

    @Test
    public void testFileWriter() throws FileNotFoundException, UnsupportedEncodingException {
        ListWriter<Double> fileWriter = new ListWriter<>();
        List<Double> testList = new ArrayList<>();
        testList.add(0.1);
        testList.add(0.9);
        fileWriter.writeListToFile(testList, "list");
    }

}
