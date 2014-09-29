package com.tihiy.fxclient;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import javax.xml.soap.Node;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public TabPane tab1;
    public TabPane tab2;
    public TabParamController tab1Controller;
    public TabParamController tab2Controller;
    public BorderPane sid;

    StringProperty bufProp1 = new SimpleStringProperty();

    public Label label;
    public Label label2;
    public TextField textField;
    public FlowPane pane;
    public Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Class clazz = FXTest.class;
        URL location1 = clazz.getResource("tabparam.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location1);
        try {
            tab1 = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sid.setRight(tab1);
        tab1Controller = fxmlLoader.getController();
        FXMLLoader fxmlLoader2 = new FXMLLoader(location1);
        try {
            tab2 = fxmlLoader2.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sid.setLeft(tab2);
        tab2Controller = fxmlLoader2.getController();


        tab1Controller.bindProperties(tab2Controller.getProperties());



        System.out.println("Controller FX "+ this.toString());
        Bindings.bindBidirectional(label.textProperty(), bufProp1);
        Bindings.bindBidirectional(label2.textProperty(), bufProp1);
        Bindings.bindBidirectional(textField.textProperty(), bufProp1);
    }

    public void setBindProperty(StringProperty property){
        Bindings.bindBidirectional(bufProp1, property);
    }
}
