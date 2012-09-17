package service;

import junit.framework.TestCase;

/**
 * User: Tihiy
 * Date: 17.09.12
 * Time: 15:14
 */
public class SignalReaderTest extends TestCase {
    public void testStartRead() throws Exception {
        Settings config = new Settings("COM3");
        SignalReader sr = new SignalReader(config);
       // sr.startRead();
    }
}
