package com.tihiy.rclint.models;

import com.tihiy.rclint.mvcAbstract.AbstractModel;

import java.util.ArrayList;
import java.util.List;

public class SignalModelIntRT extends AbstractModel{

    //    BlockingQueue queue1 = new LinkedBlockingQueue();
//    BlockingQueue queue2 = new LinkedBlockingQueue();
    final List<Float> list = new ArrayList();

    public List<Float> getList() {
        return list;
    }

    public <T> void addToList(List<T> list){
        List<Float> oldlist = new ArrayList<>();
        oldlist.addAll(this.list);
        for(T i: list){
//            java.lang.Integer integer = (Integer)i;
            Float f = Float.valueOf((Integer)i);
            this.list.add(f);
            System.out.println("Value" + i);
        }
        firePropertyChange("list", oldlist, this.list);
    }

    @Override
    protected void applyCommand(String command) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
