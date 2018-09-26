/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.Listeners;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author KOCMOC
 */
public class Testing_interface_main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("InterfaceTesting (Click the JPanel)");
        frame.setSize(800, 400);
        frame.setLayout(new GridLayout(1, 1));
        frame.setVisible(true);

        Implementor implementor = new Implementor();

        Consumer consumer = new Consumer(implementor);

        frame.add(implementor);
    }
}
