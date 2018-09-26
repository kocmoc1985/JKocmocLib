/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Synchranization;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author KOCMOC
 */
public class SyncBasics2 {

    class ObjectX1 extends JFrame implements Runnable, ActionListener {

        JButton btn1 = new JButton("btn1");
        JButton btn2 = new JButton("btn2");

        private void initGui() {
            this.setSize(200, 200);
            this.getContentPane().setBackground(Color.red);
            this.setVisible(true);
            this.setLayout(new GridLayout(1, 4));
            btn1.addActionListener(this);
            btn2.addActionListener(this);
            this.add(btn1);
            this.add(btn2);
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(SyncBasics2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public void run() {
            initGui();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btn1) || e.getSource().equals(btn2)) {
                synchronized (this) {
                    notify();
                }
            }
        }
    }

    class ObjectX2 extends JFrame implements Runnable {

        private void initGui() {
            this.setSize(200, 200);
            this.getContentPane().setBackground(Color.red);
            this.setVisible(true);
            this.setLayout(new GridLayout(1, 4));
            JButton btn1 = new JButton("btn1");
            JButton btn2 = new JButton("btn2");
            JButton btn3 = new JButton("btn3");
            this.add(btn1);
            this.add(btn2);
            this.add(btn3);
        }

        @Override
        public void run() {
            initGui();
        }
    }

    public void test() {
        Thread x1 = new Thread(new ObjectX1());
        Thread x2 = new Thread(new ObjectX2());

        x1.start();

        synchronized (x1) {
            try {
                x1.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(SyncBasics2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        x2.start();
    }

    public static void main(String[] args) {
        SyncBasics2 basics2 = new SyncBasics2();
        basics2.test();
    }
}
