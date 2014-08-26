import com.tihiy.comm.FileSignalReader;
import com.tihiy.comm.parse.Reo32Parser;
import com.tihiy.rclint.models.SignalModel;
import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractModel;
import com.tihiy.reo.ReoPostProcessor;
import com.tihiy.reonew.OneLayerModelSimple;
import com.tihiy.reonew.ReoProcessor;
import com.tihiy.reonew.SphereModelParam;
import com.tihiy.reonew.SphereModelSimple;

import java.io.File;
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
    public static final String FIRST_OLD = "first_old";
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
        addModel(FIRST_OLD, new SignalModel(FIRST_OLD));
        addModel(ECG, new SignalModel(ECG));

        List<Double> firstFilter = SlayerFilter.slayerFilter(map.get(Reo32Parser.Signal.Pulse_6), 20);
//        ((SignalModel)registeredModels.get(FIRST_OLD)).setList(map.get(Reo32Parser.Signal.Pulse_6));
//        ((SignalModel)registeredModels.get(FIRST)).setList(firstFilter);
        ((SignalModel)registeredModels.get(FIRST)).setList(map.get(Reo32Parser.Signal.Pulse_6));
        ((SignalModel)registeredModels.get(PRECARD_1)).setList(map.get(Reo32Parser.Signal.Pulse_1));
        ((SignalModel)registeredModels.get(PRECARD_2)).setList(map.get(Reo32Parser.Signal.Pulse_2));
        ((SignalModel)registeredModels.get(PRECARD_3)).setList(map.get(Reo32Parser.Signal.Pulse_3));
        ((SignalModel)registeredModels.get(PRECARD_4)).setList(map.get(Reo32Parser.Signal.Pulse_4));
        ((SignalModel)registeredModels.get(PRECARD_5)).setList(map.get(Reo32Parser.Signal.Pulse_5));
        ((SignalModel)registeredModels.get(ECG)).setList(map.get(Reo32Parser.Signal.ECG));
    }
    public void clearSignalOld(List<double[]> listOfParam){
        ReoPostProcessor rp = new ReoPostProcessor();
        for(int i = 0; i < 5; i++){
            String precardio = "precard_"+(i+1);
            String base = "precard_base_"+(i+1);
            String radius = "radius_"+(i+1);
            String clear = "clear_"+(i+1);
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
                List<Double> listOfRadius = rp.getRadiusWithRo1();
                List<Double> listOfImpedance = rp.getImpedance(listOfRadius);

                SignalModel modelRad = new SignalModel(radius);
                addModel(radius, modelRad);
                modelRad.setList(listOfRadius);

                SignalModel modelImpedance = new SignalModel(clear);
                addModel(clear, modelImpedance);
                modelImpedance.setList(listOfImpedance);

            }else{
                Logger.getLogger(getClass().getName()).info("MapModel don't contain model '"+ precardio + "'! ");
            }
        }
    }
    public void clearSignal(List<double[]> listOfParam){
        for(int i = 0; i < 5; i++){
            System.out.println("Signal #"+ (i+1));

            String precardio = "precard_"+(i+1);
            String base = "precard_base_"+(i+1);
            String radius = "radius_"+(i+1);
            String clear = "clear_"+(i+1);

            double[] array = listOfParam.get(i);
            SphereModelParam param = new SphereModelParam(array[8], 1.35, array[0], array[1], array[4], array[5], array[2],array[3]);
            SphereModelSimple model = new SphereModelSimple(param);
            ReoProcessor processor = new ReoProcessor(model);
            OneLayerModelSimple oneModel = new OneLayerModelSimple(array[6], array[7]);

            if(registeredModels.containsKey(precardio)){
                List<Double> listOfDeltaRo = oneModel.getDeltaRoList(((SignalModel)registeredModels.get(FIRST)).getList());

//                List<Double> listOfRadius = processor.getDeltaRadiusList(
//                        ((SignalModel)registeredModels.get(precardio)).getList(),
//                        ReoProcessor.MILLI_OMH
//                );
//
                List<Double> listOfRadius = processor.getDeltaRadiusList(
                        ((SignalModel)registeredModels.get(precardio)).getList(),
                        listOfDeltaRo,
                        ReoProcessor.MILLI_OMH
                );

                List<Double> listOfImpedance = processor.getImpedanceList(listOfRadius);


                SignalModel modelRad = new SignalModel(radius);
                addModel(radius, modelRad);
                modelRad.setList(listOfRadius);

                SignalModel modelImpedance = new SignalModel(clear);
                addModel(clear, modelImpedance);
                modelImpedance.setList(listOfImpedance);

            }else{
                Logger.getLogger(getClass().getName()).info("MapModel don't contain model '"+ precardio + "'! ");
            }
        }
    }

    public void addModel(String flowName, AbstractModel model){
        registeredModels.put(flowName, model);
        model.addPropertyChangeListener(this);
    }
}
