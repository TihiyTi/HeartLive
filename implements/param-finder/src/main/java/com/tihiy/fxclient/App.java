package com.tihiy.fxclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class App extends Application {
    private static String SCENE_XML = "scene.fxml";
    private static String CONTEX_XML = "context.xml";

    protected final ListableBeanFactory context = new ClassPathXmlApplicationContext(
            getClass().getPackage().getName().replaceAll("\\.", "/")+"/" + CONTEX_XML);

    public static void up(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(SCENE_XML));
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            Map<Class<?>, LinkedList<Object>> map = new HashMap<>();
            @Override
            public Object call(Class<?> param) {
                if(BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context, param).length > 1){
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

    }

    @Override
    public void stop() throws Exception{
        super.stop();
        System.exit(0);
    }
}
