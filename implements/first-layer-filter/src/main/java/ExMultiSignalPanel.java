import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.view.MultiSignalPanel2;
import com.tihiy.reonew.utils.DxMatrixFinder;
import org.ejml.simple.SimpleMatrix;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExMultiSignalPanel extends MultiSignalPanel2{
    public ExMultiSignalPanel(AbstractController mc, Map<String, List<Double>> map) {
        super(mc, map);
        reInit();
    }

    public ExMultiSignalPanel(AbstractController mc, Map<String, List<Double>> map, Map<String, Integer> mapPosition) {
        super(mc, map, mapPosition);
        reInit();
    }

    private void reInit() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                SphereModelParam param1 = new SphereModelParam(3.51,1.35, 0.04,0.02, 0.051,0.03, 0,0.047);
//                SphereModelSimple model1 = new SphereModelSimple(param1);
//                ReoProcessor proc1 = new ReoProcessor(model1);
//                OneLayerModelSimple oneModel = new OneLayerModelSimple(0.06, 0.03);
//
//                List<Double> list1 = signalMap.get("precard_1");
//                List<Double> list2 = signalMap.get("precard_2");
//                List<Double> list3 = signalMap.get("precard_3");
//                List<Double> list4 = signalMap.get("precard_4");
//                List<Double> list5 = signalMap.get("precard_5");
//                List<Double> first = signalMap.get("first");
//                int x = e.getX();
//                double pre1 = list1.get(x);
//                double pre2 = list2.get(x);
//                double pre3 = list3.get(x);
//                double pre4 = list4.get(x);
//                double pre5 = list5.get(x);
//                double fir = oneModel.getDeltaRo(first.get(x));
//                System.out.println("  pre1=" + pre1+"pre2=" + pre2+"pre3=" + pre3+"pre4=" + pre4+"pre5=" + pre5);
//                System.out.println("fir=" + fir);
//                System.out.println(proc1.getDeltaRadius(pre1/1000, -fir));


                if (mouseClik) {
                    firstPoint = (int) e.getPoint().getX();
                    mouseClik = false;
                } else {
                    secondPoint = (int) e.getPoint().getX();
                    System.out.println("F=" + firstPoint + " S=" + secondPoint);
                    List<Double> list1 = signalMap.get("clear_1");
                    List<Double> list2 = signalMap.get("clear_2");
                    List<Double> list3 = signalMap.get("clear_3");
                    List<Double> list4 = signalMap.get("clear_4");
                    List<Double> list5 = signalMap.get("clear_5");
                    int numOfStep = 6;
                    int[] indexes = new int[numOfStep];
                    List<Integer> counts = Arrays.asList(1, 2, 3, 4, 5);
                    SimpleMatrix dz = new SimpleMatrix(5, 5);
                    int j = 0;
                    for (int i = 0; i < numOfStep; i++) {
                        indexes[i] = firstPoint + i * (secondPoint - firstPoint) / numOfStep;
                    }
                    for (Integer i : counts) {
                        dz.setRow(j, 0, (int) ((list1.get(indexes[i]) - list1.get(indexes[0])) * 1000),
                                (int) ((list2.get(indexes[i]) - list2.get(indexes[0])) * 1000),
                                (int) ((list3.get(indexes[i]) - list3.get(indexes[0])) * 1000),
                                (int) ((list4.get(indexes[i]) - list4.get(indexes[0])) * 1000),
                                (int) ((list5.get(indexes[i]) - list5.get(indexes[0])) * 1000)
                        );
                        j++;
                    }
//                    System.out.println("Dz matrix");
//                    dz.print(2,2);
//
                    DxMatrixFinder finder = new DxMatrixFinder();
                    if(e.getButton()==MouseEvent.BUTTON3){
                        finder.setDz(dz);
                    }else{
                        finder.getDx(dz);
                    }

                    System.out.println();
                    System.out.println();
                    mouseClik = true;
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

}
