/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComPort;

import gnu.io.*;
import java.io.*;
import java.lang.reflect.Field;

/**
 *
 * @author Administrator
 */
public class PrintFileToComPort {

    public static void main(String args[]) throws Exception {

        if (args.length != 2) {
            System.err.println("usage : java PrintFile port file");
            System.err.println("sample: java PrintFile LPT1 sample.prn");
            System.exit(-1);
        }

        String portname = args[0];
        String filename = args[1];

        // Get port
        CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(portname);

        // Open port
        // Requires owner name and timeout
        CommPort port = portId.open("Java Printing", 30000);

        // Setup reading from file
        FileInputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);

        // Setup output
        OutputStream os = port.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);

        int c;
        while ((c = bis.read()) != -1) {
            bos.write(c);
        }

        // Close
        bos.close();
        bis.close();
        port.close();
    }

     public static void setJavaLibraryPath() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
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
            System.setProperty("java.library.path", "libraries");//"c:\\tmp"
            System.loadLibrary("rxtxParallel");
            System.loadLibrary("rxtxSerial");

        } finally {
            //Revert back the changes.
            field.set(clazz, original);
            field.setAccessible(accessible);
        }
    }
}
