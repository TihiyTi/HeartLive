import com.ak.util.GenericStorage;
import com.tihiy.comm.FileSignalReader;
import com.tihiy.comm.parse.Reo32Parser;
import com.tihiy.jfreeclient.SignalViewCreator;
import com.tihiy.rclint.models.SignalModel;
import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractModel;
import com.tihiy.reo.ReoPostProcessor;
import com.tihiy.reonew.OneLayerModelSimple;
import com.tihiy.reonew.ReoProcessor;
import com.tihiy.reonew.SphereModelParam;
import com.tihiy.reonew.SphereModelSimple;
import com.tihiy.reonew.utils.DxMatrixFinder;
import model.ConformityModel;
import org.ejml.simple.SimpleMatrix;
import settings.SistolaInterval;
import utils.ShortSignalCreator;
import utils.SlayerFilter;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;

import static settings.SistolaInterval.*;

public class ThisController extends AbstractController {
    private final Map<String, AbstractModel> registeredModels = new HashMap<>();
    private String identityPatient;

    public static final String CONFIRM_VIEW = "confirm";

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

    public static final List<String> models = Arrays.asList(ECG, FIRST, PRECARD_1, PRECARD_2, PRECARD_3, PRECARD_4, PRECARD_5,
            PRECARD_BASE_1, PRECARD_BASE_2,PRECARD_BASE_3, PRECARD_BASE_4, PRECARD_BASE_5);


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

        addModel(PRECARD_BASE_1, new SignalModel(PRECARD_BASE_1));
        addModel(PRECARD_BASE_2, new SignalModel(PRECARD_BASE_2));
        addModel(PRECARD_BASE_3, new SignalModel(PRECARD_BASE_3));
        addModel(PRECARD_BASE_4, new SignalModel(PRECARD_BASE_4));
        addModel(PRECARD_BASE_5, new SignalModel(PRECARD_BASE_5));

        List<Double> firstFilter = SlayerFilter.slayerFilter(map.get(Reo32Parser.Signal.Pulse_6), 20);
        ((SignalModel)registeredModels.get(FIRST)).setList(map.get(Reo32Parser.Signal.Pulse_6));
        ((SignalModel)registeredModels.get(FIRST_OLD)).setList(firstFilter);
        ((SignalModel)registeredModels.get(FIRST)).setList(map.get(Reo32Parser.Signal.Pulse_6));
        ((SignalModel)registeredModels.get(PRECARD_1)).setList(map.get(Reo32Parser.Signal.Pulse_1));
        ((SignalModel)registeredModels.get(PRECARD_2)).setList(map.get(Reo32Parser.Signal.Pulse_2));
        ((SignalModel)registeredModels.get(PRECARD_3)).setList(map.get(Reo32Parser.Signal.Pulse_3));
        ((SignalModel)registeredModels.get(PRECARD_4)).setList(map.get(Reo32Parser.Signal.Pulse_4));
        ((SignalModel)registeredModels.get(PRECARD_5)).setList(map.get(Reo32Parser.Signal.Pulse_5));
        ((SignalModel)registeredModels.get(ECG)).setList(map.get(Reo32Parser.Signal.ECG));

        ((SignalModel)registeredModels.get(PRECARD_BASE_1)).setList(map.get(Reo32Parser.Signal.Base_1));
        ((SignalModel)registeredModels.get(PRECARD_BASE_2)).setList(map.get(Reo32Parser.Signal.Base_2));
        ((SignalModel)registeredModels.get(PRECARD_BASE_3)).setList(map.get(Reo32Parser.Signal.Base_3));
        ((SignalModel)registeredModels.get(PRECARD_BASE_4)).setList(map.get(Reo32Parser.Signal.Base_4));
        ((SignalModel)registeredModels.get(PRECARD_BASE_5)).setList(map.get(Reo32Parser.Signal.Base_5));
    }
    public void addSignalsNew(File dataFile){
        identityPatient = dataFile.getName();

        FileSignalReader reader = new FileSignalReader();
        Map<String, List<Double>> map = reader.readFileToMapNew(dataFile);

        List<String> listOfInItem = new ArrayList<>();
        map.keySet().forEach(listOfInItem::add);

        Map<String,String> saveMap = GenericStorage.newMapStorage(identityPatient+ "confirm","").load();
        if(saveMap!=null){
            ConformityModel confimModel = (ConformityModel)registeredModels.get(CONFIRM_VIEW);
            confimModel.setInItem(models);
            confimModel.setOutItem(listOfInItem);
            confimModel.setDataMap(map);
            confirmSignal(saveMap);
        }else{
            ConformityModel confimModel = (ConformityModel)registeredModels.get(CONFIRM_VIEW);
            confimModel.setInItem(models);
            confimModel.setOutItem(listOfInItem);
            confimModel.setDataMap(map);
        }
    }
