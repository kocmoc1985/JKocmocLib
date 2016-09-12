/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Synchranization;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This class shows how the Thread x2 waits for x1 to complete
 * and than starts to run, the more smart solution can be found
 * in "SyncBasics1_1_2"
 * @author KOCMOC
 */
public class SyncBasics1 {
    
    private final Thread x = new Thread(new Runnable() {
        
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("x1-" + i);
                sleep(500);
            }
            synchronized (x) {
                x.notify();
            }
        }
    });
    ;
     private final Thread x2 = new Thread(new Runnable() {
        
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("x2-" + i);
                sleep(500);
            }
        }
    });
    
    ;
     
     private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(SyncBasics1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void go() {
        x.start();
        
        // makes that thread dies after finishing, and thefore cannot be synchronized
//        try {
//            x.join(); 
//        } catch (InterruptedException ex) {
//            Logger.getLogger(SyncBasics.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //==============================================
        synchronized (x) {
            try {
                x.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(SyncBasics1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //=============================================
        System.out.println("passed");
        x2.start();
    }
    
    public static void main(String[] args) {
        SyncBasics1 thr = new SyncBasics1();
        thr.go();
    }
}
