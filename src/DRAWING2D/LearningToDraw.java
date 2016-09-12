/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DRAWING2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** A demonstration of Java2D transformations */
public class LearningToDraw extends JPanel {

    static final int WIDTH = 800, HEIGHT = 375; // Size of our example

    @Override
    public String getName() {
        return "Paints";
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    /** Draw the example */
    @Override
    public void paint(Graphics g1) {
    Graphics2D g = (Graphics2D) g1;

        //Set background color of the JPanel, NOTE THIS ONE MUST BE FIRST
        g1.setColor(Color.black);
        g1.fillRect(0, 0, getWidth(), getHeight());

        // draw line
        g1.setColor(Color.white);
        g1.drawLine(0, 200, 800, 200);

        //draw rect 
        g1.setColor(Color.yellow);
        g1.fillRect(20, 60, 20, 40);
        
        


    }

    public static void main(String[] a) {
        JFrame f = new JFrame();
        f.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setContentPane(new LearningToDraw());
        f.setSize(800, 375);
        f.setVisible(true);
    }
}
