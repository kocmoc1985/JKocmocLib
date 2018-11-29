/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FocusTraversalPolicy;
import java.awt.Frame;
import java.awt.KeyboardFocusManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Locale;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Administrator
 */
public class MyInternals {

    /**
     * Also checks if it is 32 or 64bit.
     * @return 
     */
    public static String getJavaVersionAndBit() {
        return System.getProperty("java.version") + "/" + System.getProperty("sun.arch.data.model");
    }

    /**
     * Java version, JavaVersion, Version Java, java
     *
     * @return
     */
    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    public static Double getJavaVersion2() {
        String ver = System.getProperty("java.version");
        ver = ver.substring(0, 3);
        return Double.parseDouble(ver);
    }

    public static void main(String[] args) {
        System.out.println("" + getJavaVersionAndBit());
    }

    public static void shut_down_immediately() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("shutdown -s -t 0");
        System.exit(0);
    }

    public static String getOperatingSystem() {
        return System.getProperty("os.name");
    }

    public static String getUserName() {
        return System.getProperty("user.name");
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    /**
     * Shuts down the computer
     *
     * @tags shut_down_computer, shut down, shutdown,shut_down, turn of
     * computer,turn_off_computer, turn of
     */
    /**
     * Put computer into hibernation mode
     *
     * @tags sleep,hibernate,suspend
     * @throws IOException
     */
    public static void hibernate() throws IOException {
        Runtime.getRuntime().exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
    }

    /**
     * @tags: open browser, run browser
     */
    public static void open_default_browser() {
        if (Desktop.isDesktopSupported()) {
            try {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.mixcont.com"));
                } catch (URISyntaxException ex) {
                    Logger.getLogger(MyInternals.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(MyInternals.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This can be useful, the program is launched with assosiated app - with
     * assosiated i ment the program which is used to open another in windows
     *
     * @tags open file, run file, open_file
     * @param file
     * @throws IOException
     */
    public static void run_application_with_associated_application(File file) throws IOException {
        Desktop.getDesktop().open(file);
    }

    public static void openDirWithExploerer(String path) {
        if (path == null) {
            return;
        }
//        String[] arr = {"cmd", "/k", "start", "\"" + "runas" + "\"", "explorer", "\\\\" + host}; // this example is for shared drives
        String[] arr = {"cmd", "/k", "start", "\"" + "runas" + "\"", "explorer", path};
        runWithBuilder(arr);
    }

    public static void runWithBuilder(String[] commands) {
        ProcessBuilder builder = new ProcessBuilder(commands);
        try {
            builder.start();
        } catch (IOException ex) {
            Logger.getLogger(MyInternals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @tags open folder, open_folder, open dir
     * @param path
     */
    public static void open_dir(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(MyInternals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * A very useful method to be able to evaluate if the user is idle
     *
     * @throws InterruptedException
     */
    public static long idle_time_user() throws InterruptedException {
        long idleTime = 0;
        long start = System.currentTimeMillis();
        Point currLocation = MouseInfo.getPointerInfo().getLocation();
        while (true) {
            Thread.sleep(1000);
            Point newLocation = MouseInfo.getPointerInfo().getLocation();
            if (newLocation.equals(currLocation)) {
                //not moved
                idleTime = System.currentTimeMillis() - start;
            } else {
                System.out.printf("Idle time was: %s ms", idleTime);
//                idleTime = 0;
                start = System.currentTimeMillis();
                break;
            }
            currLocation = newLocation;
        }
        return idleTime;
    }

    /**
     *
     * @return
     */
    public static String[] getCountryCodes() {
        String[] arr1 = Locale.getISOCountries();
        for (int i = 0; i < arr1.length; i++) {
            System.out.println("" + arr1[i]);
        }
        return arr1;
    }

    /**
     *
     * @return
     */
    public static String[] getLanguaugeCodes() {
        String[] arr1 = Locale.getISOLanguages();
        for (int i = 0; i < arr1.length; i++) {
            System.out.println("" + arr1[i]);
        }
        return arr1;
    }

    /**
     * Untested but can be good.-
     *
     * @return
     */
    public static Component findPrevFocus() {

        Component c = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();

        Container root = c.getFocusCycleRootAncestor();

        FocusTraversalPolicy policy = root.getFocusTraversalPolicy();

        Component prevFocus = policy.getComponentBefore(root, c);
        if (prevFocus == null) {
            prevFocus = policy.getDefaultComponent(root);
        }
        return prevFocus;
    }

    /**
     * KeyPressExamle Monitors the program shutdown
     */
    public void shutdownHook(Object o) {
//        final XYGraph xy = (XYGraph) o;
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
//                xy.DrawGraph(); // Do something on shutdown
            }
        });
    }

    /**
     * Thus there is no double_mouse_event this method could be very useful
     *
     * @param first_click
     * @param second_click
     * @return
     */
    public static boolean double_click_mouse_event_helper(long first_click, long second_click) {

        if (System.currentTimeMillis() - first_click > Math.abs(300)) {
            first_click = 0;
        }

        if (first_click == 0) {
            first_click = System.currentTimeMillis();
            System.out.println("first click" + System.currentTimeMillis());
        } else if (second_click == 0) {
            second_click = System.currentTimeMillis();
            System.out.println("second click" + System.currentTimeMillis());
            System.out.println("Second - First = " + (second_click - first_click));

            if (second_click - first_click < 150) {
                // To do code
                return true;
            }
            first_click = 0;
            second_click = 0;
        }
        return false;
    }

    /**
     * get_line_number Very good for debugging
     *
     * @return
     */
    public int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    /**
     * Finds the user name logged in into the session
     *
     * @return
     */
    public static String getLoggedInUserName() {
        return System.getProperties().getProperty("user.name");
    }

    /**
     *
     * @return
     */
    public static String getDirectoryFromWhichTheJavaAppRuns() {
        return System.getProperties().getProperty("user.dir");
    }

    /**
     * @tags get class name, getClassName, className
     * @param obj
     * @return
     */
    public static String getClassName(Object obj) {
        return obj.getClass().getName();
    }

    /**
     * Very good
     *
     * @param depth
     * @return
     */
    public static String getMethodName(int depth) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[ste.length - 1 - depth].getMethodName();
    }

    public static void cursorSetWaitCursor(boolean yesno) {
        Cursor defCursor = Cursor.getDefaultCursor();
        Cursor waitCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        Frame.getFrames()[0].setCursor(yesno ? waitCursor : defCursor);
    }

    /**
     * This method can be used to ask if the program shall be closed
     *
     * @param question the sentence in the ConfirmDialog
     * @param dialogTitle the title of the Dialog
     */
    public static void end_Programm_with_Confirm_Dialog(String question, String dialogTitle) {
        boolean x = JOptionPane.showConfirmDialog(null, question, dialogTitle, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        if (x) {
            System.exit(0);
        }
    }

    private static void wait_(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyInternals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
