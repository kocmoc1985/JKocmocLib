/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author KOCMOC
 */
public class MyPasswordEncryption {

    public static void main(String[] args) {
        //
        byte[] clientPass = checkForPasswordFile("pass_client/pwp_c", true);
        //
        byte[] serverPass = checkForPasswordFile("pass_server/pwp_s", true);
        //
        showInformationMessage("PASSWORD VALID: " + checkPassword(clientPass, serverPass));
    }

    public static boolean checkPassword(byte[] passwordA, byte[] passwordB) {
        return Arrays.equals(passwordA, passwordB);
    }

    public static byte[] checkForPasswordFile(String pathAndFileName, boolean hidePassword) {
        //
        if (file_exists(new File(pathAndFileName))) {
            //
            byte[] pass = null;
            //
            try {
                pass = filetoByteArray(pathAndFileName);
            } catch (IOException ex) {
                Logger.getLogger(MyPasswordEncryption.class.getName()).log(Level.SEVERE, null, ex);
            }
            //
            if (pass != null && pass.length != 0) {
                return pass;
            } else {
                return setRDPCommPassword(pathAndFileName, hidePassword);
            }
            //
        } else {
            return setRDPCommPassword(pathAndFileName, hidePassword);
        }
        //
    }

    private static byte[] sha256_byte(String toHash) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(toHash.getBytes(StandardCharsets.UTF_8));
            return hash;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MyPasswordEncryption.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static byte[] setRDPCommPassword(String pathAndFileName, boolean hidePassword) {
        //
        showInformationMessage("The password is not set, please set the password");
        JPasswordField jpf = chooseFromPasswordField("Enter password", hidePassword);
        String pass = new String(jpf.getPassword());
        //
        if (pass.isEmpty() == false) {
            //
            byte[] buffer = sha256_byte(pass);
            //
            try {
                byteArrayToFile(pathAndFileName, buffer);
            } catch (IOException ex) {
                Logger.getLogger(MyPasswordEncryption.class.getName()).log(Level.SEVERE, null, ex);
            }
            //
            return sha256_byte(pass);
            //
        } else {
            showInformationMessage("Password provided is empty");
            System.exit(0);
        }
        //
        return null;
    }

    public static byte[] loadPassword(String pathAndFileName) {
        //
        try {
            return filetoByteArray(pathAndFileName);
        } catch (IOException ex) {
            Logger.getLogger(MyPasswordEncryption.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        //
    }
    
    //============================================================================

   
    private static void showInformationMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private static JPasswordField chooseFromPasswordField(String msg, boolean hideChars) {
        //
        JPasswordField jpf = new JPasswordField();
        //
        requestFocus(jpf);
        //
        if (hideChars == false) {
            jpf.setEchoChar((char) 0);
        }
        //
        boolean x = JOptionPane.showConfirmDialog(null, jpf, msg, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
        return jpf;
    }

    private static void requestFocus(final JComponent component) {
        Thread x = new Thread() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MyPasswordEncryption.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //
                component.requestFocus();
                //
            }
        };

        x.start();
    }

    private static void byteArrayToFile(String path, byte[] arr) throws FileNotFoundException, IOException {
        File f2 = new File(path);
        OutputStream out;
        out = new FileOutputStream(f2);
        out.write(arr);
    }
    
     private static byte[] filetoByteArray(String path) throws FileNotFoundException, IOException {
        byte[] content;
        FileInputStream p = new FileInputStream(path);
        content = new byte[p.available()];
        p.read(content);
        p.close();
        return content;
    }

    private static boolean file_exists(File f) {
        if (f == null) {
            return false;
        } else {
            if (f.exists() == false) {
                return false;
            } else {
                return true;
            }
        }
    }

}
