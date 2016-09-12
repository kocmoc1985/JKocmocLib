/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OneTargetClasses;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOCMOC
 */
public class LastActivityMeter implements Runnable {

    private long last_activity;
    private long recieve_time_out;

    public void last_activity(long last_activity) {
        this.last_activity = last_activity;

    }

    private void check() {
        if (last_activity == 0) { // very important!
            System.out.println("download complete");
            return;
        }
        long diff = last_activity - System.currentTimeMillis();
        System.out.println("diff = " + diff);
//        if (Math.abs(diff) > 2000) {
//            
//        }
    }

    @Override
    public void run() {
        while (true) {
            check();
            wait_(1000);
        }
    }

    private void wait_(int millis) {
        synchronized (this) {
            try {
                wait(millis);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientProtocolEx.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
