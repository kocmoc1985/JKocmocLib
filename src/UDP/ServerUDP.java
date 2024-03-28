/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.FileInputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ServerUDP implements Runnable {

    private String client_ip;
    private int client_port;
    private Properties p;
    private String properties_path;
    private boolean on = false;

    public ServerUDP(String ip, int port, String properties_path) {
        this.client_ip = ip;
        this.client_port = port;
        this.properties_path = properties_path;
    }

    /**
     * 
     */
    private void activate() {
        p = loadProperties(properties_path);
        if (p.getProperty("ServerUDPActive").equals("on")) {
            on = true;
            System.out.println("on = " + on);
        } else {
            on = false;
        }
    }

    /**
     *  
     * @param dpacket
     */
    private void sendDatagram(DatagramPacket dpacket) {
        DatagramSocket dsocketX = null;
        try {
            dsocketX = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dsocketX.send(dpacket);
            System.out.println("datagram sent to:" + dpacket.getAddress() + "  " + dpacket.getPort());
        } catch (IOException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param msg
     */
    public void prepareAndSendDatagram(String msg) {
        DatagramPacket dpacket = null;
        try {

            //<Prepare a datagramm with ip & socket parameters requested from server>
            byte[] clMsg = msg.getBytes();
            dpacket = new DatagramPacket(
                    clMsg, clMsg.length,
                    InetAddress.getByName(client_ip), client_port);
            //</Prepare a datagramm with ip & socket parameters requested from server>
            //<Pass over the datagram to the method that handles sending>
            sendDatagram(dpacket);
            //</Pass over the datagram to the method that handles sending>

        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    private Properties loadProperties(String p_path) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(p_path));
            return props;
        } catch (IOException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void run() {
//        activate();
        while (true) {
//            activate();
            prepareAndSendDatagram("TestUDP");
            synchronized (this) {
                try {
                    wait(60000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
    public static void main(String[] args) {
        new Thread(new ServerUDP("213.115.93.254", 3389,"autostartercp.properties")).start();
    }
}
