import com.ak.util.GenericStorage;
import com.tihiy.comm.FileSignalReader;
import com.tihiy.rclint.models.SignalModel;
import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractModel;
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

public class ThisController extends AbstractController {
    //private final Map<String, AbstractModel> registeredModels = new HashMap<>();
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

    public static final List<String> MODELS = Arrays.asList(ECG, FIRST, PRECARD_1, PRECARD_2, PRECARD_3, PRECARD_4, PRECARD_5,
            PRECARD_BASE_1, PRECARD_BASE_2,PRECARD_BASE_3, PRECARD_BASE_4, PRECARD_BASE_5);
    public static final List<String> CLEAR_SIGNALS = Arrays.asList(CLEAR_1,CLEAR_2,CLEAR_3,CLEAR_4,CLEAR_5);

    public void addSignals(File dataFile){
        identityPatient = dataFile.getName();

        FileSignalReader reader = new FileSignalReader();
        Map<String, List<Double>> map = reader.readFileToMapNew(dataFile);

        List<String> listOfInItem = new ArrayList<>();
        map.keySet().forEach(listOfInItem::add);

        Map<String,String> saveMap = GenericStorage.newMapStorage(identityPatient+ "confirm","").load();
        if(saveMap!=null){
            ConformityModel confimModel = (ConformityModel)registeredModels.get(CONFIRM_VIEW);
            confimModel.setInItem(MODELS);
            confimModel.setOutItem(listOfInItem);
            confimModel.setDataMap(map);
            confirmSignal(saveMap);
        }else{
            ConformityModel confimModel = (ConformityModel)registeredModels.get(CONFIRM_VIEW);
            confimModel.setInItem(MODELS);
            confimModel.setOutItem(listOfInItem);
            confimModel.setDataMap(map);
        }
        signalFilterForAlex();
    }

