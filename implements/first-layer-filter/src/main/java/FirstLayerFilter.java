import model.ConformityModel;

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

                ConformityPanel conformityPanel = new ConformityPanel(mc);
                ConformityModel conformityModel = new ConformityModel(ThisController.CONFIRM_VIEW);
                SecondControlPanel secondControlPanel =  new SecondControlPanel(mc);
                mc.addModel(ThisController.CONFIRM_VIEW, conformityModel);
                mc.addView(conformityPanel);

                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                panel.setPreferredSize(new Dimension(800,800));
                panel.setLayout(new BorderLayout());
                panel.add(signalPanel, BorderLayout.CENTER);
                panel.add(controlPanel, BorderLayout.EAST);

                JPanel secondPanel = new JPanel();
                secondPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                secondPanel.setLayout(new BorderLayout());
                secondPanel.add(secondControlPanel,BorderLayout.EAST);
                secondPanel.add(conformityPanel,BorderLayout.WEST);

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Signals", panel);
                tabbedPane.addTab("Fringe moving", secondPanel);

                JFrame frame = new JFrame("FirstLayerFilter");
                frame.setSize(panel.getPreferredSize());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(tabbedPane);
//                frame.getContentPane().add(panel);
                frame.setVisible(true);
            }
        });
    }

}
