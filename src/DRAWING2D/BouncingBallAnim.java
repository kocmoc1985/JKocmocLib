///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package DRAWING2D;
//
//import java.awt.BorderLayout;
//import java.awt.Container;
//import java.awt.EventQueue;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.geom.Ellipse2D;
//import java.awt.geom.Rectangle2D;
//import java.util.ArrayList;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
///**
// * Shows an animated bouncing ball.
// * 
// * @version 1.33 2007-05-17
// * @author Cay Horstmann
// */
//public class BouncingBallAnim {
//    
//  public static void main(String[] args) {
//    EventQueue.invokeLater(new Runnable() {
//            @Override
//      public void run() {
//        JFrame frame = new BounceFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//      }
//    });
//  }
//}
//
///**
// * The frame with ball component and buttons.
// */
//class BounceFrame extends JFrame {
//  /**
//   * Constructs the frame with the component for showing the bouncing ball and
//   * Start and Close buttons
//   */
//  public BounceFrame() {
//    setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
//    setTitle("Bounce");
//
//    comp = new BallComponent();
//    add(comp, BorderLayout.CENTER);
//    JPanel buttonPanel = new JPanel();
//    addButton(buttonPanel, "Start", new ActionListener() {
//      public void actionPerformed(ActionEvent event) {
//        addBall();
//      }
//    });
//
//    addButton(buttonPanel, "Close", new ActionListener() {
//      public void actionPerformed(ActionEvent event) {
//        System.exit(0);
//      }
//    });
//    add(buttonPanel, BorderLayout.SOUTH);
//  }
//
//  /**
//   * Adds a button to a container.
//   * 
//   * @param c
//   *          the container
//   * @param title
//   *          the button title
//   * @param listener
//   *          the action listener for the button
//   */
//  public void addButton(Container c, String title, ActionListener listener) {
//    JButton button = new JButton(title);
//    c.add(button);
//    button.addActionListener(listener);
//  }
//
//  /**
//   * Adds a bouncing ball to the panel and makes it bounce 1,000 times.
//   */
//  public void addBall() {
//    try {
//      Ball ball = new Ball();
//      comp.add(ball);
//
//      for (int i = 1; i <= STEPS; i++) {
//        ball.move(comp.getBounds());
//        comp.paint(comp.getGraphics());
//        Thread.sleep(DELAY);
//      }
//    } catch (InterruptedException e) {
//    }
//  }
//
//  private BallComponent comp;
//
//  public static final int DEFAULT_WIDTH = 450;
//
//  public static final int DEFAULT_HEIGHT = 350;
//
//  public static final int STEPS = 1000;
//
//  public static final int DELAY = 3;
//}
//
///*
// * This program is a part of the companion code for Core Java 8th ed.
// * (http://horstmann.com/corejava)
// * 
// * This program is free software: you can redistribute it and/or modify it under
// * the terms of the GNU General Public License as published by the Free Software
// * Foundation, either version 3 of the License, or (at your option) any later
// * version.
// * 
// * This program is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
// * details.
// * 
// * You should have received a copy of the GNU General Public License along with
// * this program. If not, see <http://www.gnu.org/licenses/>.
// */
//
///**
// * The component that draws the balls.
// * 
// * @version 1.33 2007-05-17
// * @author Cay Horstmann
// */
//class BallComponent extends JPanel {
//  /**
//   * Add a ball to the component.
//   * 
//   * @param b
//   *          the ball to add
//   */
//  public void add(Ball b) {
//    balls.add(b);
//  }
//
//    @Override
//  public void paintComponent(Graphics g) {
//    super.paintComponent(g); // erase background
//    Graphics2D g2 = (Graphics2D) g;
//    for (Ball b : balls) {
//      g2.fill(b.getShape());
//    }
//  }
//
//  private ArrayList<Ball> balls = new ArrayList<Ball>();
//}
//
///*
// * This program is a part of the companion code for Core Java 8th ed.
// * (http://horstmann.com/corejava)
// * 
// * This program is free software: you can redistribute it and/or modify it under
// * the terms of the GNU General Public License as published by the Free Software
// * Foundation, either version 3 of the License, or (at your option) any later
// * version.
// * 
// * This program is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
// * details.
// * 
// * You should have received a copy of the GNU General Public License along with
// * this program. If not, see <http://www.gnu.org/licenses/>.
// */
//
///**
// * A ball that moves and bounces off the edges of a rectangle
// * 
// * @version 1.33 2007-05-17
// * @author Cay Horstmann
// */
//class Ball {
//  /**
//   * Moves the ball to the next position, reversing direction if it hits one of
//   * the edges
//   */
//  public void move(Rectangle2D bounds) {
//    x += dx;
//    y += dy;
//    if (x < bounds.getMinX()) {
//      x = bounds.getMinX();
//      dx = -dx;
//    }
//    if (x + XSIZE >= bounds.getMaxX()) {
//      x = bounds.getMaxX() - XSIZE;
//      dx = -dx;
//    }
//    if (y < bounds.getMinY()) {
//      y = bounds.getMinY();
//      dy = -dy;
//    }
//    if (y + YSIZE >= bounds.getMaxY()) {
//      y = bounds.getMaxY() - YSIZE;
//      dy = -dy;
//    }
//  }
//
//  /**
//   * Gets the shape of the ball at its current position.
//   */
//  public Ellipse2D getShape() {
//    return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
//  }
//
//  private static final int XSIZE = 15;
//
//  private static final int YSIZE = 15;
//
//  private double x = 0;
//
//  private double y = 0;
//
//  private double dx = 1;
//
//  private double dy = 1;
//}
