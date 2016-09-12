package resources.visual;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * PaintWindow �r till f�r att testa klassen Graphics. Efter anrop till ritkommandon
 * ska du anropa metoden <code>update</code> f�r att �ndringarna s�kert ska bli synliga.<br><br>
 * <strong>Exempel</strong><br>
 * <code>
 * PaintWindow pw = new PaintWindow();<br>
 * pw.setBackground(Color.RED);<br>
 * pw.update(); // Bakgrunden �ndras till r�d f�rg<br>
 * // div kod h�r<br>
 * Graphics g = pw.getGraphics();<br>
 * g.setColor(Color.MAGENTA);<br>
 * g.fillRect(10,20,200,100);<br>
 * pw.update(); // Lila rektangel blir synlig<br>
 * </code> 
 * @author tsroax
 */
public class PaintWindow {

    private JFrame frame = new JFrame();
    private PaintPanel paintPanel = new PaintPanel();
    private BufferedImage image;
    private Graphics g;

    /**
     * Konstruerar ett PaintWindow med ritytan 200x200 pixlar. Bakgrundsf�rgen
     * �r vit.
     */
    public PaintWindow() {
        this(200, 200, Color.white);
    }

    /**
     * Konstruerar ett PaintWindow med angivna m�tt och angiven bakgrundsf�rg.
     * @param width bredden p� ritytan
     * @param height h�jden p� ritytan
     * @param backgroundColor bakgrundsf�rg p� ritytan
     */
    public PaintWindow(int width, int height, Color backgroundColor) {
        frame.setTitle("PaintWindow");
        paintPanel.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(paintPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        image = new BufferedImage(paintPanel.getWidth(), paintPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        g = image.createGraphics();
        setBackground(backgroundColor);
        update();
    }

    /**
     * Metoden returnerar grafik-verktyg till ritytan.
     * @return grafik-verktyg till ritytan
     */
    public Graphics getGraphics() {
        return g;
    }

    /**
     * Saves the image
     */
    public void saveImage() {
        try {
            BufferedImage bi = image; // retrieve image
            File outputfile = new File("c:/saved.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println("" + e);
        }


    }

    /**
     * Metoden ser till att bilden som visas i f�nstret �r uppdaterad.
     */
    public void update() {
        paintPanel.repaint();
    }

    /**
     * S�tter bakgrundsf�rgen p� ritytan.
     * @param color ny bakgrundsf�rg
     */
    public void setBackground(Color color) {
        g.setColor(color);
        g.fillRect(0, 0, paintPanel.getWidth(), paintPanel.getHeight());
    }

    private class PaintPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }
    }
}
