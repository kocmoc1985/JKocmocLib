package resources.visual;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * PaintWindow är till för att testa klassen Graphics. Efter anrop till ritkommandon
 * ska du anropa metoden <code>update</code> för att ändringarna säkert ska bli synliga.<br><br>
 * <strong>Exempel</strong><br>
 * <code>
 * PaintWindow pw = new PaintWindow();<br>
 * pw.setBackground(Color.RED);<br>
 * pw.update(); // Bakgrunden ändras till röd färg<br>
 * // div kod här<br>
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
     * Konstruerar ett PaintWindow med ritytan 200x200 pixlar. Bakgrundsfärgen
     * är vit.
     */
    public PaintWindow() {
        this(200, 200, Color.white);
    }

    /**
     * Konstruerar ett PaintWindow med angivna mått och angiven bakgrundsfärg.
     * @param width bredden på ritytan
     * @param height höjden på ritytan
     * @param backgroundColor bakgrundsfärg på ritytan
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
     * Metoden ser till att bilden som visas i fönstret är uppdaterad.
     */
    public void update() {
        paintPanel.repaint();
    }

    /**
     * Sätter bakgrundsfärgen på ritytan.
     * @param color ny bakgrundsfärg
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
