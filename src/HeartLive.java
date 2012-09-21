import service.Settings;
import communication.TiComPort;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 25.07.12
 * Time: 16:41
 */
public class HeartLive {
//    public static List<Integer> list = new ArrayList();

    public static void main(String[] args) {
        Settings config =  new Settings("COM3");
        TiComPort tiComPort = new TiComPort(config);

        try {
            tiComPort.findPorts();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("End");
    }
}
