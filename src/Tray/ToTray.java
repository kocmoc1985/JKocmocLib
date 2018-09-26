/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tray;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Administrator
 */
public class ToTray {

    Image img = Toolkit.getDefaultToolkit().getImage("help.png");
    PopupMenu popup;
    MenuItem exit;
    MenuItem open;
    SystemTray tray;
    TrayIcon trayIcon;
    
    /**
     *
     */
     public void toTray() {
        if (SystemTray.isSupported()) {

            tray = SystemTray.getSystemTray();
            img = Toolkit.getDefaultToolkit().getImage("help.png");


            ActionListener actionListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == exit) {
                        System.exit(0);
                    } else if (e.getSource() == open) {
                    //    makeVisible();
                    }
                  //  makeVisible();
                }
            };

            popup = new PopupMenu();
            exit = new MenuItem("EXIT");
            open = new MenuItem("OPEN");

            exit.addActionListener(actionListener);
            open.addActionListener(actionListener);

            popup.add(exit);
            popup.add(open);

            trayIcon = new TrayIcon(img, "MCMills", popup);

            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);


            try {
                tray.add(trayIcon);

            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }

        } else {
            //  System Tray is not supported
        }
    }

}
