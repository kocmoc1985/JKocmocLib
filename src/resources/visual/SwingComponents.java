/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.visual;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author KOCMOC
 */
public class SwingComponents {

    /**
     * @InterfaceToUse = MouseListener
     * @EventTypeToUse = mousePressed
     */
    public static void how_to___double_click_event(MouseEvent me) {
        if (me.getSource() == jTable2 && (me.getClickCount() == 2)) {
            //
        }
    }

    /**
     * @InterfaceToUse = ItemListener
     * @EventTypeToUse = itemStateChanged
     */
    public static void listener_for__JComboBox(ItemEvent ie) {
        if (ie.getSource() == new Object()) {
            //Do something
        }
    }

    private static HashMap<JComboBox, AutoCompleteSupport> autoSupportList = new HashMap<JComboBox, AutoCompleteSupport>();

    public static JComboBox fillComboBox(SqlBasicLocal sql, JComboBox jbox, String query, Object initialValue) {
        //
        ArrayList<Object> list = new ArrayList<Object>();
        //
        if (initialValue != null) {
            list.add(initialValue);
        }
        //
        try {
            ResultSet rs = sql.execute(query);
            while (rs.next()) {
                String val = rs.getString(1);
                if (val != null && val.isEmpty() == false) {
                    list.add(rs.getString(1));
                }
            }
            //
        } catch (Exception ex) {
            Logger.getLogger(SwingComponents.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        Object[] arr = list.toArray();
        //
        if (arr.length == 0) {
            arr = new Object[1];
            arr[0] = "";
        }
        //
        //#AutoComplete, Auto complete# glazedlists_java15-1.9.1.jar is needed
        if (autoSupportList.containsKey(jbox) == false) {
            AutoCompleteSupport support = AutoCompleteSupport.install(
                    jbox, GlazedLists.eventListOf(arr));
            //
            autoSupportList.put(jbox, support);
            //
            jbox.setSelectedIndex(0);
        } else {
            //
            AutoCompleteSupport support = autoSupportList.get(jbox);
            //
            if (support.isInstalled()) {
                support.uninstall();
                support = AutoCompleteSupport.install(
                        jbox, GlazedLists.eventListOf(arr));
                //
                autoSupportList.remove(jbox);
                //
                autoSupportList.put(jbox, support);
                //
            }
        }
        //
        //
        return jbox;
    }
    
      public static JComboBox fillComboBox_no_autofill(SqlBasicLocal sql, JComboBox jbox, String query, Object initialValue) {
        //
        ArrayList<Object> list = new ArrayList<Object>();
        //
        if (initialValue != null) {
            list.add(initialValue);
        }
        //
        try {
            ResultSet rs = sql.execute(query);
            while (rs.next()) {
                String val = rs.getString(1);
                if (val != null && val.isEmpty() == false) {
                    list.add(rs.getString(1));
                }
            }
            //
        } catch (Exception ex) {
            Logger.getLogger(SwingComponents.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        Object[] arr = list.toArray();
        //
        jbox.removeAllItems();
        //
        jbox.setModel(new DefaultComboBoxModel(arr));
        //
        return jbox;
    }

    
    /**
     * @deprecated 
     * @param jbox
     * @param values
     * @param initialValue
     * @return 
     */
    public static JComboBox fillComboBox(JComboBox jbox, Object[] values, Object initialValue) {
        //
        ArrayList<Object> list = new ArrayList<Object>();
        //
        if (initialValue != null) {
            list.add(initialValue);
        }
        //
        list.addAll(Arrays.asList(values));
        //
        Object[] arr = list.toArray();
        //
        if (arr.length == 0) {
            arr = new Object[1];
            arr[0] = "";
        }
        //
        //#AutoComplete, Auto complete# glazedlists_java15-1.9.1.jar is needed
        AutoCompleteSupport support = AutoCompleteSupport.install(
                jbox, GlazedLists.eventListOf(arr));
        //
        jbox.setSelectedIndex(0);
        //
        return jbox;
    }
}
