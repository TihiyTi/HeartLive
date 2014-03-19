import com.tihiy.rclint.control.SimpleController;
import com.tihiy.rclint.models.SignalDynamicModel;
import com.tihiy.rclint.models.SignalModelLite;
import com.tihiy.rclint.view.SignalPanelLite;

import javax.swing.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SignalDynamicViewerLite {
    public static int i = 0;
    public static void main(String[] args) {
        SimpleController mc = new SimpleController();
        SignalPanelLite panel = new SignalPanelLite("signal");
        SignalDynamicModel<Double> model = new SignalDynamicModel<>("signal");
        mc.addModel("signal", model);
        mc.addView(panel);
        panel.initFeedback(mc);
        final BlockingQueue<Double> queue = new LinkedBlockingQueue<>();
        model.setBufferQueue(queue);
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                queue.add(i%100.);
                i++;
            }
        }, 0L, 5L, TimeUnit.MILLISECONDS);
        JFrame frame = new JFrame();
        frame.setSize(300,300);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
