import com.tihiy.comm.FileSignalReader;
import com.tihiy.comm.ListWriter;
import com.tihiy.comm.parse.Reo32Parser;
import com.tihiy.rclint.models.SignalModel;
import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractModel;
import com.tihiy.reo.ReoPostProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ThisController extends AbstractController {
    private final Map<String, AbstractModel> registeredModels = new HashMap<>();

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
    public static final String ECG = "ecg";
    public static final String RADIUS_1 = "radius_1";
    public static final String RADIUS_2 = "radius_2";
    public static final String RADIUS_3 = "radius_3";
    public static final String RADIUS_4 = "radius_4";
    public static final String RADIUS_5 = "radius_5";
    public static final String CLEAR_1 = "clear_1";
    public static final String CLEAR_2 = "clear_2";
    public static final String CLEAR_3 = "clear_3";
    public static final String CLEAR_4 = "clear_4";
    public static final String CLEAR_5 = "clear_5";

    //Add data from file to model with concrete name
    public void addSignals(File dataFile){
        FileSignalReader reader = new FileSignalReader();
        Map<Reo32Parser.Signal, List<Double>> map = reader.readFileToMap(dataFile);

        addModel(PRECARD_1, new SignalModel(PRECARD_1));
        addModel(PRECARD_2, new SignalModel(PRECARD_2));
        addModel(PRECARD_3, new SignalModel(PRECARD_3));
        addModel(PRECARD_4, new SignalModel(PRECARD_4));
        addModel(PRECARD_5, new SignalModel(PRECARD_5));
        addModel(FIRST, new SignalModel(FIRST));
        addModel(ECG, new SignalModel(ECG));

        ((SignalModel)registeredModels.get(FIRST)).setList(map.get(Reo32Parser.Signal.Pulse_1));
        ((SignalModel)registeredModels.get(PRECARD_1)).setList(map.get(Reo32Parser.Signal.Pulse_2));
        ((SignalModel)registeredModels.get(PRECARD_2)).setList(map.get(Reo32Parser.Signal.Pulse_3));
        ((SignalModel)registeredModels.get(PRECARD_3)).setList(map.get(Reo32Parser.Signal.Pulse_4));
        ((SignalModel)registeredModels.get(PRECARD_4)).setList(map.get(Reo32Parser.Signal.Pulse_5));
        ((SignalModel)registeredModels.get(PRECARD_5)).setList(map.get(Reo32Parser.Signal.Pulse_6));
        ((SignalModel)registeredModels.get(ECG)).setList(map.get(Reo32Parser.Signal.ECG));
    }
    public void clearSignal(List<double[]> listOfParam){
        ReoPostProcessor rp = new ReoPostProcessor();
        for(int i = 0; i < 5; i++){
            String precardio = "precard_"+(i+1);
            String base = "precard_base_"+(i+1);
            String radius = "radius_"+(i+1);
            double[] array = listOfParam.get(i);
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

        rp.setUseFirstLayer(true);
        rp.setUseBaseImpedance(true);
        rp.setMainMeasurement();

    }

    public void addModel(String flowName, AbstractModel model){
        registeredModels.put(flowName, model);
        model.addPropertyChangeListener(this);
    }
}
