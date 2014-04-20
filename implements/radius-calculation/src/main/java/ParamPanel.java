import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

public class ParamPanel extends JPanel{

    private final JTextField mainSizeA = new JTextField();
    private final JTextField mainSizeB = new JTextField();
    private final JTextField mainXShift = new JTextField();
    private final JTextField mainYShift = new JTextField();
    private final JTextField mainRSphere = new JTextField();
    private final JTextField mainH = new JTextField();
    private final JTextField firstSizeA = new JTextField();
    private final JTextField firstSizeB = new JTextField();
    private final JTextField ro1 = new JTextField();

    public ParamPanel() {
        setBorder(BorderFactory.createLineBorder(Color.RED));
        setLayout(new GridBagLayout());
        initComponent();
    }

    private void initComponent() {
        mainSizeA.setPreferredSize(new Dimension(50, 0));

        GridBagConstraints constraints = new GridBagConstraints(0,0, 1,1, 0,0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,5,0,5), 0,0);
        constraints.gridx = 0;
        add(new Label("Size A(main)"), constraints);
        constraints.gridx = 1;
        add(mainSizeA, constraints);
        constraints.gridy = 2;
        constraints.gridx = 0;
        add(new Label("Size B(main)"), constraints);
        constraints.gridx = 1;
        add(mainSizeB, constraints);
        constraints.gridy = 3;
        constraints.gridx = 0;
        add(new Label("Shift X(main)"), constraints);
        constraints.gridx = 1;
        add(mainXShift, constraints);
        constraints.gridy = 4;
        constraints.gridx = 0;
        add(new Label("Shift Y(main)"), constraints);
        constraints.gridx = 1;
        add(mainYShift, constraints);
        constraints.gridy = 5;
        constraints.gridx = 0;
        add(new Label("R sphere(main)"), constraints);
        constraints.gridx = 1;
        add(mainRSphere, constraints);
        constraints.gridy = 6;
        constraints.gridx = 0;
        add(new Label("H (main)"), constraints);
        constraints.gridx = 1;
        add(mainH, constraints);
        constraints.gridy = 7;
        constraints.gridx = 0;
        add(new Label("Size A(FL)"), constraints);
        constraints.gridx = 1;
        add(firstSizeA, constraints);
        constraints.gridy = 8;
        constraints.gridx = 0;
        add(new Label("Size B(FL)"), constraints);
        constraints.gridx = 1;
        add(firstSizeB, constraints);
        constraints.gridy = 9;
        constraints.gridx = 0;
        add(new Label("Ro1 equval"), constraints);
        constraints.gridx = 1;
        add(ro1, constraints);


    }

    protected double[] getParam(){
        return new double[]{Double.valueOf(mainSizeA.getText()), Double.valueOf(mainSizeB.getText()),
                Double.valueOf(mainXShift.getText()), Double.valueOf(mainYShift.getText()),
                Double.valueOf(mainRSphere.getText()), Double.valueOf(mainH.getText()),
                Double.valueOf(firstSizeA.getText()), Double.valueOf(firstSizeB.getText()),
                Double.valueOf(ro1.getText())};
    }
    protected void setParam(double[] paramArray){
        mainSizeA.setText(String.valueOf(paramArray[0]));
        mainSizeB.setText(String.valueOf(paramArray[1]));
        mainXShift.setText(String.valueOf(paramArray[2]));
        mainYShift.setText(String.valueOf(paramArray[3]));
        mainRSphere.setText(String.valueOf(paramArray[4]));
        mainH.setText(String.valueOf(paramArray[5]));
        firstSizeA.setText(String.valueOf(paramArray[6]));
        firstSizeB.setText(String.valueOf(paramArray[7]));
        ro1.setText(String.valueOf(paramArray[8]));
    }
}
