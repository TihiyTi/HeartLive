package service;

import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * User: Tihiy
 * Date: 17.09.12
 * Time: 10:44
 */
public class SignalReader {

    private BlockingQueue<Float> inputData = new LinkedBlockingDeque<Float>();
    private String readBehaviour;

    public SignalReader(Settings config) {
    }

    public SignalReader(File file){
        if(file.getName().endsWith("txt")){
            startRead(file);
        }else{
            System.out.println("File's suffics doesn't contain TXT");
        }
    }


    public BlockingQueue<Float> getInputData() {
        return inputData;
    }

    private void startRead(File file){
        TxtReader txtReader = new TxtReader(file, inputData);
        txtReader.run();
    }

}

class TxtReader extends Thread{
    private BlockingQueue<Float> inputData;
    private File file;

    TxtReader(File file, BlockingQueue inputData) {
        this.inputData = inputData;
        this.file = file;
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
            System.out.println(""+ pointInFloat);
            inputData.put(pointInFloat);
            string = "";
            while( chars[i] == 32 ){
                i++;
            }
            for(i = i; i<chars.length; i++){
                string = string + chars[i];
            }
        }
    }
}