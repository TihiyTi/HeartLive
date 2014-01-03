/*
 * AbstractModel.java
 *
 * Created on January 22, 2007, 3:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.tihiy.rclint.mvcAbstract;

import com.tihiy.rclint.addon.AddOnModelInterface;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides base level functionality for all models, including a 
 * support for a property change mechanism (using the PropertyChangeSupport class),
 * as well as a convenience method that other objects can use to reset model state.
 * @author Robert Eckstein
 */
public abstract class AbstractModel
{
    protected List<AddOnModelInterface> addOns;

    public void addAddOn(AddOnModelInterface addOn){
        if(addOns == null){
            addOns =  new ArrayList<>();
        }
        addOns.add(addOn);
    }

    /**
     * Convenience class that allow others to observe changes to the model properties
     */
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Adds a property change listener to the observer list.
     * @param l The property change listener
     */
    public final void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }
    
    /**
     * Removes a property change listener from the observer list.
     * @param l The property change listener
     */
    public final void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    
    
    /**
     * Fires an event to all registered listeners informing them that a property in
     * this model has changed.
     * @param propertyName The name of the property
     * @param oldValue The previous value of the property before the change
     * @param newValue The new property value after the change
     */
    protected final void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    protected final void fireIndexedPropertyChange(String propertyName, int index, Object oldValue, Object newValue){
        propertyChangeSupport.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
    }
}
    