//
    public void confirmSignal(Map<String,String> map){
        GenericStorage.newMapStorage(identityPatient+ "confirm", "").save(map);
        ConformityModel confimModel = (ConformityModel)registeredModels.get(CONFIRM_VIEW);
        confimModel.setConfirmMap(map);
        Map<String, List<Double>>  dataMap = confimModel.getDataMap();
        map.keySet().forEach(e -> {
            addModel(e, new SignalModel(e));
//            System.out.println(e);
            SignalModel model = (SignalModel) registeredModels.get(e);
            List<Double> list = dataMap.get(map.get(e));
            model.setList(list);
        });
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
    public void calcMoveMatrix(){
        DxMatrixFinder finder = new DxMatrixFinder();
        calcMoveMatrix(finder);
    }
    public void calcMoveMatrix(DxMatrixFinder finder){
        for(int k = 1; k <= SistolaInterval.getNumOfInterval(); k++){
            System.out.println("Interval number " + k);
            SimpleMatrix dz = new SimpleMatrix(5, 5);

            List<Double> list1 = ((SignalModel)registeredModels.get("clear_1")).getList();
            List<Double> list2 = ((SignalModel)registeredModels.get("clear_2")).getList();
            List<Double> list3 = ((SignalModel)registeredModels.get("clear_3")).getList();
            List<Double> list4 = ((SignalModel)registeredModels.get("clear_4")).getList();
            List<Double> list5 = ((SignalModel)registeredModels.get("clear_5")).getList();

            int numOfStep = 6;
            int[] indexes = new int[numOfStep];
            List<Integer> counts = Arrays.asList(1, 2, 3, 4, 5);
            for (int i = 0; i < numOfStep; i++) {
                indexes[i] = SistolaInterval.getPoint(k, SistolaInterval.BEGIN) +
                        i * (SistolaInterval.getPoint(k, SistolaInterval.END) - SistolaInterval.getPoint(k, SistolaInterval.BEGIN)) / numOfStep;
            }

            int j = 0;
            for (Integer i : counts) {
                dz.setRow(j, 0, (int) ((list1.get(indexes[i]) - list1.get(indexes[0])) * 1000),
                        (int) ((list2.get(indexes[i]) - list2.get(indexes[0])) * 1000),
                        (int) ((list3.get(indexes[i]) - list3.get(indexes[0])) * 1000),
                        (int) ((list4.get(indexes[i]) - list4.get(indexes[0])) * 1000),
                        (int) ((list5.get(indexes[i]) - list5.get(indexes[0])) * 1000)
                );
                j++;
            }
            finder.getDx(dz);
        }
    }
    public void filterFirstLayer(){
//        if(!registeredModels.containsKey(FIRST_OLD)){
            addModel(FIRST_OLD, new SignalModel(FIRST_OLD));
            List<Double> filter = SlayerFilter.slayerFilter(((SignalModel)registeredModels.get(FIRST)).getList(), 20);
            ((SignalModel)registeredModels.get(FIRST_OLD)).setList(filter);
//        }

        List<Double> firstUnFilter = ((SignalModel)registeredModels.get(FIRST)).getList();
        List<Double> firstFilter = ((SignalModel)registeredModels.get(FIRST_OLD)).getList();

        SignalModel modelSee = ((SignalModel)registeredModels.get(FIRST));
        SignalModel modelUnSee = ((SignalModel)registeredModels.get(FIRST_OLD));

//        List<List<Double>> signals = Arrays.asList(modelSee.getList(), modelUnSee.getList());
//        List<String> names = Arrays.asList("filter","unfilter");
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.getContentPane().add(new SignalJFreePanel(signals, names));
//        frame.setVisible(true);

        modelSee.setList(firstFilter);
        modelUnSee.setList(firstUnFilter);
    }
    public void getShortSignal(){
        String[] signalNames = new String[]{CLEAR_1,CLEAR_2,CLEAR_3,CLEAR_4, CLEAR_5};
        List<Double>[] signals = new List[signalNames.length];
        for (int i = 0; i < signalNames.length; i++) {
            signals[i] = ((SignalModel) registeredModels.get(signalNames[i])).getList();
        }
        ShortSignalCreator.createAndViewShortMultiSignal(signalNames, signals);
    }

    public void fullCacl(List<double[]> listOfParam){
        System.out.println("SIGNAL UNfiltered");
        clearSignal(listOfParam);
        DxMatrixFinder finder = new DxMatrixFinder();
        for(int i = 1; i <= SistolaInterval.getNumOfInterval(); i++){
            setKoeffMatrix(i, finder);
            calcMoveMatrix(finder);
        }

        System.out.println("SIGNAL FILTERED");
        filterFirstLayer();
        clearSignal(listOfParam);
        finder = new DxMatrixFinder();
        for(int i = 1; i <= SistolaInterval.getNumOfInterval(); i++){
            setKoeffMatrix(i, finder);
            calcMoveMatrix(finder);
        }
    }

    private void setKoeffMatrix(int numOfinterval, DxMatrixFinder finder){
        if(numOfinterval > SistolaInterval.getNumOfInterval()){
            System.out.println("NO VALID  number of interval -  use 1 interval");
        }
        System.out.println("Use " + numOfinterval + " for calculation KoeffMatrix");
        SimpleMatrix dz = new SimpleMatrix(5, 5);

        List<Double> list1 = ((SignalModel)registeredModels.get("clear_1")).getList();
        List<Double> list2 = ((SignalModel)registeredModels.get("clear_2")).getList();
        List<Double> list3 = ((SignalModel)registeredModels.get("clear_3")).getList();
        List<Double> list4 = ((SignalModel)registeredModels.get("clear_4")).getList();
        List<Double> list5 = ((SignalModel)registeredModels.get("clear_5")).getList();

        int numOfStep = 6;
        int[] indexes = new int[numOfStep];
        List<Integer> counts = Arrays.asList(1, 2, 3, 4, 5);
        for (int i = 0; i < numOfStep; i++) {
            indexes[i] = SistolaInterval.getPoint(numOfinterval, SistolaInterval.BEGIN) +
                    i * (SistolaInterval.getPoint(numOfinterval, SistolaInterval.END) -
                         SistolaInterval.getPoint(numOfinterval, SistolaInterval.BEGIN)) / numOfStep;
        }

        int j = 0;
        for (Integer i : counts) {
            dz.setRow(j, 0, (int) ((list1.get(indexes[i]) - list1.get(indexes[0])) * 1000),
                    (int) ((list2.get(indexes[i]) - list2.get(indexes[0])) * 1000),
                    (int) ((list3.get(indexes[i]) - list3.get(indexes[0])) * 1000),
                    (int) ((list4.get(indexes[i]) - list4.get(indexes[0])) * 1000),
                    (int) ((list5.get(indexes[i]) - list5.get(indexes[0])) * 1000)
            );
            j++;
        }
        finder.setDz(dz);
    }

    //Рассчет
    public void calcDeltaRadius(){

    }

    public void addModel(String flowName, AbstractModel model){
        registeredModels.put(flowName, model);
        model.addPropertyChangeListener(this);
    }

    public void correlation(){
        String signalName = PRECARD_1;
//        String signalName = CLEAR_1;
        List<Double> bigSignal = ((SignalModel) registeredModels.get(signalName)).getList();
        List<Double> signal = new ArrayList<>();
        signal.addAll(bigSignal.subList(getPoint(1, BEGIN), getPoint(1, END)));
        signal = Invertor.invert(signal);

        List<Double> moveBadList =  Arrays.asList(0.,0.,0.,0.,3.6,5.4,5.4,7.2,7.2);

        List<Double> signalsArg = new ArrayList<>();
        for (int i = 0; i < signal.size(); i++) {
            signalsArg.add(i*1.*(moveBadList.size()-1)/signal.size());
        }

        List<Double> moveBadListArg = new ArrayList<>();
        for (int i = 0; i < moveBadList.size(); i++) {
            moveBadListArg.add((double)i);
        }

        PolynomialApproximator approximator = new PolynomialApproximator();
        List<Double> moveApprox = approximator.getApproxSignal(moveBadList, signalsArg, 5);

        Correlation cor = new Correlation();
        System.out.println("Correlation = " + cor.correlation(moveApprox, signal));

        SignalViewCreator.createSignalView(
                Arrays.asList(signal, moveBadList, moveApprox),
                Arrays.asList(signalsArg, moveBadListArg, signalsArg)
        );
    }
}
