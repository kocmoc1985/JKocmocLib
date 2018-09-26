package resources.visual;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class MyDrawShapes extends JFrame {

    public static void main(String[] args) {
        MyDrawShapes myDrawShapes = new MyDrawShapes();
    }

    public MyDrawShapes() {
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new PaintSurface(), BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * @tags draw star, create star, drawStar, createStar,drawStarShape
     * @param arms
     * @param center
     * @param rOuter
     * @param rInner
     * @return 
     */
    public static Shape createStar(int arms, Point center, double rOuter, double rInner) {
        double angle = Math.PI / arms;

        GeneralPath path = new GeneralPath();

        for (int i = 0; i < 2 * arms; i++) {
            double r = (i & 1) == 0 ? rOuter : rInner;
            Point2D.Double p = new Point2D.Double(center.x + Math.cos(i * angle) * r, center.y + Math.sin(i * angle) * r);
            if (i == 0) {
                path.moveTo(p.getX(), p.getY());
            } else {
                path.lineTo(p.getX(), p.getY());
            }
        }
        path.closePath();
        return path;
    }

    private final class PaintSurface extends JComponent {

        ArrayList<Shape> shapes = new ArrayList<Shape>();
        Point startDrag, endDrag;
        Shape found = null;

        public PaintSurface() {
            Shape s;
//            Shape s = new Rectangle2D.Float(10, 10, 60, 80);
//            shapes.add(s);
//
//            s = new RoundRectangle2D.Float(110, 10, 80, 80, 10, 10);
//            shapes.add(s);
//
//            s = new Ellipse2D.Float(10, 110, 80, 80);
//            shapes.add(s);
//
//            s = new Arc2D.Float(10, 210, 80, 80, 90, 90, Arc2D.OPEN);
//            shapes.add(s);
//
//            s = new Arc2D.Float(110, 210, 80, 80, 0, 180, Arc2D.CHORD);
//            shapes.add(s);
//
            s = new Arc2D.Float(210, 210, 80, 80, 45, 90, Arc2D.PIE);
            shapes.add(s);

            Shape s2 = createStar(5, new Point(20, 20), 5, 15);
            shapes.add(s2);
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setPaint(Color.LIGHT_GRAY);
            for (int i = 0; i < getSize().width; i += 10) {
                g2.draw(new Line2D.Float(i, 0, i, getSize().height));
            }
            for (int i = 0; i < getSize().height; i += 10) {
                g2.draw(new Line2D.Float(0, i, getSize().width, i));
            }

            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            for (Shape s : shapes) {
                g2.draw(s);
                g2.fill(s);
            }
        }
    }
}