import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ThisControlPanel extends JPanel{
    private final ThisController mc;
    boolean autoChooseFile = true;

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
        calcMoveMatrix = new JButton("Calc Moving");
        filterFirstLayer =  new JButton("Filter First Layer");
        fullCalculation = new JButton("FullCalculation");
        getShortSignal = new JButton("Get Short Signal");
        unipolarFirst = new JButton("Unipolar First Layer");

        GridBagConstraints constraints = new GridBagConstraints(0,0, 1,1, 0,0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0,0);
        add(chooseSignal,constraints);
        constraints.gridy = 1;
        add(clearSignal, constraints);
        constraints.gridy = 2;
        add(param, constraints);
        constraints.gridy = 3;
        add(calcMoveMatrix, constraints);
        constraints.gridy = 4;
        add(filterFirstLayer, constraints);
        constraints.gridy = 6;
        add(fullCalculation, constraints);
        constraints.gridy = 7;
        add(getShortSignal, constraints);

        constraints.gridy = 8;
        add(unipolarFirst, constraints);

    }

    private void initListeners(){
        chooseSignal.addActionListener(e -> {
            File sourceFile;
            if(autoChooseFile){
                String userName = System.getProperties().getProperty("user.name");
                sourceFile = new File("C:\\Users\\" + userName + "\\Documents\\My Box Files\\Asp\\Experiment\\20140724\\21rad.txt");
            }else{
                sourceFile = chooseFile();
            }
//            mc.addSignals(sourceFile);
            mc.addSignals(sourceFile);
        });
        clearSignal.addActionListener(e -> mc.clearSignal(param.getParam()));
        calcMoveMatrix.addActionListener(e -> mc.calcMoveMatrix());
        filterFirstLayer.addActionListener(e -> {
            if(filterFirstLayer.getText().equals("Filter First Layer")){
                filterFirstLayer.setText("Unfilter First Layer");
            }else{
                filterFirstLayer.setText("Filter First Layer");
            }
            mc.filterFirstLayer();
        });
        fullCalculation.addActionListener(e -> mc.fullCacl(param.getParam()));
        getShortSignal.addActionListener(e -> mc.getShortSignal());
        unipolarFirst.addActionListener(e -> mc.unipolarFirst());
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


    private TabParamPanel param;
    private JButton chooseSignal;
    private JButton clearSignal;
    private JButton calcMoveMatrix;
    private JButton filterFirstLayer;

    private JButton fullCalculation;
    private JButton getShortSignal;

    private JButton unipolarFirst;
}
