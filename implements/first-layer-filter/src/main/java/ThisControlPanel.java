import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.14
 * Time: 14:16
 */
public class ThisControlPanel extends AbstractViewPanel {
    private final ThisController mc;

    public ThisControlPanel(ThisController mc) {
        this.mc = mc;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.RED));
        initComponent();
        initListeners();
    }

    private void initComponent() {
        param = new TabParamPanel();
        chooseSignal = new JButton("Choose Signal");
        clearSignal = new JButton(" Clear Signal");

        GridBagConstraints constraints = new GridBagConstraints(0,0, 1,1, 0,0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0,0);
        add(chooseSignal,constraints);
        constraints.gridy = 1;
        add(clearSignal, constraints);
        constraints.gridy = 2;
        add(param, constraints);

    }

    private void initListeners(){
        chooseSignal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File sourceFile;

                boolean autoChooseFile = true;
                if(autoChooseFile){
                    sourceFile = new File("C:\\Users\\Alex\\Documents\\My Box Files\\Asp\\Experiment\\20140724\\2rad.txt");
                }else{
                    sourceFile = chooseFile();
                }
                mc.addSignals(sourceFile);
            }
        });
        clearSignal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.clearSignal(param.getParam());
            }
        });
    }

    private File chooseFile(){
        JFileChooser fileChooser =  new JFileChooser();
        fileChooser.changeToParentDirectory();
        String userName = System.getProperties().getProperty("user.name");
        fileChooser.setCurrentDirectory(new File("C:\\Users\\" + userName + "\\Documents\\My Box Files\\Asp"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showDialog(new JFrame(), "Choose signal");
        return fileChooser.getSelectedFile();
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }

    private TabParamPanel param;
    private JButton chooseSignal;
    private JButton clearSignal;
}
