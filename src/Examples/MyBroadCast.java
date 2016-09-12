/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOCMOC
 */
public class MyBroadCast implements Runnable {

    private String host;

    public MyBroadCast(String host) {
        this.host = host;
        startThread();
    }

    private void startThread() {
        Thread x = new Thread(this);
        x.start();
    }

    @Override
    public void run() {
        try {
            ping3(host);
        } catch (IOException ex) {
            Logger.getLogger(MyBroadCast.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyBroadCast.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean ping3(String host) throws IOException, InterruptedException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows ? "-n" : "-c", "1", host);
        Process proc = processBuilder.start();

        int returnVal = proc.waitFor();
        boolean reachable = returnVal == 0;

        if (reachable) {
            System.out.println("host: " + host);
        }

        return returnVal == 0;
    }

    public static void main(String[] args) {
        String host_prefix = "10.87.0.";

        for (int i = 1; i < 255; i++) {
            MyBroadCast bc = new MyBroadCast(host_prefix + i);
        }
    }
}
