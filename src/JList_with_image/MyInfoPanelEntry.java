/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JList_with_image;

import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class MyInfoPanelEntry {

    private String title;
    private String imagePath;
    private ImageIcon image;

    public MyInfoPanelEntry(String title, String imagePath) {
        this.title = title;
        this.imagePath = imagePath;
    }

    public ImageIcon getImage() {
        if (image == null) {
            image = new ImageIcon(imagePath);
        }
        return image;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
