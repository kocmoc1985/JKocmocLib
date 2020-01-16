/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author Administrator
 */
public class LoookAndFeel extends JFrame {

    public LoookAndFeel() {
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 4,2,0));
        this.setVisible(true);
        jbutton_look_and_feel();
    }

    public static void main(String[] args) {
        LoookAndFeel laf = new LoookAndFeel();
    }
    
    /**
     * TESTED [2019-12-23]
     */
    public static void jtableNimbusLookAndFeelVerticalScrollBarThumbFix(){
        UIManager.put("ScrollBar.minimumThumbSize", new DimensionUIResource(35, 35));
    }

    /**
     * Property String	Object Type
     * Button.actionMap	ActionMap
     *Button.background	Color
     *Button.border	Border
     *Button.contentAreaFilled	Boolean
     *Button.darkShadow	Color
     *Button.dashedRectGapHeight	Integer
     *Button.dashedRectGapWidth	Integer
     *Button.dashedRectGapX	Integer
     *Button.dashedRectGapY	Integer
     *Button.defaultButtonFollowsFocus Boolean	Button.disabledForeground
     *Color	Button.disabledGrayRange
     *Integer[ ]	Button.disabledShadow
     *Color	Button.disabledText
     *Color	Button.disabledToolBarBorderBackground Color
     *Button.focus	Color
     *Button.focusInputMap	InputMap
     *Button.font	Font
     *Button.foreground	Color
     *Button.gradient	List
     *Button.highlight	Color
     *Button.icon	Icon
     *Button.iconTextGap	Integer
     *Button.light	Color
     *Button.margin	Insets
     *Button.rollover	Boolean
     *Button.rolloverIconType	String
     *Button.select	Color
     *Button.shadow	Color
     *Button.showMnemonics	Boolean
     *Button.textIconGap	Integer
     *Button.textShiftOffset	Integer
     *Button.toolBarBorderBackground	Color
     *ButtonUI	String
     */
    private void jbutton_look_and_feel() {
        //Very important that the "UIManager" settings are done before the component is initialized!
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 40));
        //==========================GRADIENT=================================
        ArrayList<Object> gradients = new ArrayList();
        gradients.add(0.8);
        gradients.add(0.1);
        gradients.add(new Color(221, 232, 243));
        gradients.add(new Color(255, 255, 255));
        gradients.add(new Color(184, 207, 229));

        UIManager.put("Button.gradient", gradients);
        UIManager.put("Button.focus", Color.white);
        
//        SwingUtilities.updateComponentTreeUI(this);
        //===========================================================

        JButton button = new JButton("Test button");
        this.add(button);

        JButton button_2 = new JButton("Test button 2");
        this.add(button_2);

        refresh_jframe();
    }

    private void refresh_jframe() {
        this.setSize(399, 399);
    }
}
