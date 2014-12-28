package com.tihiy.fxclient;

import com.tihiy.fxclient.element.SignalModel;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.*;

public class FXTest2 extends Application {
    private static final String SCENE_XML = "scene2.fxml";
    private static final String KEYS_PROPS = "keys";
    private static final String FX_CONTEXT_XML = "context2.xml";

    private final ListableBeanFactory context = new ClassPathXmlApplicationContext(
            getClass().getPackage().getName().replaceAll("\\.", "/") + "/" + FX_CONTEXT_XML
    );

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try{
            Class clazz = FXTest.class;
            URL location1 = clazz.getResource(SCENE_XML);
            FXMLLoader fxmlLoader = new FXMLLoader(location1);
//            fxmlLoader.setControllerFactory(e -> BeanFactoryUtils.beanOfType(context, e));
            fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
                Map<Class<?>, LinkedList<Object>> map = new HashMap<>();
                @Override
                public Object call(Class<?> param) {
                    if(BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context,param).length > 1){
                        if(!map.containsKey(param)){
                            map.put(param, new LinkedList<>());
                            Collection<?> col = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, param).values();
                            LinkedList<Object> list = map.get(param);
                            col.forEach(list::add);
                            System.out.println();
                        }
                        return map.get(param).pollFirst();
                    }else{
                        return BeanFactoryUtils.beanOfType(context, param);
                    }
                }
            });
            Pane root = fxmlLoader.load();

            stage.setScene(new Scene(root));
            stage.show();

            SignalModel model = (SignalModel)context.getBean("mo1");
            SignalModel model2 = (SignalModel)context.getBean("mo2");
            List<Double> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                list.add(Math.random()*100);
            }
            List<Double> list2 = Arrays.asList(21d,4d,3d,45d,23d);
            model.setList(list);
            model2.setList(list2);

        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    @Override
    public void stop() throws Exception{
        super.stop();
        System.exit(0);
    }
}
