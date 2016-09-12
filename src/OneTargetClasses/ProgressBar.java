package OneTargetClasses;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author KOCMOC
 */
public class ProgressBar {

    private static JPanel jpanel = new JPanel();
    private static JProgressBar pbar;
    private static int min = 1;
    private static int max = 100;
    private static JFrame frame = new JFrame("Checking for updates please wait!");
    private static boolean first_time = true;
    private static boolean update_finished = false;
    private static boolean update_bar_called_at_least_one_time = false;

    static {
        init();
    }

   public static void hideIt(){
       frame.setVisible(false);
   }

    private static void init() {
        frame.setIconImage(new ImageIcon("images/client.png").getImage());
        pbar = new JProgressBar();
        pbar.setMinimum(min);
        pbar.setMaximum(max);
        pbar.setPreferredSize(new Dimension(400, 50));
        jpanel.add(pbar);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        frame.setContentPane(jpanel);
        frame.pack();
        frame.setVisible(false);
        frame.setAlwaysOnTop(true);
    }

    private static void reset() {
        pbar.setMinimum(min);
        pbar.setMaximum(max);
        frame.setVisible(true);
    }

    public static void updateBar(int newValue, int maxValue) {
        update_bar_called_at_least_one_time = true;
        max = maxValue;

        if (max == newValue) {
            pbar.setValue(newValue);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
            }
            update_finished = true;
            first_time = true;
            frame.setVisible(false);
            pbar.setValue(min);
            return;
        }

        if (first_time) {
            update_finished = false;
            reset();
            first_time = false;
        }
        pbar.setValue(newValue);
    }
    
    public static boolean getUpdateBarCalled(){
        return update_bar_called_at_least_one_time;
    }

    public static boolean getUpdateFinished() {
        return update_finished;
    }
}