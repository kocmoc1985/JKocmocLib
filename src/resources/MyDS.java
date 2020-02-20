/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MyDS {
    
    public  static void constraintBufferSize(LinkedList<Properties> buffer, int maxSize) {
        if (buffer != null) {
            if (buffer.size() >= maxSize) {
                while (buffer.size() >= maxSize) {
                    buffer.pollFirst();
                }
            }
        }
    }
    

    public static char[] reverseCharArr(char[] arr) {
        char[] arr_return = new char[arr.length];
        int counter = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            arr_return[counter] = arr[i];
            counter++;
        }
        return arr_return;
    }

    public static Properties joinProperties(Properties p1, Properties p2) {

        Properties joined_properties = new Properties();

        Set set = p1.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = p1.getProperty(key);
            joined_properties.put(key, value);
        }

        Set set_2 = p2.keySet();
        Iterator it_2 = set_2.iterator();
        while (it_2.hasNext()) {
            String key = (String) it_2.next();
            String value = p2.getProperty(key);
            joined_properties.put(key, value);
        }

        return joined_properties;
    }

    public static ArrayList propertiesKeysToArrayList(Properties p) {
        ArrayList<String> list_to_return = new ArrayList<String>();

        Set set = p.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            list_to_return.add(key);
        }

        return list_to_return;
    }

    public static String[] propertiesKeysToArray(Properties p) {
        String[] arr = new String[p.size()];

        Set set = p.keySet();
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            String key = (String) it.next();
            arr[i] = key;
            i++;
        }
        return arr;
    }

    /**
     * This one reads the properties in the same order as the are in the file
     *
     * @param filename
     * @return
     */
    public static ArrayList<String> readPropertiesFileToArrayListSameOrder(String filename) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String[] parts;
            String rs = br.readLine();
            while (rs != null) {
                parts = rs.split("=");
                list.add(parts[0]);
                rs = br.readLine();
            }

        } catch (IOException e) {
            Logger.getLogger(MyDS.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public static String[] readPropertiesKeysToArraySameOrder(String filename) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String[] parts;
            String rs = br.readLine();
            while (rs != null) {
                parts = rs.split("=");
                list.add(parts[0]);
                rs = br.readLine();
            }

        } catch (IOException e) {
            Logger.getLogger(MyDS.class.getName()).log(Level.SEVERE, null, e);
        }

        return list.toArray(new String[list.size()]);
    }

    public static String[] array_list_to_array(ArrayList<String> list) {
        return list.toArray(new String[list.size()]);
    }

    public static ArrayList array_to_array_list(String[] arr) {
        ArrayList list = new ArrayList();
        list.addAll(Arrays.asList(arr));
        return list;
    }

    /**
     *
     * @param in_properties
     * @return
     */
    public static void list_keys_values_for_properties(Properties p) {
        Set set = p.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = p.getProperty(key);
            System.out.println("key = " + key + "  value = " + value);
        }
    }

    public static void list_keys_values_for_hashmap(HashMap p) {
        Set set = p.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = (String) p.get(key);
            System.out.println("key = " + key + "  value = " + value);
        }
    }

    public static void list_properties_keys_and_values_sorted(Properties p) {
        ArrayList<String> list = new ArrayList();

        Set set = p.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            try {
                String next = (String) it.next();
                list.add(next + " = " + (String) p.get(next));
            } catch (NoSuchElementException ex) {
                System.out.println("" + ex);
            }
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("" + list.get(i));
        }
    }

    public static void list_hashmap_keys_and_values_sorted(HashMap p) {
        ArrayList<String> list = new ArrayList();

        Set set = p.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            try {
                Object key = it.next();
                Object value = p.get(key);
                list.add(key + " = " + value);
            } catch (NoSuchElementException ex) {
                System.out.println("" + ex);
            }
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("" + list.get(i));
        }
    }

    public static HashMap update_value_hash_map(Object key, Object value, HashMap map) {
        map.remove(key);
        map.put(key, value);
        return map;
    }

    public static HashMap increase_map_value_by_one(int key, HashMap map) {
        if (map.containsKey(key)) {
            int val = (Integer) map.get(key);
            val++;
            return update_value_hash_map(key, val, map);
        } else {
            map.put(key, 1);
            return map;
        }
    }

    public static HashMap increase_map_value_with_x(Object key, Object value, HashMap map) {
        if (map.containsKey(key)) {
            Object result = null;
            if (map.get(key) instanceof Integer && value instanceof Integer) {
                int val_1 = (Integer) map.get(key);
                int val_2 = (Integer) value;
                result = val_1 + val_2;
            } else if (map.get(key) instanceof Integer && value instanceof Double) {
                int val_1 = (Integer) map.get(key);
                double val_2 = (Double) value;
                result = (double) (val_1 + val_2);
            }

            return update_value_hash_map(key, result, map);
        } else {
            map.put(key, value);
            return map;
        }
    }

    public static void list_hashmap_in_for_loop(HashMap p) {

        for (int i = 1; p.get(i) != null; i++) {
            System.out.println("" + p.get(i));
        }

    }

    public static HashMap swap_keys_and_values(HashMap ht) {
        HashMap h = new HashMap();
        Set set = ht.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = (String) ht.get(key);
            h.put(value, key);
        }
        return h;
    }

    public static HashMap swap_keys_and_values(Properties p) {
        HashMap hm = new HashMap();
        Set set = p.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = (String) p.get(key);
            hm.put(value, key);
        }
        return hm;
    }

    /**
     * Skriver ut Arrayen av typen LinkedList i output fönstret
     *
     * @param lista Tar imot LinkedList som skall skrivas ut
     */
    public static void iterate_through_a_linked_list(LinkedList<String> lista) {
        Iterator<String> iter = lista.iterator();
        while (iter.hasNext()) {
            String res = iter.next();
            System.out.println("" + res);
        }
    }

    /**
     * @tags convert array to arraylist
     * @param array
     * @return
     */
    public static ArrayList array_to_array_list(Object[] array) {
        ArrayList list = new ArrayList();
        list.addAll(Arrays.asList(array));
        return list;
    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        LinkedList<Integer>linkedList = new LinkedList<Integer>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        //
        int x = linkedList.poll();
        //
        System.out.println("x: " + x);
    }
}
