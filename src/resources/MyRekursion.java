/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import static resources.MyFile.delete_files_and_folders;

/**
 *
 * @author KOCMOC
 */
public class MyRekursion {

    //==========================================================================
    // [EXAMPLES OF RECURSION USED BY ME]
    // [MCAutoStarter -> Moduls -> Backuper -> copyDirectory(File source, File destination, FileFilter filter)]
    // [PropertiesReader -> main -> PReader -> find_properties(String path)]
    //
    //==========================================================================
    /**
     * This is a very good example of "Recursion" usage
     *
     * This method is a void but it uses the "ArrayList<String> list" as the
     * variable to be returned indirectly, Usage example.
     *
     * ArrayList<String>found_files_list = new ArrayList<String>(); //the list
     * which is filled by the method find_files_with_given_regex("xml", ".xml",
     * found_files_list);
     *
     * @tags recursion, rekursion, rekursija, recurssion, rekurssion
     * @param path - the initial path by the first call
     * @param regex - the expression to search for example ".exe"
     * @param list - the list to be filled with found matches
     */
    public static void recursively_find_files_with_given_regex(String path, String regex, ArrayList<String> list) {
        File[] f = new File(path).listFiles();
        for (File file : f) {
            if (file.isDirectory()) {
                recursively_find_files_with_given_regex(file.getPath(), regex, list);
            } else if (file.getName().contains(regex)) {
                list.add(file.getPath());
            }
        }
    }
    
    public static void main(String[] args) {
        ArrayList<String>list = new ArrayList<String>();
        recursively_find_files_with_given_regex(".", ".jar", list);
        
        for (String fileName : list) {
            System.out.println("" + fileName);
        }
    }
    
    public static void addMouseListenerToAllSubComponents(JFrame frame, MouseListener ml) {
        Component[] c_arr = frame.getContentPane().getComponents();

        for (Component component : c_arr) {
            component.addMouseListener(ml);
            addMouseListenerToAllComponentsOfComponent((JComponent) component, ml);
        }
    }

    private static void addMouseListenerToAllComponentsOfComponent(JComponent c, MouseListener ml) {
        Component[] c_arr = c.getComponents();
        for (Component component : c_arr) {
            component.addMouseListener(ml);
            addMouseListenerToAllComponentsOfComponent((JComponent) component, ml);
        }
    }

    public static void replaceComponentRecursively(JComponent search, Component replace, Component replaceWith) {
        //
        Component[] c_arr = search.getComponents();
        //
        for (Component component : c_arr) {
            if (component == replace) {
                //to do code
                Component parent = component.getParent();
                //
                if (parent instanceof JComponent) {
                    JComponent par = (JComponent) parent;
                    par.remove(replace);
                    par.add(replaceWith);
                }
                //
            } else {
                if (component instanceof JComponent) {
                    replaceComponentRecursively((JComponent) component, replace, replaceWith);
                }
            }
        }
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Test");
//        frame.setSize(new Dimension(200, 200));
//        frame.setLayout(new BorderLayout());
//        //
//        //
//        JPanel cont1 = new JPanel();
//        cont1.setLayout(new GridLayout(4, 1));
//        JScrollPane jScrollPane = new JScrollPane(cont1);
//        //
//        JButton button = new JButton("Button1");
//        //
//        cont1.add(button);
//        //
//        frame.add(jScrollPane);
//        //
//        //
//        frame.setVisible(true);
//        //
//        //
//        //
//        replaceComponentRecursively(jScrollPane, button, new JTextField("TEST"));
//    }

    /**
     * Uses recursion
     *
     * @param path
     */
    public static void recursively_delete_files_and_folders(String path) {
        File f = new File(path);
        File[] f_arr = f.listFiles();

        for (File file : f_arr) {
            if (file.isDirectory()) {
                delete_files_and_folders(file.getPath());
                file.delete();//delete folder
            } else {
                file.delete();
            }
        }
    }

    /**
     * This method finds and runs the application without knowing it's path, it
     * finds the path it's self.
     *
     * @OBS you should initially call this function ->
     * find_and_run_application(".", "mcbrowser.exe") -> where "." means current
     * directory
     * @param path
     * @param application_to_run_name
     */
    public static void find_and_run_application(String path, String application_to_run_name) {
        File[] f = new File(path).listFiles();

        for (File file : f) {
            if (file.isDirectory()) {
                find_and_run_application(file.getPath(), application_to_run_name);
            } else if (file.getName().toLowerCase().contains(application_to_run_name.toLowerCase())) {
                try {
                    run_application_exe_or_jar(application_to_run_name, file.getParent());
                } catch (IOException ex) {
                    Logger.getLogger(MyRekursion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Runs both .exe & .jar applications
     *
     * @param application_to_run_name
     * @param path
     * @throws IOException
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
}
