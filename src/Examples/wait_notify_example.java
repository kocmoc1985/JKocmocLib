/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import java.net.*;
import java.util.*;

/**
 *
 * @author Andrei Brassas m09k2847
 */
public class wait_notify_example extends LinkedList {

    private Queue<DatagramPacket> queue = new LinkedList<DatagramPacket>();

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void enqueue(DatagramPacket dpacket) {
        queue.add(dpacket);
        notify();
    }

    public synchronized DatagramPacket dequeue() {
        while (queue.isEmpty()) {
            try {
                wait(); // tråden väntar tills den blir notified
            } catch (InterruptedException e) {
                return null;
            }
        }
        return queue.remove();
    }
    

}
