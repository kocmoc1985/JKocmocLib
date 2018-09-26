/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DRAWING2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class FiguresAndText2 extends JComponent {


    @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

//    // draw entire component white
//    g.setColor(Color.black);
//    g.fillRect(0, 0, getWidth(), getHeight());
    
    // yellow circle
    g.setColor(Color.yellow);
    g.fillOval(0, 0, 20, 20);
    
  }

  public static void main(String args[]) {
    JFrame mainFrame = new JFrame("Graphics demo");
    mainFrame.getContentPane().add(new FiguresAndText2());
    mainFrame.pack();
    mainFrame.setVisible(true);
  }
}
