package com.tihiy.comm.parse;

import com.ak.util.GenericStorage;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LocalStorageTest {
    @Test
    public void testLocalStorage(){
        String fruit = "apple";
        String animal = "bat";
        String drink = "vodka";
        Map<String, String> map = new HashMap<String, String>();
        map.put("fruit", fruit);
        map.put("animal", animal);
        map.put("drink", drink);
        GenericStorage.newMapStorage("myMap", "").save(map);
        Map myMap;
        myMap = GenericStorage.newMapStorage("myMap", "").load();
        for(Object element: myMap.keySet()){
            System.out.println(myMap.get(element));
        }
    }
}
