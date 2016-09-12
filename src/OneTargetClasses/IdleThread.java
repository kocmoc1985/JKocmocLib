package OneTargetClasses;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class IdleThread implements Runnable {

    Properties p;
    boolean monitor;
    long inactive_time;

    public IdleThread(Properties props) {
         p = props;
        inactive_time = Long.parseLong(p.getProperty("idle_time"));
        monitor = Long.parseLong(p.getProperty("idle_time")) != -1;
        System.out.println("" + monitor);
       
    }

    public void run() {
        while (monitor) {
            try {
                idle_time_user();
            } catch (InterruptedException ex) {
                Logger.getLogger(IdleThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *  A very useful method to be able to evaluate if the user is idle
     * @throws InterruptedException
     */
    public void idle_time_user() throws InterruptedException {
        boolean disconnected = false;
        long idleTime = 0;
        long start = System.currentTimeMillis();
        Point currLocation = MouseInfo.getPointerInfo().getLocation();
        while (true) {
            System.out.println("monitoring");
            Thread.sleep(1000);
            Point newLocation = MouseInfo.getPointerInfo().getLocation();
            if (newLocation.equals(currLocation)) {
                //not moved
                idleTime = System.currentTimeMillis() - start;
                if (idleTime > minutes_to_milliseconds_converter(inactive_time) && disconnected == false) {
                    System.out.println("Disconnecting session");
                    disconnect_current_session();                   
                    disconnected = true;
                }
            } else {
                disconnected = false;
                System.out.printf("Idle time was: %s ms", idleTime);
                idleTime = 0;
                start = System.currentTimeMillis();
                break;
            }
            currLocation = newLocation;
        }
    }

    /**
     *
     * @param minutes
     * @return
     */
    public static long minutes_to_milliseconds_converter(long minutes) {
        return minutes * 60000;
    }

    /**
     * Disconnects the current logged on session
     * @param args
     */
    public static void disconnect_current_session() {
        String[] commands2 = {"c:/windows/system32/tsdiscon.exe"};// 
        try {
            Process pr = Runtime.getRuntime().exec(commands2);
        } catch (IOException ex) {
        }
    }

    public static void main(String[] args) {
        Properties pr = new Properties();
        try {
            pr.load(new FileInputStream("autostarterfed.properties"));
            pr.list(System.out);
        } catch (IOException ex) {
          
        }
        new IdleThread(pr).run();
    }
}
