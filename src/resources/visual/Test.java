/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.visual;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author KOCMOC
 */
public class Test extends JFrame implements ActionListener {

    public static void main(String[] args) {
        Test t = new Test();
    }

    public Test() throws HeadlessException {
        this.setSize(200, 200);
        this.setLayout(new GridLayout(1, 1));
        //
        JComboBox comboBox = new JComboBox(new DefaultComboBoxModel());
        this.add(comboBox);
        //
        addActionListenerToJComboBoxButton(comboBox, this);
        //
        this.setVisible(true);
    }
    
    
    public static HashMap<JComboBox, JButton> JCOMBOBOX_MAP = new HashMap<JComboBox, JButton>();

    public static void addActionListenerToJComboBoxButton(JComboBox c, ActionListener al) {
        Component[] c_arr = c.getComponents();
        for (Component component : c_arr) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.addActionListener(al);
                JCOMBOBOX_MAP.put(c, button);
                System.out.println("");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("YES");
    }
}