    private void signalFilterForAlex(){
        List<String> listSignalForFilter = Arrays.asList(PRECARD_2,PRECARD_4, PRECARD_5);
        listSignalForFilter.forEach(e -> {
            List<Double> list = ((SignalModel)registeredModels.get(e)).getList();
            List<Double> filtList = SlayerFilter.slayerFilter(list, 20);
            ((SignalModel)registeredModels.get(e)).setList(filtList);
        });
    }
//
    public void confirmSignal(Map<String,String> map){
        GenericStorage.newMapStorage(identityPatient+ "confirm", "").save(map);
        ConformityModel confimModel = (ConformityModel)registeredModels.get(CONFIRM_VIEW);
        confimModel.setConfirmMap(map);
        Map<String, List<Double>>  dataMap = confimModel.getDataMap();
        map.keySet().forEach(e -> {
            addModel(e, new SignalModel(e));
            SignalModel model = (SignalModel) registeredModels.get(e);
            List<Double> list = dataMap.get(map.get(e));
            model.setList(list);
        });
    }
     public void unipolarFirst(){
         List<Double> unipolarFirst = SignalProccesor.makeSignalUnipolar(((SignalModel) registeredModels.get(FIRST)).getList());
         SignalModel model = (SignalModel)registeredModels.get(FIRST);
         model.setList(unipolarFirst);
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
        if(!registeredModels.containsKey(FIRST_OLD)){
            addModel(FIRST_OLD, new SignalModel(FIRST_OLD));
            List<Double> filter = SlayerFilter.slayerFilter(((SignalModel)registeredModels.get(FIRST)).getList(), 20);
            ((SignalModel)registeredModels.get(FIRST_OLD)).setList(filter);
        }

        List<Double> firstUnFilter = ((SignalModel)registeredModels.get(FIRST)).getList();
        List<Double> firstFilter = ((SignalModel)registeredModels.get(FIRST_OLD)).getList();

        SignalModel modelSee = ((SignalModel)registeredModels.get(FIRST));
        SignalModel modelUnSee = ((SignalModel)registeredModels.get(FIRST_OLD));

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

    public void addModel(String flowName, AbstractModel model){
        registeredModels.put(flowName, model);
        model.addPropertyChangeListener(this);
    }

    public void correlation(){
        //Ivan
//        List<Double> moveOrigin_1 =  Arrays.asList(0.,0.,0.,0.,3.6,5.4,5.4,7.2,7.2);
//        List<Double> moveOrigin_2 =  Arrays.asList(1.77,3.54,3.54,3.54,5.31,7.08,10.62,12.39,15.93);
//        List<Double> moveOrigin_3 =  Arrays.asList(0.,1.75,1.75,3.5,7.,8.75,10.5,12.25,14.2);
//        List<Double> moveOrigin_4 =  Arrays.asList(-1.75,-1.75,0.,1.775,3.5,5.25,8.75,10.5,12.25);
//        List<Double> moveOrigin_5 =  Arrays.asList(1.71,3.42,5.13,6.84,8.55,10.26,11.97,11.97,13.68);

        //Alex
        List<Double> moveOrigin_1 =  Arrays.asList(0., 0., 0., 1.7, 1.7, 5.1, 1.7, 1.7, 1.7, 1.7);
        List<Double> moveOrigin_2 =  Arrays.asList(0., 0., 1.75, 1.75, 1.75, 5.25, 5.25, 7., 7., 7.);
        List<Double> moveOrigin_3 =  Arrays.asList(0., 0., 1.75, 1.75, 1.75, 5.25, 5.25, 7., 7., 7.);
        List<Double> moveOrigin_4 =  Arrays.asList(0., 1.8, 3.6, 3.6, 5.4, 5.4, 7.2, 9., 9., 9.);
        List<Double> moveOrigin_5 =  Arrays.asList(0., 1.8, 5.4, 9., 12.6, 16.2, 16.2, 16.2, 18., 18.);


        List<List<Double>> listOfMoveOrigin = new ArrayList<>();
        listOfMoveOrigin.addAll(Arrays.asList(moveOrigin_1,moveOrigin_2,moveOrigin_3,moveOrigin_4,moveOrigin_5));
        int i = 0;
        for (String signalName : CLEAR_SIGNALS) {
            System.out.println("Channel #"+(i+1));
            List<Double> signal = ((SignalModel) registeredModels.get(signalName)).getList();
//            signal = SignalProccesor.invert(signal);   Not need for CLEAR signal
            List<Double> moveBadList =  listOfMoveOrigin.get(i);

            MySpesificCorrelation myCor = new MySpesificCorrelation(moveBadList, signal, 5);
            myCor.removeTrends = true;
            myCor.getSignalInFrame(false, signalName);
            System.out.println("Correlation ");
            myCor.getCorrel().forEach(e-> System.out.printf("%.3f  ", e));
            System.out.println();
            myCor.getSignalInFrame(true, signalName);
            System.out.println("Correlation after approximation");
            myCor.getCorrelAprox().forEach(e-> System.out.printf("%.3f  ", e));
            System.out.println();

            i++;
        }

//        String signalName = CLEAR_1;
//        List<Double> signal = ((SignalModel) registeredModels.get(signalName)).getList();
//        signal = SignalProccesor.invert(signal);
//        List<Double> moveBadList =  Arrays.asList(0.,0.,0.,0.,3.6,5.4,5.4,7.2,7.2);
//
//        MySpesificCorrelation myCor = new MySpesificCorrelation(moveBadList, signal, 5);
//        System.out.println("First channel");
//        myCor.getSignalInFrame(false);
//        System.out.println("Corellation ");
//        myCor.getCorrel().forEach(e-> System.out.printf("%.3f  ", e));
//        myCor.getSignalInFrame(true);
//        System.out.println("Corellation after approximation");
//        myCor.getCorrelAprox().forEach(e-> System.out.printf("%.3f  ", e));

    }
}
