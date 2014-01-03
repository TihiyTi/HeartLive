import com.tihiy.rclint.addon.AddOnFactory;
import com.tihiy.rclint.control.SimpleController;
import com.tihiy.rclint.models.SignalModelLite;
import com.tihiy.rclint.view.SignalPanelLite;
import javax.swing.*;

public class SignalViewerLite {
    public static void main(String[] args) {
        SimpleController mc = new SimpleController();
        SignalPanelLite panel = new SignalPanelLite("signal");
        SignalModelLite model = new SignalModelLite("signal");
        model.autoFillingArray();
        mc.addModel("signal", model);
        mc.addView(panel);
        AddOnFactory factory = AddOnFactory.getInstance(mc);
        factory.addSimpleAddOn("signal");

        JFrame frame = new JFrame();
        frame.setSize(300,300);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
