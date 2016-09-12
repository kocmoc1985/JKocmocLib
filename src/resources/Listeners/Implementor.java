/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.Listeners;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author KOCMOC
 */
public class Implementor extends JPanel implements MouseListener {

    private ArrayList<MyColorChangedListener> my_color_changed_listener_list = new ArrayList<MyColorChangedListener>();
    private ArrayList<MySignalsRecievedListener> my_signals_recieved_listener_list = new ArrayList<MySignalsRecievedListener>();
    private Color initialColor = Color.yellow;
    private Timer timer;

    public Implementor() {
        this.setSize(50, 50);
        this.setBackground(initialColor);
        this.setLayout(new GridLayout(1, 1));
        this.addMouseListener(this);
        startReadingSignals();
        timer.start();
    }

    public void addMyColorChangeListener(MyColorChangedListener lto) {
        my_color_changed_listener_list.add(lto);
    }

    public void addMySignalsRecievedListener(MySignalsRecievedListener msrl) {
        my_signals_recieved_listener_list.add(msrl);
    }

    private void startReadingSignals() {
         timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Properties p = new Properties();
                p.put("torque", random());
                p.put("speed", random());
                //OBS!
                for (MySignalsRecievedListener srl : my_signals_recieved_listener_list) {
                    srl.receiveSignals(p);
                }
                //
            }
        });
    }

    public static String random() {
        int x = (int) ((Math.random() * 800) + 21);
        return "" + x;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() instanceof JPanel) {
            this.setBackground(Color.BLACK);
            JPanel panel = (JPanel) me.getSource();
            if (panel.getBackground() != initialColor) {
                //
                //OBS!
                for (MyColorChangedListener myComponentListener : my_color_changed_listener_list) {
                    myComponentListener.colorChangedEvent(panel.getBackground());
                }
                //
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
