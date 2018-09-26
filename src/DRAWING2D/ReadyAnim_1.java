/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DRAWING2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ReadyAnim_1 extends JPanel implements ActionListener {

    Timer timer;
    private double angle = 0;
    private double scale = 1;
    private double delta = 0.01;
    Rectangle.Float r = new Rectangle.Float(20, 20, 20, 20);

    public ReadyAnim_1() {
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        int h = getHeight();
        int w = getWidth();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.translate(w / 2, h / 2);
        

        if (angle > 6) {
            System.out.println("x = " + getX() + "  y = " + getY());
            System.out.println("w = " + getWidth() + "  h = " + getHeight());
            g2d.clearRect(-100, -100, getWidth(),getHeight());
//            timer.stop();
            angle = 0;
        }

        g2d.rotate(angle);

//    g2d.scale(scale, scale);

        g2d.fill(r);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Moving star");
        frame.add(new ReadyAnim_1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle += 0.05;
        
        repaint();
    }
}
