import com.tihiy.comm.ListWriter;
import com.tihiy.rclint.ReadingFiles;
import com.tihiy.rclint.models.SignalModel;
import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractModel;
import com.tihiy.reo.ReoPostProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class ControllerFLayer extends AbstractController{
    private final Map<String, AbstractModel> registeredModels = new HashMap<>();
    private final Map<String, BlockingQueue<Integer>> signalMap = new HashMap<>();

    public static final String PRECARD_1 = "precard_1";
    public static final String PRECARD_2 = "precard_2";
    public static final String PRECARD_3 = "precard_3";
    public static final String PRECARD_4 = "precard_4";
    public static final String PRECARD_5 = "precard_5";
    public static final String PRECARD_BASE_1 = "precard_base_1";
    public static final String PRECARD_BASE_2 = "precard_base_2";
    public static final String PRECARD_BASE_3 = "precard_base_3";
    public static final String PRECARD_BASE_4 = "precard_base_4";
    public static final String PRECARD_BASE_5 = "precard_base_5";
    public static final String FIRST = "first";
    public static final String RADIUS_1 = "radius_1";
    public static final String RADIUS_2 = "radius_2";
    public static final String RADIUS_3 = "radius_3";
    public static final String RADIUS_4 = "radius_4";
    public static final String RADIUS_5 = "radius_5";


    public void addSignal(String name, File file) throws IOException {
        if(file != null){
            List<Double> list =  ReadingFiles.readFile(file);
            if(registeredModels.containsKey(name)){
                ((SignalModel)registeredModels.get(name)).setList(list);
            }else{
                addModel(name, new SignalModel(name));
                ((SignalModel)registeredModels.get(name)).setList(list);
            }
        }else{
            Logger.getLogger(getClass().getName()).info("File didn't choosen.");
        }
    }
    public void calculate(double[] main, double[] first, File radiusFile, String comment){
        System.out.println("Size A = " + main[0]);
        ReoPostProcessor rp = new ReoPostProcessor();
//        rp.setMainMeasurement(main[0], main[1], main[2], main[3], main[4], main[5], ((SignalModel)registeredModels.get("sourceSignal")).getList());
        rp.setMainMeasurement(main[0], main[1], main[2], main[3], main[4], main[5], ((SignalModel)registeredModels.get("sourceSignal")).getList(), ((SignalModel)registeredModels.get("baseSignal")).getList());
        rp.setFirstLayerMeasurement(first[0], first[1], ((SignalModel)registeredModels.get("targetSignal")).getList());
        boolean useFirstLayer = true;
        boolean useBaseImpedance = true;
        rp.setUseFirstLayer(useFirstLayer);
        rp.setUseBaseImpedance(useBaseImpedance);
        comment = comment + " UseBaseImpedance="+useBaseImpedance+ " UseBaseImpedance="+useBaseImpedance;
        List<Double> result = rp.getRadiusWithRo1();
        int i = 0;
        ListWriter listWriter = new ListWriter();
        try {
            listWriter.writeListToFile(result, radiusFile, comment);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        for(Double r: result){
            System.out.println(i + " " + r);
            i++;
        }
        ((SignalModel)registeredModels.get("radiusSignal")).setList(result);
    }



    public void calculate(List<double[]> list, File defaultPath){
        ReoPostProcessor rp = new ReoPostProcessor();
        for(int i = 0; i < 5; i++){
            String precardio = "precard_"+(i+1);
            String base = "precard_base_"+(i+1);
            String radius = "radius_"+(i+1);
            double[] array = list.get(i);
            if(registeredModels.containsKey(precardio)){
                boolean useFirstLayer = true;
                boolean useBaseImpedance = false;
                rp.setUseFirstLayer(useFirstLayer);
                rp.setUseBaseImpedance(useBaseImpedance);
                if(useBaseImpedance){
                    rp.setMainMeasurement(array[0], array[1], array[2], array[3], array[4], array[5], ((SignalModel)registeredModels.get(precardio)).getList(), ((SignalModel)registeredModels.get(base)).getList());
                }else{
                    rp.setMainMeasurement(array[0], array[1], array[2], array[3], array[4], array[5], ((SignalModel)registeredModels.get(precardio)).getList());
                }
                rp.setFirstLayerMeasurement(array[6], array[7], ((SignalModel)registeredModels.get(FIRST)).getList());
                rp.setRoEquivalent(array[8]);
                List<Double> result = rp.getRadiusWithRo1();

                SignalModel model = new SignalModel(radius);
                addModel(radius, model);
                model.setList(result);

                ListWriter listWriter = new ListWriter();
                try {
                    listWriter.writeListToFile(result, new File(defaultPath, "radius"+ getDate()+precardio));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }else{
                Logger.getLogger(getClass().getName()).info("MapModel don't contain model '"+ precardio + "'! ");
            }
        }
    }

//    public void createSignalModel(String flowName, AbstractModel model){
//        addModel(model);
//    }

    public void addModel(String flowName, AbstractModel model){
        registeredModels.put(flowName, model);
        model.addPropertyChangeListener(this);
    }

    public void modifyModel(String flowName){
//        registeredModels.get(flowName).
    }

    private static String getDate(){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("_yyyy_MM_dd_hh_mm_ss");
        return ft.format(dNow);
    }
}
