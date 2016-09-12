/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.visual;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JButton;

/**
 *
 * @author KOCMOC
 */
public class PaintJButton extends JButton {

    public PaintJButton() {
        //Whithout this it won't work!
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0),
                Color.WHITE,
                new Point(0, getHeight()),
                new Color(249, 250, 252)));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }
}
