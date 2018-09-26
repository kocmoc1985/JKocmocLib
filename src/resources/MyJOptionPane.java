/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Administrator
 */
public class MyJOptionPane {

    /**
     * Very good
     * @param box
     * @param msg
     * @return 
     */
    public static boolean chooseFromComboBoxDialog(JComboBox box, String msg) {
        return JOptionPane.showConfirmDialog(null, box, msg, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
    
    public static boolean confirm() {
        return JOptionPane.showConfirmDialog(null, "Confirm action?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
   

    public static void main(String[] args) {
       confirm();
    }

    public static void show_joption_pane_message_dialog_some_time_and_close(String message, String error) {
        KeyPressRobot.go();
        JOptionPane.showMessageDialog(null, message + " " + error, "MCMillsBrowser", JOptionPane.INFORMATION_MESSAGE);
    }

    static class KeyPressRobot {

        private static Timer t = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressAway();
            }
        });

        public static void go() {
            t.setRepeats(false);
            t.start();
        }

        private static void pressAway() {
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException ex) {
                Logger.getLogger(MyJOptionPane.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < 1; i++) {
                robot.keyPress(KeyEvent.VK_ESCAPE);
                wait_(50);
                robot.keyRelease(KeyEvent.VK_ESCAPE);
                wait_(1000);
            }
        }

        private static void wait_(int i) {
            try {
                Thread.sleep(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyJOptionPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
