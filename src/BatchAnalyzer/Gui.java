/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BatchAnalyzer;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author KOCMOC
 */
public class Gui extends JFrame implements ActionListener {
    private JTextField file = new JTextField ("110731");
    private JTextField from = new JTextField("");
    private JTextField to = new JTextField("Time to");
    private JButton start = new JButton("Calculate");
    private MainSource fr;

    public Gui(MainSource fr){
        super();
        this.fr = fr;

        this.setTitle("Frequency Kalkulator");
        this.getContentPane().setLayout(new GridLayout(2,2));
        this.getContentPane().setBackground(Color.blue);
        this.setSize(200,150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(323, 229);
        this.setVisible(true);
        
//        file.setBounds(10, 35, 160, 25);
//        from.setBounds(10, 95, 80, 25);
//        to.setBounds(10, 155, 80, 25);


        this.add(file);
//        this.add(from); //
//        this.add(to);
        this.add(start);

         start.setBounds(40, 195, 120, 25);
         start.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== this.start){
            String fileName = file.getText();
            fr.readLines(fileName);
            fr.mainLoop();
//            fr.countMl();
            System.out.println("END");
        }
    }
}
