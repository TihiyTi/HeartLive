package com.tihiy.comm.parse;

import com.tihiy.comm.SignalReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 25.09.12
 * Time: 0:31
 * To change this template use File | Settings | File Templates.
 */
public class FileSignalReader extends  Thread implements SignalReader {
    private List<BlockingQueue> listOfSignal = new ArrayList<>();
    private BlockingQueue inputData = new LinkedBlockingQueue();
    private File file;
    private Boolean readComplete = false;
//    public int dataLength = 0;

    @Override
    public BlockingQueue getData(File file) {
        this.file = file;
        this.run();
        return inputData;
    }

    @Override
    public List getAllData(File file) {
        return listOfSignal;
    }

    @Override
    public Boolean isReadComplete() {
        return readComplete;
    }

    public void run() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fileReader);
            String s = buffer.readLine();
            if(s.contains("Elapsed time")){
                phisioNetParser(buffer);
            }else{
                System.out.println("Unsupported file format");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void phisioNetParser(BufferedReader buffer) throws IOException, InterruptedException {
        String s = buffer.readLine();
        while((s = buffer.readLine())!=null){
            char[] chars = s.toCharArray();
            int i = 17;
            String string = "";
            while( chars[i] == 32 ){
                i++;
            }
            while( chars[i]!=32 ){
                string = string + chars[i];
                i++;
            }
            float pointInFloat = Float.parseFloat(string);
            //System.out.println(""+ pointInFloat);
            inputData.put(pointInFloat);
//            dataLength++;
            string = "";
            while( chars[i] == 32 ){
                i++;
            }
            for(i = i; i<chars.length; i++){
                string = string + chars[i];
            }
        }
        readComplete = true;
    }

}
