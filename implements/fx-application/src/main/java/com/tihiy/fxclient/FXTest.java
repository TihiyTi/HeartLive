package com.tihiy.fxclient;

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

public class FXTest extends Application {
    private static final String SCENE_XML = "scene.fxml";
    private static final String KEYS_PROPS = "keys";
    private static final String FX_CONTEXT_XML = "context.xml";


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
            Pane root = fxmlLoader.load();

            stage.setScene(new Scene(root));
            stage.show();

//            Controller mc = (Controller) context.getBean("mc");
            StringProperty prop = new SimpleStringProperty();
//            mc.setBindProperty(prop);
            prop.addListener((observable, oldValue, newValue) -> System.out.println("TEST COMPLETE!!"));
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
