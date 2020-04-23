/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.jezhumble.javasysmon.JavaSysMon;
import com.jezhumble.javasysmon.ProcessInfo;
import java.awt.Desktop;
import java.awt.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MyProcess {

    /**
     * This is very powerful RUN METHOD It also works when a program should be
     * run as from it's "Home" dir "." -> Current dir, ".." -> step out one dir
     *
     * @param path
     * @param application_to_run_name
     */
    public static void find_and_run_application(String path, String application_to_run_name) {
        //
        File[] f = new File(path).listFiles();
        //
        for (File file : f) {
            if (file.isDirectory()) {
                find_and_run_application(file.getPath(), application_to_run_name);
            } else if (file.getName().toLowerCase().trim().equals(application_to_run_name.toLowerCase().trim())) {
                try {
                    run_application_exe_or_jar(application_to_run_name, file.getParent());
                } catch (IOException ex) {
                    Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

//    private void run_application_exe_or_jar(String application_to_run_name, String path) throws IOException {
//        String[] commands = new String[3];
//        if (application_to_run_name.contains(".jar")) {
//            commands[0] = "java";
//            commands[1] = "-jar";
//            commands[2] = application_to_run_name;
//        } else {
//            commands[0] = path + "/" + application_to_run_name;
//            commands[1] = "";
//            commands[2] = "";
//        }
//        ProcessBuilder builder = new ProcessBuilder(commands);
//        builder.directory(new File(path));
//        builder.start();
//    }
    /**
     * Runs both .exe & .jar applications. If you run .exe from the same dir use
     * "." for path parameter
     *
     * @param application_to_run_name
     * @param path
     * @throws IOException
     * @verified 2014-02-14
     */
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

    /**
     * f you run .exe from the same dir use * "." for path parameter
     *
     * @param application_to_run_name
     * @param arg
     * @param path
     * @throws IOException
     */
    public static void run_application_jar_with_argument(String application_to_run_name, String arg, String path) throws IOException {
        String[] commands = new String[4];
        if (application_to_run_name.contains(".jar")) {
            commands[0] = "java";
            commands[1] = "-jar";
            commands[2] = application_to_run_name; //OBS! pay attention here
            commands[3] = arg;
        }
        ProcessBuilder builder = new ProcessBuilder(commands);
        builder.directory(new File(path));
        builder.start();
    }

    /**
     * This one is BEST! Suits both for launching .exe & .jar files
     *
     * @throws IOException
     */
    public static void run_application_as_from_home_dir() throws IOException {
        String[] commands = {"xml/backupclient/mcautostarter.exe", "xx"};
        ProcessBuilder builder = new ProcessBuilder(commands);
        builder.directory(new File("xml/backupclient"));
        builder.start();
    }

    /**
     * Use this one for running Java Examle of running java with ProcessBuilder
     *
     * @param app_path
     * @param name
     * @param arg
     * @throws IOException
     */
    private static void run_java_app_with_processbuiler(String app_path, String name, String arg) throws IOException {
        String[] commands = {"java", "-jar", name, arg};
        ProcessBuilder builder = new ProcessBuilder(commands);
        builder.directory(new File(app_path));
        builder.start();
    }

    /**
     * This can be useful, the program is launched with assosiated app - with
     * assosiated i ment the program which is used to open another in windows
     *
     * @param file
     * @throws IOException
     */
    public static void run_application_with_associated_application(File file) throws IOException {
        Desktop.getDesktop().open(file);
    }

    

    /**
     * Can be very useful
     *
     * @return
     * @throws IOException
     */
    public static Process run_cmd() throws IOException {
        String[] commands = new String[3];
        commands[0] = "cmd";
        commands[1] = "/c";
        commands[2] = "start";
        ProcessBuilder builder = new ProcessBuilder(commands);
        return builder.start();
    }

    public static void navigate_to_webbpage(String addr) {
        if (Desktop.isDesktopSupported()) {
            try {
                try {
                    Desktop.getDesktop().browse(new URI(addr));//"http://www.mixcont.com"
                } catch (URISyntaxException ex) {
                    Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showInputDialog(null, "Your current configuration doesn't allow"
                    + " to open web browser please open this link manually:", addr);
        }
    }

    /**
     * OBS! Seldom but some applications don't star with methods using
     * "ProcessBuilder" NOTE: it doesn't work without Thread.sleap(100). If you
     * want to run a non .exe file you should then run it with help of a .cmd or
     * .bat file
     *
     * @param path
     */
    public static void run_application_exe(String path) {
//        path = "c:/documents and settings/administrator/my documents/progs/1.exe";
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Process p = Runtime.getRuntime().exec(path);

        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }

    /**
     *
     * @param path_to_program
     * @param p1
     * @param p2
     */
    public static void run_application_exe_with_parameters(String path_to_program, String p1, String p2) {
        String[] cmd = {path_to_program, p1, p2};
        try {
            Process process = Runtime.getRuntime().exec(cmd);
        } catch (IOException ex) {
            Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Runs a java app
     *
     * @param name - wright the name with extension
     * @deprecated
     */
    public static void run_application_jar_with_cmd_console(String name) {
        String[] commands2 = {"java", "-jar", name};
        try {
            Process p = Runtime.getRuntime().exec(commands2);
        } catch (IOException ex) {
            Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //==============================================================================
    /**
     * Checks if the given process is running
     *
     * Note how important it is to compare with "toLowerCase()"!!!!!
     *
     * @processName str the process name to search for "Browser.exe"
     * @return
     */
    public static boolean processRunning(String processName) {
        JavaSysMon monitor = new JavaSysMon();
        ProcessInfo[] pinfo = monitor.processTable();
        for (int i = 0; i < pinfo.length; i++) {
            String pname = pinfo[i].getName();
            if (pname.toLowerCase().equals(processName.toLowerCase())) {
                return true;
            }

        }
        return false;
    }

    public static void terminate_process_no_external_apps_and_libraries(String progName) {
        //
        String KILL = "taskkill /F /IM ";
        //
        try {
            Runtime.getRuntime().exec(KILL + progName);
        } catch (IOException ex) {
            Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Terminate a process using the process name
     *
     * @param processName
     */
    public static void terminate_process_no_external_apps_in_use(String processName) {
        JavaSysMon monitor = new JavaSysMon();
        ProcessInfo[] pinfo = monitor.processTable();

        for (int i = 0; i < pinfo.length; i++) {
            String pname = pinfo[i].getName();
            int pid = pinfo[i].getPid();
            if (pname.toLowerCase().equals(processName.toLowerCase())) {
                monitor.killProcess(pid);
            }
        }
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 2; i++) {
//            JavaSysMon monitor = new JavaSysMon();
//            System.out.println("" + monitor.currentPid());
//        }
//
//    }
    /**
     * Terminate a process using its PID
     *
     * @param pid process id of the program that shoul be terminated
     */
    public static void terminate_process_no_external_apps_in_use(int pid) {
        JavaSysMon monitor = new JavaSysMon();
        monitor.killProcess(pid);
    }

    /**
     *
     * @param args
     */
    public static void terminate_process(String processName_with_exe) {
        String[] commands2 = {"c:/windows/system32/pskill.exe", processName_with_exe};// "c:/black87.rdp" ---//c:/windows/system32/logonsessions.exe
        try {
            Process pr = Runtime.getRuntime().exec(commands2);
        } catch (IOException ex) {
        }
    }

    /**
     * Checks if the given process is running
     *
     * @param str the process name to search for "Browser.exe"
     * @return
     */
    public static boolean processRunning(int pid) {
        JavaSysMon monitor = new JavaSysMon();
        ProcessInfo[] pinfo = monitor.processTable();
        for (int i = 0; i < pinfo.length; i++) {
            int pidS = pinfo[i].getPid();
            if (pidS == pid) {
                return true;
            }
        }
        return false;
    }
//==============================================================================

    /**
     * This one is for running cmd apps such as "ping" , "query" etc
     *
     * @param cmd_application
     * @param arg
     * @throws IOException
     */
    public static void run_with_cmd(String cmd_application, String arg) throws IOException {
        String[] commands = {"cmd", "/c", "start", "\"" + cmd_application + "\"", cmd_application, arg};
        ProcessBuilder builder = new ProcessBuilder(commands);
        builder.start();
    }

    /**
     * Launches the ping
     */
    public static void pingWithWindowsCmd(String hostIP, String numberTimesToPing) {
        String[] commands2 = {"cmd", "/c", "start", "\"ping\"", "ping", hostIP, "-n", numberTimesToPing};
        try {
            Process p = Runtime.getRuntime().exec(commands2);
        } catch (IOException ex) {
            Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Shuts down the computer
     *
     * @tags shut_down_computer, shut down, shutdown,shut_down, turn of
     * computer,turn_off_computer, turn of
     */
    public static void shut_down_immediately() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        // "t" is the time before shutDown is done
        Process proc = runtime.exec("shutdown -s -t 0");
//        Process proc = runtime.exec("standby -s -t 0");
        System.exit(0);
    }

    public static boolean shut_down_remote_pc(String host) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("shutdown -s -m \\\\" + host);
        int returnVal = proc.waitFor();
        return returnVal == 0;
    }

    private static boolean shut_down_remote_pc__with_catching_output(String host) throws IOException {
        Process p = Runtime.getRuntime().exec("shutdown -s -m \\\\" + host);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = input.readLine()) != null) {
            if (!line.trim().equals("")) {
                // keep only the process name
                return false;
            }
        }
        return true;
    }

    public static void restart() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("shutdown -r -t 0");
        System.exit(0);
    }

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
     *
     * @param rdp_path path to mstsc.exe
     * @param config_path path to rd icon containig configurations
     */
    public static void run_RDP_console_full_screen(String rdp_path, String config_path) {
        String[] commands2 = {rdp_path, config_path, "/console", "/f"};// "c:/black87.rdp"
        try {
            Process pr = Runtime.getRuntime().exec(commands2);
        } catch (IOException ex) {
        }
    }

    /**
     * Disconnects the current logged on session
     *
     * @param args
     */
    public static void disconnect_current_session() {
        String[] commands2 = {"c:/windows/system32/tsdiscon.exe"};// "c:/black87.rdp" ---//c:/windows/system32/logonsessions.exe
        try {
            Process pr = Runtime.getRuntime().exec(commands2);
        } catch (IOException ex) {
        }
    }

    /**
     * This method performs a restart of a local or remote machine, to be able
     * to use this method the psshutdown.exe is to be placed in
     * c:/windows/system32
     *
     * @param path
     */
    public static void remote_restart(String ipOrHost, String user_name, String password) {
        String[] commands = {"cmd", "/k", "start", "\"p\"", "psshutdown", "\\\\10.87.0.3", "-u", "Administrator", "-p", "black", "-r", "-e", "p:2:3"};
        String[] cmd = {"cmd", "/k", "start", "\"p\"", "psshutdown", "\\\\" + ipOrHost, "-u", user_name, "-p", password, "-r", "-f", "-e", "p:2:3"};
//            String[] commands2 = {"cmd", "/c", "start", "\"ping\"","ping","10.87.0.2", "-n","10"};
        try {
            Process p = Runtime.getRuntime().exec(cmd);
        } catch (IOException ex) {
            Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method performs a shutdown of a local or remote machine, to be able
     * to use this method the psshutdown.exe is to be placed in
     * c:/windows/system32
     *
     * @param path
     */
    public static void remote_shutdown(String ipOrHost, String user_name, String password) {
        String[] cmd = {"cmd", "/k", "start", "\"p\"", "psshutdown", "\\\\" + ipOrHost, "-u", user_name, "-p", password, "-s", "-f", "-e", "p:2:3"};
        try {
            Process p = Runtime.getRuntime().exec(cmd);
        } catch (IOException ex) {
            Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method performs a logof of a local or remote machine, to be able to
     * use this method the psshutdown.exe is to be placed in c:/windows/system32
     *
     * @param path
     */
    public static void remote_loggOf_console_session(String ipOrHost, String user_name, String password) {
        String[] cmd = {"cmd", "/k", "start", "\"p\"", "psshutdown", "\\\\" + ipOrHost, "-u", user_name, "-p", password, "-o", "-f"};
        try {
            Process p = Runtime.getRuntime().exec(cmd);
        } catch (IOException ex) {
            Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //===========================================================================

    /**
     * uses query.exe
     *
     * @tested
     * @tags grab_output, grab_out_put, grab output, console
     * @param path_to_executing_app
     * @return
     * @throws IOException
     */
    public static boolean check_if_console_session(String path_to_executing_app) throws IOException {
        String[] cmd = {path_to_executing_app, "session"};//c:/windows/system32/query.exe

        String line;
        InputStream stdout = null;

        // launch EXE and grab stdin/stdout and stderr
        Process process = Runtime.getRuntime().exec(cmd);
        stdout = process.getInputStream();

        // clean up if any output in stdout
        BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(stdout));
        while ((line = brCleanUp.readLine()) != null) {
            String pattern_1 = ">console"; //this means that this session belongs to my active session
            if (line.toLowerCase().contains(pattern_1.toLowerCase())) {
                return true;
            }
        }
        brCleanUp.close();
        return false;
    }

    /**
     *
     * @tested @tags grab_output, grab_out_put, grab output
     * @param path_to_executing_app
     * @param expression_to_match
     * @return
     * @throws IOException
     */
    private static int count_sessions(String path_to_executing_app, String expression_to_match) throws IOException {
        String[] cmd = {path_to_executing_app, "session"};//c:/windows/system32/query.exe

        int nr_sessions = 0;
        String line;
        InputStream stdout = null;

        // launch EXE and grab stdin/stdout and stderr
        Process process = Runtime.getRuntime().exec(cmd);
        stdout = process.getInputStream();

        // clean up if any output in stdout
        BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(stdout));
        while ((line = brCleanUp.readLine()) != null) {
            if (line.toLowerCase().contains(expression_to_match.toLowerCase())) {
                if (line.toLowerCase().contains("active")) {
                    System.out.println("session found");
                    nr_sessions++;
                }
            }
            System.out.println("[Stdout] " + line);
        }
        brCleanUp.close();
        return nr_sessions;
    }

    /**
     * @tags grab_output, grab_out_put, grab output
     * @param pathToLogonssesionsApp
     * @param expressionToMatch
     * @return
     * @throws IOException
     */
    private static ArrayList<String> check_hosts(String pathToLogonssesionsApp, String expressionToMatch) throws IOException {
        ArrayList<String> host_list = new ArrayList<String>();
        try {
            String[] cmd = {pathToLogonssesionsApp, "-n"};//c:/windows/system32/netstat.exe
            String line;

            InputStream stdout = null;

            // launch EXE and grab stdin/stdout and stderr
            Process process = Runtime.getRuntime().exec(cmd);
            stdout = process.getInputStream();

            // clean up if any output in stdout
            BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(stdout));
            while ((line = brCleanUp.readLine()) != null) {
                if (line.toLowerCase().contains(expressionToMatch.toLowerCase())) {//checkStringContains(line.toLowerCase(), expressionToMatch.toLowerCase())
                    String temp = line.substring(29, 47);
                    temp = temp.replaceAll(" ", "");
                    temp = temp.replaceAll(":", "");
//                    System.out.println("" + translateAddrToHost(temp));
                    host_list.add(line + " <> " + translateAddrToHost(temp));
                }
//                System.out.println("[Stdout] " + line);
            }
            brCleanUp.close();
        } catch (Throwable ex) {
            return null;
        }
        return host_list;
    }

    private static String translateAddrToHost(String ip) {
        String host_name = "";
        try {
            // Get hostname by textual representation of IP address
            InetAddress addr = InetAddress.getByName(ip);
            host_name = addr.getCanonicalHostName();
        } catch (UnknownHostException ex) {
//            Logger.getLogger(HelpM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return host_name;

    }

    public static void run_program_with_catching_output_EASY() throws IOException {
        Process p = Runtime.getRuntime().exec("tasklist.exe /V");
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = input.readLine()) != null) {
            if (!line.trim().equals("")) {
                // keep only the process name
                line = line.substring(1);
                System.out.println("" + line);
            }

        }
    }

    //*********************************
    public static void run_program_with_catching_output(TextArea textarea, String appName, String path, String cmd1, String cmd2, String cmd3) {
        textarea.setText("");
        String[] commands2 = {cmd1, cmd2, cmd3};
        try {
            run_program_with_catching_output_overall(textarea, appName, path, commands2);
        } catch (IOException ex) {
            Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void run_program_with_catching_output_overall(TextArea textarea, String appName, String path, String[] commands) throws IOException {
        String line;
        OutputStream stdin;
        InputStream stderr;
        InputStream stdout;

        Process process = run_application_exe_for_output_catching(appName, path, commands);

        // launch EXE and grab stdin/stdout and stderr
        stdin = process.getOutputStream();
        stderr = process.getErrorStream();
        stdout = process.getInputStream();

        // "write" the parms into stdin
        line = "param1" + "\n";
        stdin.write(line.getBytes());
        stdin.flush();

        line = "param2" + "\n";
        stdin.write(line.getBytes());
        stdin.flush();

        line = "param3" + "\n";
        stdin.write(line.getBytes());
        stdin.flush();

        stdin.close();

        // clean up if any output in stdout
        BufferedReader brCleanUp
                = new BufferedReader(new InputStreamReader(stdout));
        while ((line = brCleanUp.readLine()) != null) {
            textarea.append("" + line + "\n");
        }
        brCleanUp.close();

        // clean up if any output in stderr
        brCleanUp
                = new BufferedReader(new InputStreamReader(stderr));
        while ((line = brCleanUp.readLine()) != null) {
            textarea.append("" + line);
            System.out.println("[Stderr] " + line);
        }
        brCleanUp.close();
    }

    private static Process run_application_exe_for_output_catching(String application_to_run_name, String path, String[] args) throws IOException {
        String[] commands = new String[4];
        //
        if (path.isEmpty() == false) {
            commands[0] = path + "/" + application_to_run_name; // and here!
        } else {
            commands[0] = application_to_run_name;
        }
        //
        commands[1] = args[0];
        commands[2] = args[1];
        commands[3] = args[2];
        //
        ProcessBuilder builder = new ProcessBuilder(commands);
        //
        if (path.isEmpty() == false) {
            builder.directory(new File(path));
        }
        //
        return builder.start();
    }
    
    public static void main(String[] args) {
        try {
            grab_output_from_a_non_java_prog_for_testing();
        } catch (IOException ex) {
            Logger.getLogger(MyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //*********************************
    /**
     * OBS! Good as an example!
     *
     * @tags grab_output, grab_out_put, grab output
     * @throws IOException
     */
    private static void grab_output_from_a_non_java_prog_for_testing() throws IOException {

        String[] commands2 = {"ping","-n","1", "192.168.1.1"};

        String line;
        OutputStream stdin = null;
        InputStream stderr = null;
        InputStream stdout = null;

        // launch EXE and grab stdin/stdout and stderr
        Process process = Runtime.getRuntime().exec(commands2);
        stdin = process.getOutputStream();
        stderr = process.getErrorStream();
        stdout = process.getInputStream();

        // "write" the parms into stdin
        line = "param1" + "\n";
        stdin.write(line.getBytes());
        stdin.flush();

        line = "param2" + "\n";
        stdin.write(line.getBytes());
        stdin.flush();

        line = "param3" + "\n";
        stdin.write(line.getBytes());
        stdin.flush();

        stdin.close();

        // clean up if any output in stdout
        BufferedReader brCleanUp
                = new BufferedReader(new InputStreamReader(stdout));
        while ((line = brCleanUp.readLine()) != null) {
            System.out.println("[Stdout] " + line);
        }
        brCleanUp.close();

        // clean up if any output in stderr
        brCleanUp
                = new BufferedReader(new InputStreamReader(stderr));
        while ((line = brCleanUp.readLine()) != null) {
            System.out.println("[Stderr] " + line);
        }
        brCleanUp.close();
    }

    /**
     * This must use query.exe
     *
     * @deprecated
     * @tags grab_output, grab_out_put, grab output
     * @param pathToLogonssesionsApp
     * @param expressionToMatch
     * @return
     * @throws IOException
     */
    public static int count_sessions_usinq_query_exe(String pathToLogonssesionsApp, String expressionToMatch) throws IOException {
        int nrSessions = 0;
        String[] cmd = {pathToLogonssesionsApp, "session", expressionToMatch};//c:/windows/system32/query.exe

        String line;
        OutputStream stdin = null;
        InputStream stderr = null;
        InputStream stdout = null;

        // launch EXE and grab stdin/stdout and stderr
        Process process = Runtime.getRuntime().exec(cmd);
        stdin = process.getOutputStream();
        stderr = process.getErrorStream();
        stdout = process.getInputStream();

        // "write" the parms into stdin
        line = "param1" + "\n";
        stdin.write(line.getBytes());
        stdin.flush();

        line = "param2" + "\n";
        stdin.write(line.getBytes());
        stdin.flush();

        line = "param3" + "\n";
        stdin.write(line.getBytes());
        stdin.flush();

        stdin.close();

        // clean up if any output in stdout
        BufferedReader brCleanUp
                = new BufferedReader(new InputStreamReader(stdout));
        while ((line = brCleanUp.readLine()) != null) {

            if (checkStringContains(line.toLowerCase(), expressionToMatch.toLowerCase())) {
                nrSessions++;
            }
            System.out.println("[Stdout] " + line);
        }
        brCleanUp.close();

        // clean up if any output in stderr
        brCleanUp
                = new BufferedReader(new InputStreamReader(stderr));
        while ((line = brCleanUp.readLine()) != null) {

            System.out.println("[Stderr] " + line);

        }
        brCleanUp.close();
        return nrSessions++;
    }

    /**
     * is used by the method above
     *
     * @param searchedString
     * @return
     */
    private static boolean checkStringContains(String str_to_analyze, String searchedString) {
        int index1 = 0;
        index1 = str_to_analyze.indexOf(searchedString);
        if (index1 != -1) {
            return true;
        }
        return false;
    }
    //===========================================================================
}
