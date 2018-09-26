/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.visual;

import com.sun.jndi.toolkit.url.Uri;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MyImage {

    /**
     * To create a imageicon for a frame
     *
     * @param frame
     * @param path
     * @return
     */
    public static JFrame setImageIcon(JFrame frame, String path) {
        frame.setIconImage(new ImageIcon(path).getImage());
        return frame;
    }

    /**
     * Just to remember path example("C:/project/002.jpg");
     *
     * @param
     * @return ImageIcon
     */
    public static ImageIcon create_ImageIcon(String path) {
        return new ImageIcon(path);
    }

    /**
     *
     */
    public static Image createImage(String path) {
        return Toolkit.getDefaultToolkit().getImage(path);
    }
    
    public static Image createImage(byte[] arr) {
        return Toolkit.getDefaultToolkit().createImage(arr);
    }
    
    public static Clipboard getClipBoard() {
        return Toolkit.getDefaultToolkit().getSystemClipboard();
    }
    
    public static ImageIcon scaleImageIconSmooth(ImageIcon ic, int width, int height) {
        Image img = ic.getImage();
        img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ic.setImage(img);
        return ic;
    }
    
    public static void fitImageToJLabel(URL imageUrl, JLabel label) {
        //
        BufferedImage img;
        //
        try {
            img = ImageIO.read(imageUrl);
            //
            Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            //
            ImageIcon imageIcon = new ImageIcon(dimg);
            //
            label.setIcon(imageIcon);
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param path
     * @param width
     * @param height
     * @return scaled ImageIcon
     */
    public static ImageIcon scaleImageIconSmooth(String path, int width, int height) {
        ImageIcon ic = new ImageIcon(path);
        Image img = ic.getImage();
        img = ic.getImage();
        img = img.getScaledInstance(1100, 740, Image.SCALE_SMOOTH);
        ic.setImage(img);
        return ic;
    }

    /**
     *
     * @param path
     * @param width
     * @param height
     * @return scaled Image
     */
    public static Image scaleImageSmooth(String path, int width, int height) {
        ImageIcon ic = new ImageIcon(path);
        Image img = ic.getImage();
        img = ic.getImage();
        img = img.getScaledInstance(1100, 740, Image.SCALE_SMOOTH);
        ic.setImage(img);
        return img;
    }

    /**
     *
     * @param path
     * @param width
     * @param height
     * @param jpTitle the JOptionaPane title
     * @return
     */
    public static ImageIcon scaleImageIconSmoothAndShowInJOPtionPane(String path, int width, int height, String jpTitle) {
        ImageIcon ic = new ImageIcon(path);
        Image img = ic.getImage();
        img = ic.getImage();
        img = img.getScaledInstance(1100, 740, Image.SCALE_SMOOTH);
        ic.setImage(img);
        JOptionPane.showMessageDialog(null, ic, jpTitle, 1);
        return ic;
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

    /**
     *
     */
    public static class ImageContainer {
        
        Object img1;
        Object img2;
        
        public ImageContainer(Object img1, Object img2) {
            this.img1 = img1;
            this.img2 = img2;
        }
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void ImageToFileCompression() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("Img");
        ZipEntry ze = new ZipEntry("images.zip");
        ZipOutputStream zos = new ZipOutputStream(fos);
        zos.putNextEntry(ze);
        zos.setLevel(9);
        ObjectOutputStream oos = new ObjectOutputStream(zos);
        ImageIcon k = new ImageIcon("c:/b.jpg");
        ImageContainer ims = new ImageContainer(k, k);
        oos.writeObject(ims);
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void ImageToFileNoCompression() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("Img2");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ImageIcon k = new ImageIcon("c:/b.jpg");
        ImageContainer ims = new ImageContainer(k, k);
        oos.writeObject(ims);
    }
    //***************************************************************************************************************************************

    /**
     * VERY
     * IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * This method can pack a Objcect and write it to file which contains Images
     * in form of byte[]arrays
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void ObjectWithImagesToFile() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("Img3");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ImageContainer2 ims = new ImageContainer2(FiletoByteArray("c:/b.jpg"), FiletoByteArray("c:/c.jpg"));
        Object obj = (Object) ims;
        oos.writeObject(obj);
    }

    /**
     * VERY
     * IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static byte[] FiletoByteArray(String path) throws FileNotFoundException, IOException {
        byte[] content;
        FileInputStream p = new FileInputStream(path);
        content = new byte[p.available()];
        p.read(content);
        p.close();
        return content;
    }

    /**
     * VERY
     * IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param path
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void FileToObject(String path) throws IOException, ClassNotFoundException {
        FileInputStream fas = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fas);
        Object obj = ois.readObject();
        ImageContainer2 imgs = (ImageContainer2) obj;
        JOptionPane.showMessageDialog(null, imgs.getImageIcon1());
    }

    /**
     *
     */
    public static class ImageContainer2 implements Serializable {
        
        byte[] img1;
        byte[] img2;
        
        public ImageContainer2(byte[] img1, byte[] img2) {
            this.img1 = img1;
            this.img2 = img2;
        }
        
        public ImageIcon getImageIcon1() {
            return new ImageIcon(img1);
        }
        
        public ImageIcon getImageIcon2() {
            return new ImageIcon(img2);
        }
    }
    
    public static void main(String[] args) {
//
//                    try {
//            //            ImageToFile();
//            //            ImageToFileNoCompression();
//                        ObjectWithImagesToFile();
//                    } catch (FileNotFoundException ex) {
//                        Logger.getLogger(MyImage.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (IOException ex) {
//                        Logger.getLogger(MyImage.class.getName()).log(Level.SEVERE, null, ex);

        try {
            FileToObject("Img3");
        } catch (IOException ex) {
            Logger.getLogger(MyImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
