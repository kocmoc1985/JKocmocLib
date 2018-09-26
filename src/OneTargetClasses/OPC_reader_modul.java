/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OneTargetClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafish.clients.opc.JOpc;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.variant.Variant;
import resources.MyInternals;

/**
 *
 * @author Administratororg.
 */
public class OPC_reader_modul implements Runnable {

    private boolean stop = true;
//    private Console con;
//*************************************
    int absolute_time = 0;
    String date;
    String recipe = "";//************************************¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤
    String order = "";//************************************¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤
    public String batch = "";
    String batchPrev = "";
    String s1_mills_torque, s2_mixer_disch, s3_mills_speed, s4_mills_gap_left, s5_mills_gapright, s6_mills_undifined, s7_mills_undifined;
    private int initialize_flag = 1;
    public int flag_disch;//*****************************************¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    
    public Properties p;
    public Properties signal_props;
    public Hashtable signal_dict_map;
    private String testData;

    public OPC_reader_modul() {

        try {
            setJavaLibraryPath("c:\\tmp", "JCustomOpc");
        } catch (SecurityException ex) {
            Logger.getLogger(OPC_reader_modul.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(OPC_reader_modul.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(OPC_reader_modul.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(OPC_reader_modul.class.getName()).log(Level.SEVERE, null, ex);
        }

        p = new Properties();
        p = properties_load_properties("autostarterfed.properties", true);

        signal_props = new Properties();
        signal_props = properties_load_properties("opc_signals.properties", true);
        signal_dict_map = hashtable_swap_keys_and_values(signal_props);
        try {
            read();
        } catch (InterruptedException ex) {
            Logger.getLogger(OPC_reader_modul.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void read() throws InterruptedException {

        String[] arr = properties_values_to_array_convertor(signal_props);


        JOpc.coInitialize();
        JOpc jopc = new JOpc(p.getProperty("opc_ip_host"), p.getProperty("opc_server_name"), p.getProperty("opc_server_pass"));
        OpcItem[] opcItemArr = new OpcItem[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() > 1) { // If the name of the signal is less than one char it is than not taken into account
                OpcItem item = new OpcItem(arr[i], true, "");
                opcItemArr[i] = item;
            }
        }

        OpcGroup group = new OpcGroup("group1", true, 100, 0.0f);

        for (int i = 0; i < opcItemArr.length; i++) {

            if (opcItemArr[i] != null) {
                group.addItem(opcItemArr[i]);
            }
        }

//        group.addItem(item1);
//        group.addItem(item2);
        jopc.addGroup(group);

        try {
            jopc.connect();

        } catch (ConnectivityException e2) {
            e2.printStackTrace();
//
//            try {
//                this.stop();
//            } catch (Throwable ex) {
//                Logger.getLogger(OPCVerifier.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

        try {
            jopc.registerGroups();
            System.out.println("OPCGroup are registered... ");

        } catch (UnableAddGroupException e2) {
            e2.printStackTrace();
        } catch (UnableAddItemException e2) {
            e2.printStackTrace();
        }

        synchronized (this) {
            this.wait(50);
        }

        // Synchronous reading of item

        while (stop == false) {
            synchronized (this) {
                this.wait(1000);
            }
            absolute_time++;
            String[] signalIDandValue = new String[opcItemArr.length];
            try {
                this.testData = "";
                for (int i = 0; i < opcItemArr.length; i++) {
                    if (opcItemArr[i] != null) {

                        String datum = MyInternals.get_proper_date_and_time_default_format();
                        //----------------------------------------------------------
                        String actualOpcItemName = (String) signal_dict_map.get(opcItemArr[i].getItemName());
                        System.out.println("" + actualOpcItemName);
                        //----------------------------------------------------------
                        OpcItem responseItem = jopc.synchReadItem(group, opcItemArr[i]);
                        signalIDandValue[i] = responseItem.getItemName() + " ; " + Variant.getVariantName(responseItem.getDataType()) + " ; " + responseItem.getValue() + " ; " + datum + " ->>>>>  " + actualOpcItemName;

                        if (actualOpcItemName.equals("mixer_recipe")) {
                            recipe = "" + responseItem.getValue();
                        } else if (actualOpcItemName.equals("mixer_order")) {
                            order = "" + responseItem.getValue();
                        } else if (actualOpcItemName.equals("mixer_batchnr")) {
                            batch = "" + responseItem.getValue(); //************************************¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤

                        } else if (actualOpcItemName.equals("mills_torque")) {
                            s1_mills_torque = "" + responseItem.getValue();

                        } else if (actualOpcItemName.equals("mixer_discharge")) {
                            s2_mixer_disch = "" + responseItem.getValue();

                        } else if (actualOpcItemName.equals("mills_speed")) {
                            s3_mills_speed = "" + responseItem.getValue();
                        } else if (actualOpcItemName.equals("mills_gapleft")) {
                            s4_mills_gap_left = "" + responseItem.getValue();
                        } else if (actualOpcItemName.equals("mills_gapright")) {
                            s5_mills_gapright = "" + responseItem.getValue();
                        } else if (actualOpcItemName.equals("xxxxx")) {
                            s6_mills_undifined = "" + "" + responseItem.getValue();
                        } else if (actualOpcItemName.equals("yyyyyy")) {
                            s7_mills_undifined = "" + responseItem.getValue();
                        }

                        this.testData += signalIDandValue[i] + "\n";
                        System.out.println("" + testData);

                    }
                }
                initialize_flag++;



            } catch (ComponentNotFoundException e1) {
                e1.printStackTrace();
            } catch (SynchReadException e) {
                e.printStackTrace();
            }
        } // WHILE (within 1 sec)


        JOpc.coUninitialize();
    }

    /**
     *
     * @param path_andOr_fileName "onoff.properties" or "c:/src/onoff.properties"
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

    public static void setJavaLibraryPath(String path, String dllLibraryNameWithoutExtension) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        // Reset the "sys_paths" field of the ClassLoader to null.
        Class clazz = ClassLoader.class;
        Field field = clazz.getDeclaredField("sys_paths");
        boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        Object original = field.get(clazz);
        // Reset it to null so that whenever "System.loadLibrary" is called, it will be reconstructed with the changed value.
        field.set(clazz, null);
        try {
            // Change the value and load the library.
            System.setProperty("java.library.path", path);//"c:\\tmp"
            if (dllLibraryNameWithoutExtension.length() > 0) {
                System.loadLibrary(dllLibraryNameWithoutExtension);
            }
        } finally {
            //Revert back the changes.
            field.set(clazz, original);
            field.setAccessible(accessible);
        }
    }

    public static String[] properties_values_to_array_convertor(Properties p) {
        ArrayList<String> list = new ArrayList();
        Hashtable h = new Hashtable();
        h = (Hashtable) p;

        Set set = h.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            list.add((String) h.get((String) it.next()));
        }
        Collections.sort(list);
        String[] arr = new String[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
//            System.out.println("" + list.get(i));
        }

        return arr;
    }

    /**
     * Swaps keys and values in an Hashtable
     * @param ht The Hashtable to convert
     * @return a Hashtable containing all keys from given Hashtable
     */
    public static Hashtable hashtable_swap_keys_and_values(Object o) {
        Hashtable h = new Hashtable();
        Hashtable ht_in = (Hashtable) o;
        Set set = ht_in.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = (String) ht_in.get(key);
            h.put(value, key);
        }
        ht_in = null;
        return h;
    }

    public static void main(String[] args) {
        new OPC_reader_modul();
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


