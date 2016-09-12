/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.XML;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.JDOMException;

/**
 *
 * @author KOCMOC
 */
public class MyXmlProps extends MyXMLBasic {

    private String PATH;

    public MyXmlProps(String path, boolean b) {
        //
        PATH = path;
        //
        try {
            loadDocFromFile(path);
//            writeXMLDocToFile("test.xml", false, false);
        } catch (JDOMException ex) {
            Logger.getLogger(MyXmlProps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyXmlProps.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getProperty(String property) {
        return getElemTextByName(property);
    }

    public String getProperty(String parent, String property, String defaultValue) {
        String value = getElemTextByName(parent, property);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        } else {
            return value;
        }
    }

    public String getProperty(String property, String defaultValue) {
        String value = getElemTextByName(property);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        } else {
            return value;
        }
    }

    public String getSubProperty(String property, String subproperty, String defaultValue) {
        String value = getAttributeValue(property, subproperty);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        } else {
            return value;
        }
    }

    public boolean setProperty(String property, String value) {
        return setElemTextByName(property, value);
    }

    public boolean setSubProperty(String property, String subproperty, String value) {
        return setAttributeValue(property, subproperty, value);
    }

    public boolean addSubProperty(String property, String subproperty, String value) {
        return addAttribute(property, subproperty, value);
    }

    public void saveProperties() {
        writeXMLDocToFile(PATH, false, false);
    }

//    public static void main(String[] args) {
//        MyXmlProps myProps = new MyXmlProps("properties/goetfert.xml", true);
//        String odbc = myProps.getProperty("odbc", "xxx");
//        System.out.println("" + odbc);
//
//        myProps.addSubProperty("port", "info2", "???");
//
////        myProps.setProperty("ip", "192.168.18.220");
//        myProps.saveProperties();
//    }
}
