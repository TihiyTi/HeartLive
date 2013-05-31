package com.tihiy.comm.serial;

import com.tihiy.comm.serial.protocols.Protocol;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aleksey
 * Date: 23.03.13
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public interface SignalReturn {

    public void getSamples(double[] samples, String flowName);

    public void getSamples(byte[] samples, String flowName);

    public void createSignal(String flowName);

    Protocol getProtocol();

    <T> void  getSamples(List<T> samples);


    // Todo maybe add STATIC return of data
//    public List[] getListSamples();


}
