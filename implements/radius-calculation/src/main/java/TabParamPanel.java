import com.ak.util.GenericStorage;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class TabParamPanel extends JTabbedPane{

    public TabParamPanel() {
        initComponent();
    }

    private void initComponent() {
        for(int i=0; i <5; i++){
            addTab(""+(i+1), new ParamPanel());
        }
        loadParam();
    }

    protected List<double[]> getParam(){
        List<double[]> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            setSelectedIndex(i);
            ParamPanel panel = (ParamPanel) getSelectedComponent();
            double[] array = panel.getParam();
            list.add(array);
        }
        Map<String, List<double[]>> map = new HashMap<>();
        map.put("param", list);
        GenericStorage.newMapStorage("param", "").save(map);
        return list;
    }
    protected void loadParam(){
        Map<String, List<double[]>> map = GenericStorage.newMapStorage("param", "").load();
        List<double[]> list = (List<double[]>) GenericStorage.newMapStorage("param", "").load().get("param");
        if(list!=null){
            for(int i = 0; i < 5; i++ ){
                setSelectedIndex(i);
                ParamPanel panel = (ParamPanel) getSelectedComponent();
                panel.setParam(list.get(i));
            }
        }
    }
    protected void setDefaultParam(){
        double[] mas = {0.04,0.02,0,0.037,0.045, 0.019, 0.06, 0.03, 5};
        for(int i = 0; i < 5; i++ ){
            setSelectedIndex(i);
            ParamPanel panel = (ParamPanel) getSelectedComponent();
            panel.setParam(mas);
        }
    }
}
