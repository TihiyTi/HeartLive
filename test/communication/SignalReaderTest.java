package communication;

import junit.framework.TestCase;
import service.Settings;
import communication.SignalReader;

import java.io.File;

/**
 * User: Tihiy
 * Date: 17.09.12
 * Time: 15:14
 */
public class SignalReaderTest extends TestCase {
    public void testStartRead() throws Exception {
        Settings config = new Settings("COM3");
        SignalReader sr = new SignalReader(new File("C:\\Users\\aleksey\\IdeaProjects\\HeartLive\\test\\sirvice\\samples.txt"));
       // sr.startRead();
    }

}
