/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JList_with_image;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Administrator
 */
public class MyInfoPanel extends JPanel implements MouseListener, ActionListener {

    private DefaultListModel model = new DefaultListModel();
    private JList list = new JList(model);
    private JScrollPane scroll_pane = new JScrollPane(list);
    private MyInfoPanelEntry selected_entry;
    private PopupMenu popup = new PopupMenu("Popup");
    private MenuItem menu_item = new MenuItem("Send");


    public MyInfoPanel() {
        
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        build();
        list.setCellRenderer(new JListCellRenderer()); // this class (JListCellRenderer) is in this file!
//        list.setVisibleRowCount(4);
        list.addMouseListener(this);
        menu_item.addActionListener(this);
        popup.add(menu_item);
        list.add(popup);
    }

    public static void main(String[] args) {
        MyInfoPanel mip = new MyInfoPanel();
        mip.addMessage(new MyInfoPanelEntry("Failure while loading", "images/1.png"));
        JFrame frame = new JFrame("List Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mip.getPanel());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method is used to insert this component in an other 
     * component
     * @return 
     */
    public JPanel getPanel() {
        return this;
    }

    private void build() {
        this.setLayout(new GridLayout(1, 0));
        this.add(scroll_pane);
    }

    /**
     * Adds an object of user type into the 
     * List Box. Note that in the list box
     * is the toString method of inserted object 
     * displayed
     * @param user 
     */
    public void addMessage(MyInfoPanelEntry entry) {
        model.addElement(entry);
    }

    /**
     * Cleares the entire list
     */
    public void clearList() {
        this.model.clear();
    }

    public DefaultListModel getModel() {
        return this.model;
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("button  " + e.getButton());
        if (e.getComponent() instanceof JList && e.getButton() == 3) {
            JList listt = (JList) e.getComponent();
            selected_entry = (MyInfoPanelEntry) listt.getSelectedValue();
            popup.show(list, 0, 0);
        }

    }

    public void mousePressed(MouseEvent e) {
        System.out.print("");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.print("");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.print("");
    }

    public void mouseExited(MouseEvent e) {
        System.out.print("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu_item) {
            System.out.print("");
        }
    }

    /**
     * 
     */
    class JListCellRenderer extends JLabel implements ListCellRenderer {

        private final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

        public JListCellRenderer() {
            setOpaque(true);
            setIconTextGap(12);
        }

        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            MyInfoPanelEntry entry = (MyInfoPanelEntry) value;
            setText(entry.getTitle());
            setIcon(entry.getImage());
            if (isSelected) {
                setBackground(HIGHLIGHT_COLOR);
                setForeground(Color.white);
            } else {
                setBackground(Color.white);
                setForeground(Color.black);
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
            return this;
        }
    }
}
