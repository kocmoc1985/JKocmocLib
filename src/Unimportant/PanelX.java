/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unimportant;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author KOCMOC
 */
public class PanelX extends JPanel implements MyInterface{

    private Object value;
    private JLabel jLabel = new JLabel("AAAA");

    public PanelX() {
        this.setSize(50,50);
        this.setBackground(Color.yellow);
        this.setLayout(new GridLayout(1, 1));
        this.add(jLabel);
    }
    
    @Override
    public void takeValue(Object value) {
       jLabel.setText(""+(Integer)value);
    }

    public Object getValue() {
        return value;
    }
    
    
    
}
