/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.visual;

import resources.visual.PaintWindow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class MyDraw {

    private static PaintWindow pw = new PaintWindow(600, 600, Color.white);
    private static Graphics g = pw.getGraphics();

    public static void draw_oval() {
        g.setColor(Color.red);
        g.drawOval(0, 0, 50, 50);
    }

    /**
     *
     * @return
     */
    public static HashMap create_color_mapping() {
        HashMap color_map = new HashMap();
        color_map.put("black", Color.BLACK);
        color_map.put("magenta", Color.MAGENTA);
        color_map.put("blue", Color.BLUE);
        color_map.put("red", Color.RED);
        color_map.put("green", Color.GREEN);
        color_map.put("yellow", Color.YELLOW);
        return color_map;
    }
    
    public static JFrame set_location_of_jframe_so_it_appears_in_the_middle_of_the_screen(JFrame frame){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2,
                (d.height - frame.getSize().height) / 2);
        return frame;
    }

    public static void main(String[] args) {
        draw_oval();
    }
}
