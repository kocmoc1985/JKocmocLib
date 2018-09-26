/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Same as "SyncBasics1" but more smart.
 * Executes a thread then lauches the next one.
 * This solution implements "LinkedList"
 * @author KOCMOC
 */
public class SyncBasics1_1_2 {

    private LinkedList<Thread> threadList = new LinkedList<Thread>();
    private final Thread x = new Thread(new Runnable() {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("x1-" + i);
                sleep(500);
            }
            System.out.println("\n");
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
            Logger.getLogger(SyncBasics1_1_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void go() {
        threadList.add(x);
        threadList.add(x2);
        for (Iterator<Thread> it = threadList.iterator(); it.hasNext();) {
            Thread thread = it.next();
            thread.start();
            System.out.println("start");
            System.out.println("start");
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SyncBasics1_1_2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        SyncBasics1_1_2 basics1_4 = new SyncBasics1_1_2();
        basics1_4.go();
    }
}
