package com.tihiy.rclint.view;

import com.tihiy.rclint.mvcAbstract.AbstractController;
import com.tihiy.rclint.mvcAbstract.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.14
 * Time: 20:43
 */
public class MultiSignalPanel2 extends AbstractViewPanel{
    private JScrollPane scrollPane;
    private AbstractController mc;
    protected Map<String, List<Double>> signalMap;
    private Map<String, SignalSetting> settingsMap = new HashMap<>();
    private int numOfBox = 1;
    private int scale = 1;
    private int mouseX = 0;

    protected boolean mouseClik = true;
    protected boolean nPressed = false;
    protected int firstPoint = 0;
    protected int secondPoint = 0;

    private int currentHeight;

    public MultiSignalPanel2(AbstractController mc, Map<String, List<Double>> map) {
        this.mc = mc;
        this.signalMap = map;
        initComponent();
    }
    public MultiSignalPanel2(AbstractController mc, Map<String, List<Double>> map, Map<String, Integer> mapPosition){
        this.mc = mc;
        this.signalMap = map;
        currentHeight = getHeight();
        initComponent();
        numOfBox = Collections.max(mapPosition.values());
        for(String key: signalMap.keySet()){
            settingsMap.put(key, new SignalSetting(map.get(key), mapPosition.get(key)));
        }
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        super.paintComponent(g);
        if(currentHeight!=getHeight()){
            currentHeight = getHeight();
            for(String key: settingsMap.keySet()){
                settingsMap.get(key).reconfigResize();
            }
        }
        int numOfSignal = 1;
        for(String key: signalMap.keySet()){
            paintSignal(g, key);
            if(mouseX < signalMap.get(key).size()){
                Font font = new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 10);
                font = font.deriveFont((float)20);
                g.setFont(font);
                NumberFormat f = NumberFormat.getInstance();
                f.setMaximumFractionDigits(3);
                g.drawString(""+ key +": " + f.format(signalMap.get(key).get(mouseX)), mouseX + 50, getHeight() - 20*numOfSignal);
                numOfSignal++;

                g.drawString("" + mouseX, 200, 50);
                g.drawString("" + scrollPane.getViewport().getViewPosition().getX(), 200, 100);
            }
        }
    }

    private void paintSignal(Graphics g, String signalName) {
        g.setColor(Color.black);
        Color color = settingsMap.get(signalName).color;
        if(color != null){
            g.setColor(color);
        }
        List<Double> signal = signalMap.get(signalName);
        SignalSetting st = settingsMap.get(signalName);
        if(!signal.isEmpty()){
            for (int i = 0; i < signal.size() - 1; i++) {
//                int yPoint_1 = (int)(signal.get(i)*st.scaleY + st.translateY);
                int yPoint_1 = (int)((signal.get(i) - st.translateYNorm)*st.scaleY + st.translateY);
                int yPoint_2 = (int)((signal.get(i+1) - st.translateYNorm)*st.scaleY + st.translateY);
//                int yPoint_2 = (int)(signal.get(i+1)*st.scaleY + st.translateY);
                g.drawLine(i/scale,yPoint_1, (i+1)/scale,yPoint_2);
            }
        }
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if(signalMap.containsKey(evt.getPropertyName())){
            List<Double> list = (List)evt.getNewValue();
//            setPreferredSize(new Dimension(list.size(), getHeight()));
            signalMap.put(evt.getPropertyName(), list);
            settingsMap.get(evt.getPropertyName()).configSignal(list);
            repaint();
        }
    }

    private void initComponent(){
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = (int) e.getPoint().getX();
                repaint();
            }
        });


    }

    public void colorSignal(Map<String, Color> mapOfColor){
        for (String key: mapOfColor.keySet()){
            settingsMap.get(key).setColor(mapOfColor.get(key));
        }
    }

    public void setScrollPane(JScrollPane scrollPane){
        this.scrollPane=scrollPane;
    }


    class SignalSetting{
        Color color;
        double scaleX = 1;
        double scaleY = 1;
        double translateY = 0;
        double translateYNorm = 0;
        double max;
        double min;
        int position;
        SignalSetting(List<Double> signal,int position){
            this.position = position;
            if(!signal.isEmpty()){
                configSignal(signal);
            }
        }

        private void configSignal(List<Double> signal){
            max = Collections.max(signal);
            min = Collections.min(signal);
            scaleY = 1.* getHeight()/(numOfBox *(max - min));

            translateY = (position - 0.5)*(getHeight()/numOfBox);
            translateYNorm = (max +min)/2;
            //printAllSetting();
        }
        private void reconfigResize(){
            scaleY = 1.* getHeight()/(numOfBox *(max - min));
            translateY = (position - 0.5)*(getHeight()/numOfBox);
            translateYNorm = (max +min)/2;
//            translateY = (position - 0.5)*(getHeight()/numOfBox) - (max +min)/2;
            //printAllSetting();
        }
        private void printAllSetting(){
            System.out.println("Signal №" + position);
            System.out.println("max = " + max + " min = " + min);
            System.out.println("  ScaleY = "+scaleY+"  translateY = "+ translateY);
        }
        private void setColor(Color color){
            this.color = color;
        }
    }
}
