/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Telnet;

import examples.util.IOUtil;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.telnet.TelnetClient;

/**
 *
 * @author Administrator
 */
public class TelnetCl {

    public static void main(String[] args) throws IOException {
        TelnetClient telnet;

        telnet = new TelnetClient();

        try {
            telnet.connect("localhost",1111);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        InputStream input = telnet.getInputStream();

        IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(),
                System.in, System.out);
        

        byte[] readBuffer = new byte[8];

        while (input.available() > 0) {
            int numBytes = input.read(readBuffer);
            System.out.println("c");
        }


        String msg = new String(readBuffer).trim();
        
        System.out.println(msg);

        try {
            telnet.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }
}
