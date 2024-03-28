/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import ca.beq.util.win32.registry.RegistryKey;
import ca.beq.util.win32.registry.RegistryValue;
import ca.beq.util.win32.registry.RootKey;
import ca.beq.util.win32.registry.ValueType;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOCMOC
 */
public class MyRegistry {

    public static void edititing_existing_registry_key() {
        RegistryKey r = new RegistryKey(RootKey.HKEY_CURRENT_USER, "Software\\TightVNC\\Server\\Test");
        if (r.exists() == false) {
            r.create();
        }
        RegistryValue v = new RegistryValue("UseVncAuthentication", ValueType.REG_DWORD, 0);
        r.setValue(v);
    }

    /**
     *
     * @param value - 0 = enable, 1 = disable
     */
    public static void disable_enable_rdp(int value) {
        RegistryKey r = new RegistryKey(RootKey.HKEY_LOCAL_MACHINE, "System\\CurrentControlSet\\Control\\Terminal Server");
        if (r.exists() == false) {
            r.create();
        }
        RegistryValue v = new RegistryValue("fDenyTSConnections", ValueType.REG_DWORD, value);
        r.setValue(v);
    }


    public static void main(String[] args) {
//        edititing_existing_registry_key();
        disable_enable_rdp(0);
//        System.out.println("" + portOccupied(3389));
    }
}
