package com.tihiy.fxclient;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TabParamController implements Initializable{
    private List<List<DoubleProperty>> listOfList = new ArrayList<>();
    public VBox param1;
    public VBox param2;
    public VBox param3;
    public VBox param4;
    public VBox param5;


    public ParamController param1Controller;
    public ParamController param2Controller;
    public ParamController param3Controller;
    public ParamController param4Controller;
    public ParamController param5Controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listOfList.add(param1Controller.getListOfProperties());
        listOfList.add(param2Controller.getListOfProperties());
        listOfList.add(param3Controller.getListOfProperties());
        listOfList.add(param4Controller.getListOfProperties());
        listOfList.add(param5Controller.getListOfProperties());
    }

    public void bindProperties(List<List<DoubleProperty>> listOfProp){
        listOfProp.forEach(modelProperties-> bindTwoList(modelProperties, listOfList.get(listOfProp.indexOf(modelProperties))));
    }
    public List<List<DoubleProperty>> getProperties(){
        return listOfList;
    }
    private void bindTwoList(List<DoubleProperty> model,List<DoubleProperty> view){
        model.forEach(e -> Bindings.bindBidirectional(e, view.get(model.indexOf(e))));
    }
}