/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOCMOC
 */
public class Testt {

    public static void main(String[] args) {
        run_with_cmd("mstsc", "/v:" + "10.87.0.175" + ":3389");
    }

    public static void run_with_cmd(String cmd_application, String arg) {
        String[] commands = {"cmd", "/c", "start", "\"" + cmd_application + "\"", cmd_application, arg};
        ProcessBuilder builder = new ProcessBuilder(commands);
        try {
            builder.start();
        } catch (IOException ex) {
            Logger.getLogger(Testt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
