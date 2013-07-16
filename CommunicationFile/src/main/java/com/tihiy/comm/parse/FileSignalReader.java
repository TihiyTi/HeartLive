package com.tihiy.comm.parse;

import com.google.common.base.Splitter;

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
public class FileSignalReader extends  Thread{
    private List<BlockingQueue> listOfSignal = new ArrayList<>();
    private BlockingQueue inputData = new LinkedBlockingQueue();
    private File file;
    private Boolean readComplete = false;
//    public int dataLength = 0;

    public BlockingQueue getData(File file) {
        this.file = file;
        this.run();
        return inputData;
    }

    public List getAllData(File file) {
        this.file = file;
        this.run();
        return listOfSignal;
    }

    public Boolean isReadComplete() {
        return readComplete;
    }

    public void run() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fileReader);
            String s = buffer.readLine();
            if(s.contains("Elapsed time")){
                for(int i = 0 ; i<2; i++){
                    listOfSignal.add(new LinkedBlockingQueue());
                }
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
            Splitter splitter = Splitter.on('\t').trimResults().omitEmptyStrings();
            Iterable listOfPoints = splitter.split(s);
            int numOfSignal = 0;
            for (Object point : listOfPoints) {
                String pointInString = (String)point;
                if(!pointInString.contains(":")){
                    float pointInFloat =  Float.parseFloat(pointInString);
                    listOfSignal.get(numOfSignal).add(pointInFloat);
                    numOfSignal++;
                }
            }
        }
        readComplete = true;
    }

}
