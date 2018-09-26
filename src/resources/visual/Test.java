/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.visual;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author KOCMOC
 */
public class Test extends JFrame {

    public Test() throws HeadlessException {
    setSize(400, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel usernameLabel = new JLabel("Username: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);

    usernameLabel.setDisplayedMnemonic(KeyEvent.VK_U);
    usernameLabel.setLabelFor(usernameField);
    passwordLabel.setDisplayedMnemonic(KeyEvent.VK_P);
    passwordLabel.setLabelFor(passwordField);

    getContentPane().add(usernameLabel);
    getContentPane().add(usernameField);
    getContentPane().add(passwordLabel);
    getContentPane().add(passwordField);
  }

  public static void main(String[] args) {
    new Test().setVisible(true);
  }
}
