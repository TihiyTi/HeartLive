import com.tihiy.comm.FileSignalReader;
import com.tihiy.comm.parse.Reo32Parser;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SimpleTest {
    @Test
    public void test() throws IOException {
        File file = new File(getClass().getResource("1.txt").getFile());
        FileSignalReader reader = new FileSignalReader();
        String string = reader.readFile(file);
        System.out.println("Исходник");
        System.out.println(string);
        Reo32Parser parser = new Reo32Parser();
        parser.skipLine(1).skipColumn(0);
        List<List<Double>> dataList = parser.parse(string);
        for(List<Double> list: dataList){
            list.toString();
        }
    }
}
