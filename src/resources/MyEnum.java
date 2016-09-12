/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

public class MyEnum {

    protected Graphics g;
    protected ImageObserver observer;

    public MyEnum(Graphics g, ImageObserver observer) {
        this.g = g;
        this.observer = observer;
    }

    public enum Align {
        North, NorthEast, East, SouthEast, South, SouthWest, West, NorthWest, Center
    }

    public static Point2D getPoint(RectangularShape bounds, Align align) {
        double x = 0.0;
        double y = 0.0;

        switch (align) {
            case North:
                x = bounds.getCenterX();
                y = bounds.getMinY();
                break;
            case NorthEast:
                x = bounds.getMaxX();
                y = bounds.getMinY();
                break;
            case East:
                x = bounds.getMaxX();
                y = bounds.getCenterY();
                break;
            case SouthEast:
                x = bounds.getMaxX();
                y = bounds.getMaxY();
                break;
            case South:
                x = bounds.getCenterX();
                y = bounds.getMaxY();
                break;
            case SouthWest:
                x = bounds.getMinX();
                y = bounds.getMaxY();
                break;
            case West:
                x = bounds.getMinX();
                y = bounds.getCenterY();
                break;
            case NorthWest:
                x = bounds.getMinX();
                y = bounds.getMinY();
                break;
            case Center:
                x = bounds.getCenterX();
                y = bounds.getCenterY();
                break;
        }

        return new Point2D.Double(x, y);
    }
}
