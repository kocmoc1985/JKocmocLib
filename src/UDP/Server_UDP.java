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

/**
 *
 * @author Administrator
 */
public class Server_UDP implements Runnable {

    private DatagramSocket dsocket;
    private final int recieve_port;
    private final ServerProtocol_UDP protocol_UDP;

    public Server_UDP(int port, ServerProtocol_UDP protocol_UDP) {
        //
        this.recieve_port = port;
        this.protocol_UDP = protocol_UDP;
        //
        try {
            dsocket = new DatagramSocket(recieve_port);
        } catch (SocketException ex) {
            Logger.getLogger(Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recieve() {
        //
        byte[] buffer = new byte[120];
        int buffersize = 120;
        DatagramPacket recPacket = new DatagramPacket(buffer, buffersize);
        //
//        System.out.println("trying to recieve!");
        //
        try {
            //
            dsocket.receive(recPacket);
            String msg = new String(recPacket.getData()).trim();
//            System.out.println("recieved msg: " + msg);
            protocol_UDP.add(msg);
            //
        } catch (IOException ex) {
            Logger.getLogger(Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
    }

    @Override
    public void run() {
        while (true) {
            recieve();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server_UDP(9999, new ServerProtocolTest())).start();
    }
}
