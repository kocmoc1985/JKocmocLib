/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unimportant;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOCMOC
 */
public class TestRdpRunScriptMCRemoteRelated {

    public static void main(String[] args) {
        boolean rdp_activated = portOccupied(3389);
        if (rdp_activated == true) {
            try {
                run_application_with_associated_application(new File("properties/enable.bat"));
            } catch (Exception ex) {
                Logger.getLogger(TestRdpRunScriptMCRemoteRelated.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public static void run_application_with_associated_application(File file) throws IOException {
        Desktop.getDesktop().open(file);
    }
    
    private static boolean run_application_exe(String path) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestRdpRunScriptMCRemoteRelated.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Process p = Runtime.getRuntime().exec(path);
            return true;
        } catch (IOException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    public static boolean portOccupied(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            ss.close();
            return false;
        } catch (Exception ex) {
            return true;
        }
    }

    public static void run_application_exe_or_jar(String application_to_run_name, String path) throws IOException {
        String[] commands = new String[3];
        if (application_to_run_name.contains(".jar")) {
            commands[0] = "java";
            commands[1] = "-jar";
            commands[2] = application_to_run_name; //OBS! pay attention here
        } else {
            commands[0] = path + "/" + application_to_run_name; // and here!
            commands[1] = "";
            commands[2] = "";
        }
        ProcessBuilder builder = new ProcessBuilder(commands);
        builder.directory(new File(path));
        builder.start();
    }
}
