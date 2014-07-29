package com.tihiy.comm.parse;

import com.tihiy.comm.FileSignalReader;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SimpleTest {
    @Test
    public void test() throws IOException {
        File file = new File(getClass().getResource("signal1.txt").getFile());
        FileSignalReader reader = new FileSignalReader();
        String string = reader.readFile(file);
        System.out.println(string);
        Reo32Parser parser = new Reo32Parser();
        Map<Reo32Parser.Signal, List<Double>> map = parser.parse(string);
    }
}
