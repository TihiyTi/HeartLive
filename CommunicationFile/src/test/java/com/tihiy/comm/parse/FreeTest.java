package com.tihiy.comm.parse;

import com.google.common.base.CharMatcher;
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
        String s = "       0:00.011\t -0.145\t -0.065";
        //System.out.println(s.split("."));
        Splitter MY_SPLITTER = Splitter.on('\t').trimResults().omitEmptyStrings();
        Iterable list = MY_SPLITTER.split(s);
        for (Object s1 : list) {
            System.out.println("{"+s1+"}");
        }
    }
    @Test
    public void testTwo(){
        System.out.println("Test TWO");
        CharMatcher trimmer = CharMatcher.anyOf("-.0123456789");
        Splitter splitter = Splitter.on('\t').trimResults().omitEmptyStrings().trimResults(trimmer);
        Iterable list = splitter.split("       0:00.011\t -0.145\t -0.065");
        for (Object s1 : list) {
            System.out.println("{"+s1+"}");
        }
    }
}
