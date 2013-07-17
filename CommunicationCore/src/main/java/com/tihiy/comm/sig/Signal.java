package com.tihiy.comm.sig;

import java.util.HashMap;
import java.util.Map;

public class Signal implements SignalInterface{
    Map<String, Channel> channelMap = new HashMap<>();

    public Channel getChannel(String nameChannel){
        if(channelMap.containsKey(nameChannel)){
            return channelMap.get(nameChannel);
        }else{
            channelMap.put(nameChannel, new Channel());
            return channelMap.get(nameChannel);
        }
    }
}
