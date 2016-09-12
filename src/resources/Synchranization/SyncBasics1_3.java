/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Synchranization;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class shows whow to call a function
 * "i tur ordning"
 * @author KOCMOC
 */
public class SyncBasics1_3 {

    private Thread prevThread;
    private int TEST_VAL = 0;
    private final Thread x = new Thread(new Runnable() {

        @Override
        public void run() {
            x.setName("Thread X");
            for (int i = 0; i < 10; i++) {

                synchMethod();
                synchronized (x) {
                    try {
                        x.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SyncBasics1_3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                sleep(500);

            }
            System.out.println("x1 exited");

            synchronized (x2) {
                x2.notify();
            }
        }
    });
    ;
    
     private final Thread x2 = new Thread(new Runnable() {

        @Override
        public void run() {
            x2.setName("Thread X2");
            for (int i = 0; i < 10; i++) {
                synchMethod();
                synchronized (x2) {
                    try {
                        x2.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SyncBasics1_3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                sleep(500);
            }
            System.out.println("x2 exited");

            synchronized (x) {
                x.notify();
            }
        }
    });
    ;
     
     

    /**
     * Note the difference when the method is synchronized and when not.
     * When synchronized the method cannot be executed simulteneously by
     * both Threads!!!!!
     */
    public synchronized void synchMethod() {
        TEST_VAL++;
        System.out.println("VAL = " + TEST_VAL + "  executed by " + Thread.currentThread().getName());
        if (prevThread != null && prevThread != Thread.currentThread()) {
            synchronized (prevThread) {
                prevThread.notify();
            }
        }
        prevThread = Thread.currentThread();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(SyncBasics1_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void go() {
        x.start();
        x2.start();

    }

    public static void main(String[] args) {
        SyncBasics1_3 thr = new SyncBasics1_3();
        thr.go();
    }
}
