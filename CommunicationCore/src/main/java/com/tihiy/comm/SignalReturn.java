package com.tihiy.comm;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 23.03.13
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public interface SignalReturn {

    public void getSamples(double[] samples, String flowName);

    public void createSignal(String flowName);

    // Todo maybe add STATIC return of data
//    public List[] getListSamples();


}
