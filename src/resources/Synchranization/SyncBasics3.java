/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class can be called a sort of JOptionPane.
 * I finally figured out how to make the executing thread to wait for
 * answer from the gui
 * @author KOCMOC
 */
public class SyncBasics3 {

    class ObjectX1 extends JFrame implements ActionListener {

        JButton btn1 = new JButton("btn1");
        JButton btn2 = new JButton("btn2");
        Thread executingThread;

        /**
         * Note that this Class should be initialized without
         * java.awt.EventQueue.invokeLater(new Runnable() !!!!
         * 
         */
        public ObjectX1() {
            executingThread = Thread.currentThread();
            initGui();
        }

        private void initGui() {
            this.setSize(200, 200);
            this.getContentPane().setBackground(Color.red);
            this.setVisible(true);
            this.setLayout(new GridLayout(1, 4));
            btn1.addActionListener(this);
            btn2.addActionListener(this);
            this.add(btn1);
            this.add(btn2);
            synchronized (executingThread) {
                try {
                    System.out.println("" + executingThread.getName());
                    executingThread.wait();
                    System.out.println("wait interrupted");
                } catch (InterruptedException ex) {
                    Logger.getLogger(SyncBasics3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btn1) || e.getSource().equals(btn2)) {
                
                synchronized (executingThread) {
                    executingThread.notifyAll();
                    System.out.println("" + executingThread.getName());
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
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//              
//            }
//        });

          ObjectX1 objX1 = new ObjectX1();
        Thread x2 = new Thread(new ObjectX2());

        x2.start();
    }

    public static void main(String[] args) {
        SyncBasics3 basics2 = new SyncBasics3();
        basics2.test();
    }
}
