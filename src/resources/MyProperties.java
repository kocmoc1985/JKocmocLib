/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MyProperties {

    private static HashMap<String, String> properties_to_use_map = new HashMap();
    private static int nr_properties;

    private static void example_save_properties_ordered(String propertiesPath) throws IOException {
        //
        LinkedHashMap props = properties_load_properties_ordered(propertiesPath);
        //
        props.put("project_name", "demo");
        //
//        System.out.println("get: " + props.get("project_name","bemo"));
        //
        properties_save_properties_ordered(props, propertiesPath);
        //
    }
    
    public static MyLinkedHashMapDefault properties_load_properties_ordered(String propertiesPath) {
        //
        ArrayList<String> properties_list = properties_load_properties_to_arraylist(propertiesPath);
        //
        MyLinkedHashMapDefault pseudo_properties = new MyLinkedHashMapDefault();
        //
        String key;
        String value;
        //
        for (String property : properties_list) {
            //
            String[] arr = property.split("#");
            //
            if (arr.length == 1) {
                key = arr[0];
                value = "";
            } else {
                key = arr[0];
                value = arr[1];
            }
            //
            pseudo_properties.put(key, value);
            //
        }
        //
        return pseudo_properties;
        //
    }
    
    public static void properties_save_properties_ordered(LinkedHashMap<String, String> lhm, String fileName) throws IOException {
        //
        FileWriter fstream = new FileWriter(fileName, false);
        BufferedWriter out = new BufferedWriter(fstream);
        //
        Set set = lhm.keySet();
        Iterator it = set.iterator();
        //
        while (it.hasNext()) {
            String k = (String) it.next();
            String v = (String) lhm.get(k);
            //
            out.write(k + "=" + v);
            out.newLine();
            out.flush();
            //
        }
        out.close();
    }
    
    /**
     * SUPER GOOD
     * @param path
     * @return 
     */
    public static Properties choose_properties(String path) {
        String dialog = choose_properties_dialog_string(path);
        String property_path;
        Properties properties;
        if (nr_properties == 1) {
            property_path = (String) properties_to_use_map.get("" + 1);
            properties = properties_load_properties(path + "/" + property_path, false);
        } else {
            int val = Integer.parseInt(JOptionPane.showInputDialog(dialog));
            property_path = (String) properties_to_use_map.get("" + val);
            properties = properties_load_properties(path + "/" + property_path, false);
        }
        if (properties == null) {
            JOptionPane.showMessageDialog(null, "properties not found or error occured: " + property_path + " / program will close");
            System.exit(0);
            return null;
        }
        return properties;
    }

    private static String choose_properties_dialog_string(String path) {
        File[] f = new File(path).listFiles();
        String dialog = "";
        int i = 1;
        for (File file : f) {
            String file_name = file.getName();
            if (file_name.contains("properties")) {
                dialog += i + ". " + file_name + "\n";
                properties_to_use_map.put("" + i, file_name);
                i++;
                nr_properties++;
            }
        }
        return dialog.isEmpty() ? "0" : dialog;
    }

    /**
     *
     * @param in_properties
     * @return
     */
    public static void iterate_through_properties(Properties in_properties) {

        Set set = in_properties.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String next = (String) it.next();
            System.out.println(" " + next + " | " + in_properties.getProperty(next));
        }
    }

    public static void save_properties_which_is_placed_in_jar_file(Properties properties, URL url) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("Url is not set.");
        } else {
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            store(properties, connection.getOutputStream());
            return;
        }
    }

    private static void store(Properties properties, OutputStream outputStream) throws IOException {
        try {
            if (properties == null) {
                throw new IllegalArgumentException("Properties is not set.");
            }
            if (outputStream == null) {
                throw new IllegalArgumentException("OutputStream is not set.");
            }
            properties.store(new BufferedOutputStream(outputStream), null);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     *
     * @param props_to_store the properties to be saved
     * @param fileName the name of file in which the properties gonna be saved
     * @param title_inside_file title inside the props file
     */
    public static void properties_save_properties(Properties props_to_store, String fileName, String title_inside_file) {
        try {
            props_to_store.store(new FileOutputStream(fileName), title_inside_file);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }

    /**
     * Saves the properties in the same order as in the file
     *
     * @param properties_list
     * @param fileName
     * @throws IOException
     */
    public static void properties_save_properties_manual(ArrayList<String> properties_list, String fileName) throws IOException {
        FileWriter fstream = new FileWriter(fileName, false);
        BufferedWriter out = new BufferedWriter(fstream);

        for (String property : properties_list) {
            String arr[] = property.split("#");
            String key = arr[0];
            String value = arr[1];
            out.write(key + "=" + value);
            out.newLine();
            out.flush();
        }
        out.close();
    }

    /**
     *
     * @param path_andOr_fileName "onoff.properties" or
     * "c:/src/onoff.properties"
     * @param list_properties specifies if the properties should be listed
     * @return loaded properties
     */
    public static Properties properties_load_properties(String path_andOr_fileName, boolean list_properties) {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(path_andOr_fileName));
            if (list_properties == true) {
                p.list(System.out);
            }
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
        return p;
    }

    /**
     * Loads the properties to an "ArrayList". The properties are loaded in the
     * same order as they are in the file!
     *
     * @param path
     * @return
     */
    public static ArrayList<String> properties_load_properties_to_arraylist(String path) {
        ArrayList<String> properties_list = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader);
            String[] parts;

            String rs = br.readLine();
            while (rs != null) {
                parts = rs.split("=");
                if (parts.length == 1) {
                    rs = br.readLine();
                    continue;
                }
                if (parts.length == 0) {
                    break;
                }
                if (parts[0].isEmpty() == false && parts[1].isEmpty() == false) {
                    properties_list.add(properties_list.size(), parts[0] + "#" + parts[1]);
                }

                rs = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("" + e);
        }

        return properties_list;
    }

    public static Properties properties_load_properties(URL url, boolean list_properties) {
        Properties p = new Properties();
        try {
            p.load(url.openStream());
            if (list_properties == true) {
                p.list(System.out);
            }
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
        return p;
    }

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!! SAVES THE FILE WITH GIVEN ENCODING
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! UTF-8 for öÅ and so on
     *
     * @param props_to_store the properties to be saved
     * @param fileName the name of file in which the properties gonna be saved
     * @param title_inside_file title inside the props file
     */
    public static void properties_save_properties_with_required_encoding(Properties props_to_store, String fileName_path, String title_inside_file, String encoding) {
        try {
            Writer out = new OutputStreamWriter(new FileOutputStream(fileName_path), encoding);
            props_to_store.store(out, title_inside_file);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }
}
