import com.tihiy.comm.serial.ComPortListener;
import com.tihiy.comm.serial.DefaultSimpleSignalManager;
import com.tihiy.comm.serial.protocols.Protocol;
import com.tihiy.rclint.control.SimpleController;
import com.tihiy.rclint.models.SignalDynamicModelInteger;
import com.tihiy.rclint.view.SignalPanel;
import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SignalViewer {
    public static void main(String[] args) {
        DefaultSimpleSignalManager<Integer> manager = new DefaultSimpleSignalManager<>();
        manager.setProtocol(Protocol.ByteFlow);
        ComPortListener listener = ComPortListener.getInstance(manager);
        Executors.newSingleThreadScheduledExecutor().schedule(listener, 2L, TimeUnit.SECONDS);


        SimpleController controller = new SimpleController();
        SignalDynamicModelInteger model = new SignalDynamicModelInteger("model");
        SignalPanel panel = new SignalPanel("model", true);
        controller.addModel("model", model);
        controller.addView(panel);
        model.setQueue(manager.getQueue());

        JFrame frame = new JFrame();
        frame.setSize(300,300);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
