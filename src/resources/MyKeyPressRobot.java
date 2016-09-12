/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author KOCMOC
 */
public class MyKeyPressRobot {

    public static void main(String[] args) {
        several_keys_pressed_event();
//        System.out.println("" + get_key_code_of_a_string("\u001bOP"));
    }
    
    /**
     * Very important
     * This gets the same as "keycode" 
     * so for "1" it returns 49 for F1(\u001bOP) returns 28476
     * @param str
     * @return 
     */
    public static int get_key_code_of_a_string(String str){
        return str.hashCode();
    }

    public static void several_keys_pressed_event() {
        KeyListener keylist = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.print("");
            }

            @Override
            public void keyPressed(KeyEvent e) { // SHIFT + ALT + CTRL
                int modifier = e.getModifiers();
                int key = e.getKeyCode();
                System.out.println("modifier " + modifier);
                System.out.println("key " + key);
                //if "ctrl + c" is pressed
                if (modifier == 2 && key == 67) { //2 = ctrl, 67 = c
                    System.out.println("aa");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.print("");
            }
        };

        JFrame frame = new JFrame("Test");
        frame.addKeyListener(keylist);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }

    /**
     * This is a very useful method to be able to press away error messages.
     * Error messages ussualy halts the further processing of program untill the
     * error msg is pressed away.
     *
     * @Tags [key press simulation,key simulation,keysimulation,erromessage,
     * errormessage windows, reset errormessage,reset error message,reset error
     * msg, take away error msg]
     * @param nr_attempts
     * @throws AWTException
     */
    public static void press_away_errormessages(int nr_attempts) throws AWTException {
        Robot robot = new Robot();
        for (int i = 0; i < nr_attempts; i++) {
            robot.keyPress(KeyEvent.VK_ESCAPE);
            wait_(50);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            wait_(1000);
        }
    }

    public static void press_away_errormessages_with_window_focus(int nr_attempts) throws AWTException {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(MyKeyPressRobot.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < nr_attempts; i++) {
            //==================================================================
            robot.keyPress(KeyEvent.VK_ALT);
            //==
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            //==
            robot.keyRelease(KeyEvent.VK_ALT);
            //==================================================================

            robot.keyPress(KeyEvent.VK_ESCAPE);
            wait_(50);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            wait_(50);
        }
    }

    /**
     *
     * @Tags [simulate key press, key simulation, keysimulation, robot]
     */
    public static void key_press_simulation() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(10); //KeyEvent.VK_A // KeyCode 10 = "Enter btn"
        robot.keyRelease(10);
    }

    static class KeyPressExamle implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyInternals.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                key_press_simulation();
            } catch (AWTException ex) {
                Logger.getLogger(MyInternals.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * This method is done to loose focus from for example an icon on the
     * desktop
     *
     * @Tags [lose focus, key press, keypress, loosefocus]
     * @throws AWTException
     */
    public static void looseFocus() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(MyInternals.class.getName()).log(Level.SEVERE, null, ex);
        }
        wait_(3000);
        // This is done to loose focus ========      
        robot.keyPress(KeyEvent.VK_TAB);
        wait_(10);
        robot.keyRelease(KeyEvent.VK_TAB);
        wait_(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        wait_(10);
        robot.keyRelease(KeyEvent.VK_ENTER);
        wait_(50);
        robot.keyPress(KeyEvent.VK_ESCAPE);
        wait_(10);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        //======================================
    }

    private static void wait_(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyInternals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
