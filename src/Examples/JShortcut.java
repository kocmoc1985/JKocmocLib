/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import java.io.File;
import net.jimmc.jshortcut.JShellLink;

/**
 *
 * @author MCREMOTE
 */
public class JShortcut {

    public static void main(String[] args) {
//        boolean isSucc = createLnk("E:\\Java\\workspace\\swing\\", "ScreenShot1.0.exe",
//                "E:\\Java\\workspace\\swing\\sshot\\ScreenShot1.0.exe");
        String path = new File("pixie.exe").getAbsolutePath();
        System.out.println("Path: " + path);
        boolean isSucc = createLnk("", "pixie.exe", path);
        //
        System.out.println(isSucc);
    }

    public static boolean createLnk(String savePath, String appName, String exePath) {
        try {
            File f = new File(exePath);
            File f2 = new File(savePath);
            if (!f.exists() ) { // || !f2.exists()
                return false;
            }
            JShellLink link = new JShellLink();
            // storage path
            link.setFolder(JShellLink.getDirectory("desktop"));
//            link.setFolder(savePath);
            // Shortcut name
            link.setName(appName);
            // pointed exe
            link.setPath(exePath);
            link.save();
            return true;
        } catch (Throwable e) {
            // It is the changed jar application, throw all
            e.printStackTrace();
        }
        return false;
    }
}
