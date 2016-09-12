
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class shows the usage of a synchronized method, namely
 * it shows that if the method is synchronized it cannot be called 
 * by several Threads simulteneously so it prevents "distortion".
 * So the method is beeing blocked while its executed by a method!
 * @author KOCMOC
 */
public class SyncBasics1_2 {

    private int TEST_VAL = 0;
    private final Thread x = new Thread(new Runnable() {

        @Override
        public void run() {
            x.setName("Thread X");
            x.setPriority(Thread.MAX_PRIORITY);
            for (int i = 0; i < 10; i++) {
                synchMethod();
                sleep(500);
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
                sleep(500);
            }
        }
    });

    
     
     private  void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(SyncBasics1_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Note the difference when the method is synchronized and when 
      * not.
      * When synchronized the method cannot be executed simulteneously by
      * both Threads!!!!!
     */
    public synchronized void synchMethod() {
        TEST_VAL++;
        System.out.println("VAL = " + TEST_VAL + "  executed by " + Thread.currentThread().getName());
    }

    public void go() {
        x.start();
        x2.start();
    }

    public static void main(String[] args) {
        SyncBasics1_2 thr = new SyncBasics1_2();
        thr.go();
    }
}
