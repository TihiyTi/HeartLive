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

        add(viewCorrelation);
    }
    private void initListeners(){
        viewCorrelation.addActionListener(e-> mc.correlation());

    }

    private JButton viewCorrelation;
}
