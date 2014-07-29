import javax.swing.*;
import java.awt.*;

public class FirstLayerFilter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }

            private void createAndShowGUI() {
                ThisController mc = new ThisController();
                GroupSignalPanel signalPanel = new GroupSignalPanel(mc);
                ThisControlPanel controlPanel = new ThisControlPanel(mc);

                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                panel.setPreferredSize(new Dimension(800,800));
                panel.setLayout(new BorderLayout());
                panel.add(signalPanel, BorderLayout.CENTER);
                panel.add(controlPanel, BorderLayout.EAST);

                JFrame frame = new JFrame("FirstLayerFilter");
                frame.setSize(panel.getPreferredSize());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(panel);
                frame.setVisible(true);
            }
        });
    }

}
