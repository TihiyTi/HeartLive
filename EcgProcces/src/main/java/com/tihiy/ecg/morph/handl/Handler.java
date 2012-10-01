package com.tihiy.ecg.morph.handl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 28.09.12
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */
public interface Handler {
    public List<Float> getData();
    public void setData(List<Float> data);
}
