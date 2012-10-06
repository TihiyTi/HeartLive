package com.tihiy.comm.parse;

import com.google.common.base.Splitter;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 05.10.12
 * Time: 18:02
 * To change this template use File | Settings | File Templates.
 */
public class FreeTest extends TestCase {
    @Test
    public void testFirst(){
//       Test GUAVA function
        Splitter splitter;
        String s = "foo, ,bar, quux,";
        //System.out.println(s.split("."));
        Splitter MY_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();
        Iterable list = MY_SPLITTER.split(s);
        for (Object s1 : list) {
            System.out.println(""+s1);
        }
    }
}
