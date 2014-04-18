import com.tihiy.comm.FileSignalReader;
import com.tihiy.comm.parse.Reo32Parser;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SimpleTest {
    @Test
    public void test() throws IOException {
        File file = new File(getClass().getResource("signal.txt").getFile());
        FileSignalReader reader = new FileSignalReader();
        String string = reader.readFile(file);
        System.out.println(string);
        Reo32Parser parser = new Reo32Parser();
        parser.parse(string);
    }
}
