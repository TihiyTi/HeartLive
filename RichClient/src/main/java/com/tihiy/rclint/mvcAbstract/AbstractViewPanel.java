/*
 * AbstractViewPanel.java
 *
 * Created on January 22, 2007, 9:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.tihiy.rclint.mvcAbstract;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the base level abstraction for views in this example. All
 * views that extend this class also extend JPanel (which is useful for performing
 * GUI manipulations on the view in NetBeans Matisse), as well as providing the 
 * modelPropertyChange() method that controllers can use to propogate model 
 * property changes.
 *
 * @author Robert Eckstein
 */

public abstract class AbstractViewPanel extends JPanel {

    protected List<ViewAddOnInterface> addOns;

    protected String viewName;
    /**
     * Called by the controller when it needs to pass along a property change 
     * from a model.
     *
     * @param evt The property change event from the model
     */
    public abstract void modelPropertyChange(PropertyChangeEvent evt);
    
    public void addAddOn(ViewAddOnInterface addOn, String modelName){
        if(modelName.equals(viewName)){
            if(addOns ==  null){
                addOns = new ArrayList<>();
            }
            addOns.add(addOn);
        }
    }
}
