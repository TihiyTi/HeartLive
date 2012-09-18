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
        // Choose
        File f = new File("C:\\Users\\Alex\\IdeaProjects\\HeartLive\\src\\samples.txt");
        TxtReader txtReader = new TxtReader(f, inputData);
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
                // File from PhisioNEt
                phisioNetParser(buffer);
            }else{
//                if(){
//
//                }
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
            System.out.println(s);
            char[] chars = s.toCharArray();
            int i = 0;
            int n = 0;
//            while(chars[i]==Character.)
            for(i = 0; i < chars.length; i++){
                if(chars[i]==32){
                    n++;
                }
// System.out.print(" " + Character.getNumericValue(chars[i]));
                System.out.print(" "+(int)chars[i]);
            }
            System.out.println(""+n);
            //inputData.put(Float.parseFloat(s));
        }
    }
}