/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unimportant;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class PopupTest {
  public static void main(String args[]) {
    JFrame frame = new JFrame("Popup Menu Listener");
    Container contentPane = frame.getContentPane();

    final String flavors[] = { "Item 1", "Item 2", "Item 3"};

    PopupMenuListener listener = new PopupMenuListener() {
      boolean initialized = false;

      public void popupMenuCanceled(PopupMenuEvent e) {
      }

      public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
      }

      public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        if (!initialized) {
          JComboBox combo = (JComboBox) e.getSource();
          ComboBoxModel model = new DefaultComboBoxModel(flavors);
          combo.setModel(model);
          initialized = true;
        }
      }
    };

    JComboBox jc = new JComboBox();
    jc.addPopupMenuListener(listener);
    jc.setMaximumRowCount(4);
    jc.setEditable(true);
    contentPane.add(jc, BorderLayout.NORTH);

    frame.pack();
    frame.show();
  }
}
