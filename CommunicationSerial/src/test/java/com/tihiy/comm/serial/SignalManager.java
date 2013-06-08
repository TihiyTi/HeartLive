package com.tihiy.comm.serial;

import com.tihiy.comm.serial.protocols.Protocol;
import com.tihiy.rclint.control.Controller;
import com.tihiy.rclint.models.SignalModel;

public class SignalManager extends AbstractSignalManager<Integer> {
    Controller cn;

    public SignalManager(Controller cn) {
        this.cn = cn;
    }


    @Override
    public void createSignal(String flowName){
        super.createSignal(flowName);
        if(getProtocol().equals(Protocol.SimpleEcg)){
            cn.createSignalModel(flowName, new SignalModel());
        }

    }
}
