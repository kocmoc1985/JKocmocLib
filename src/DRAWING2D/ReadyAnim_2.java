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

public class ReadyAnim_2 extends JPanel implements ActionListener {

    Timer timer;
    private double angle_r1 = 0;
    private double angle_r2 = 160;
    private Rectangle.Float r = new Rectangle.Float(10, 10, 20, 20);
    private Rectangle.Float r_2 = new Rectangle.Float(10,10, 20, 20);

    public ReadyAnim_2() {
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

        g2d.setPaint(Color.BLUE);
        g2d.rotate(angle_r1);
        g2d.fill(r);
        
        g2d.setPaint(Color.GREEN);
        g2d.rotate(angle_r2);
        g2d.fill(r_2);
        
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Moving star");
        frame.add(new ReadyAnim_2());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle_r1 += 0.05;
        angle_r2 += 0.00001;
        
//        repaint();
        repaint();
    }
}
