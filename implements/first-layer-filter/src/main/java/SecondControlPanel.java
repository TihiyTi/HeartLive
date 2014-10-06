import javax.swing.*;

public class SecondControlPanel extends JPanel {
    private final ThisController mc;

    public SecondControlPanel(ThisController mc){
        this.mc =  mc;
        initComponent();
        initListeners();
    }

    private void initComponent(){
        viewCorrelation = new JButton("ViewCorrelation");
        crossCorrelation = new JButton("CrossCorrelation");
        add(viewCorrelation);
        add(crossCorrelation);
    }
    private void initListeners(){
        viewCorrelation.addActionListener(e-> mc.correlation());
        crossCorrelation.addActionListener(e-> mc.crossCorrelation());
    }

    private JButton viewCorrelation;
    private JButton crossCorrelation;
}
