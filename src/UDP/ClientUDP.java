/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.net.SocketException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class ClientUDP implements Runnable {

    private DatagramSocket dsocket;
    private int recieve_port;

    public ClientUDP(int port) {
        this.recieve_port = port;
        try {
            dsocket = new DatagramSocket(recieve_port);
        } catch (SocketException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void recieve() {
        byte[] buffer = new byte[120];
        int buffersize = 120;
        DatagramPacket recPacket = new DatagramPacket(buffer, buffersize);
        System.out.println("trying to recieve!");
        try {
            dsocket.receive(recPacket);
            System.out.println("recieved msg!");
            JOptionPane.showMessageDialog(null, new String(recPacket.getData()).trim());
        } catch (IOException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            recieve();
        }
    }

    public static void main(String[] args) {
        new Thread(new ClientUDP(9999)).start();
    }
}
