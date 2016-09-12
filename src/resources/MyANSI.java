/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.charset.Charset;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Administrator
 */
public class MyANSI {

    private static final int ATTR_NORMAL = 0;
    private static final int ATTR_BOLD = 1;
    private static final int ATTR_DIM = 2;
    private static final int ATTR_UNDERLINE = 3;
    private static final int ATTR_BLINK = 5;
    private static final int ATTR_REVERSE = 7;
    private static final int ATTR_HIDDEN = 8;
    private static final int FG_BLACK = 30;
    private static final int FG_RED = 31;
    private static final int FG_GREEN = 32;
    private static final int FG_YELLOW = 33;
    private static final int FG_BLUE = 34;
    private static final int FG_MAGENTA = 35;
    private static final int FG_CYAN = 36;
    private static final int FG_WHITE = 37;
    private static final int BG_BLACK = 40;
    private static final int BG_RED = 41;
    private static final int BG_GREEN = 42;
    private static final int BG_YELLOW = 44;
    private static final int BG_BLUE = 44;
    private static final int BG_MAGENTA = 45;
    private static final int BG_CYAN = 46;
    private static final int BG_WHITE = 47;
    private static final String PREFIX = "\u001b["; // ESC key
    private static final String SUFFIX = "m";
    private static final char SEPARATOR = ';';
    private static final String END_STR = PREFIX + SUFFIX;
    private static final String STD_MSG_F = PREFIX + ATTR_DIM + SEPARATOR + FG_BLACK + SUFFIX;
    private static final String ERR_MSG_F = PREFIX + ATTR_BLINK + SEPARATOR + FG_RED + SUFFIX;
    private static final String INFO_MSG_F = PREFIX + ATTR_NORMAL + SEPARATOR + FG_GREEN + SUFFIX;
    private static final String STATUS_MSG_F = PREFIX + ATTR_DIM + SEPARATOR + FG_MAGENTA + SUFFIX;
    private static final String PROMPT_MSG_F = PREFIX + ATTR_BOLD + SEPARATOR + FG_BLUE + SUFFIX;
    private static final String WELCOME_MSG_F = PREFIX + ATTR_DIM + SEPARATOR + FG_BLUE + SUFFIX;
    //===========================================================================
    private static final int STD_MSG_TYPE = 0;
    private static final int ERROR_MSG_TYPE = 1;
    private static final int INFO_MSG_TYPE = 2;
    private static final int STATUS_MSG_TYPE = 3;
    private static final int PROMPT_MSG_TYPE = 4;
    private static final int WELCOME_MESSAGE_TYPE = 5;
    //===========================================================================
    //===========================================================================
    //===========================================================================
    public final static String EXAMPLE_1 = getFormatedMessage("MCBarcodeReader \n\rall rights reserved\n\rF4 for help", WELCOME_MESSAGE_TYPE);
    public final static String EXAMPLE_2 = getFormatedMessage("Scan complete", INFO_MSG_TYPE);
    public final static String EXAMPLE_3 = getFormatedMessage("Connection with DB failed", ERROR_MSG_TYPE);
    public final static String EXAMPLE_4 = "\r_________________________\n";
    //===========================================================================
    //This command makes the ANSI terminal to anwer, the answer contain the attribute/id of the device
    public final static String DEVICE_ATTRIBUTE_REQUEST = "\u001b[c";
    //===========================================================================
    public static final String ESC_KEY = "\u001b[";
    public final static String ENTER_KEY = "\r";
    public final static String DEL_KEY = "\b";
    //===========================================================================
    public final static String F1 = "\u001bOP";
    public final static String F2 = "\u001bOQ";
    public final static String F5 = "\u001b[16~";

    
    public static void main(String[] args) {
        String message = "MixCont";
        final StringBuffer msg = new StringBuffer(message);
        msg.insert(0, PROMPT_MSG_F);
        msg.append(END_STR);
        
        TextArea jta = new TextArea(msg.toString());
        JOptionPane.showMessageDialog(null, jta);
        
        System.out.println("" + msg.toString());
        //========================================================
//        KeyCode code;
//        code = new KeyCode();
        //=========================================================
//        System.out.println("" + GET_BACKSPACE_CHAR());
//        System.out.println("");
//        System.out.println("" + codeToString(new Byte("27")));
    }
    /**
     *
     * @param message
     * @param messageType
     * @return
     */
    public static String getFormatedMessage(String message, int messageType) {
        final StringBuffer msg = new StringBuffer(message);
        switch (messageType) {
            case STD_MSG_TYPE:
                msg.insert(0, STD_MSG_F);
                msg.append(END_STR);
                break;
            case ERROR_MSG_TYPE:
                msg.insert(0, ERR_MSG_F);
                msg.append(END_STR);
                break;
            case INFO_MSG_TYPE:
                msg.insert(0, INFO_MSG_F);
                msg.append(END_STR);
                break;
            case STATUS_MSG_TYPE:
                msg.insert(0, STATUS_MSG_F);
                msg.append(END_STR);
                break;
            case PROMPT_MSG_TYPE:
                msg.insert(0, PROMPT_MSG_F);
                msg.append(END_STR);
                break;
            case WELCOME_MESSAGE_TYPE:
                msg.insert(0, WELCOME_MSG_F);
                msg.append(END_STR);
                break;
            default: // Use STD_MSG_TYPE as default
                msg.insert(0, STD_MSG_F);
                msg.append(END_STR);
                break;
        }
        return msg.toString();
    }
    //===========================================================================
    private static Charset charset_g = Charset.forName("ASCII");
    private static Charset charset = Charset.forName("windows-1252");

    /**
     * Very useful
     *
     * @param msg
     * @return
     */
    private static String buildBasicAnsiMessage(String msg) {
        return "\r" + msg + "\n\r";
    }

    private static String buildMessageAdvanced() {
        String specialChar1 = getSpecialString(255);
        String specialChar2 = getSpecialString(239);
        String mainPrefix = "\u001b["; //basic escape sequence for ANSI
        String sufix = "\u001b[li";
        String prefix = "li";
        String msg_temp = "OK";
        String msg = new String(msg_temp.getBytes(), charset);
        StringBuilder builder = new StringBuilder(msg);
        builder.insert(0, prefix);
        builder.insert(0, mainPrefix);
//        builder.append(specialChar1);
//        builder.append(specialChar2);
//        builder.append(sufix);
        builder.append("\r");

        byte[] arr = builder.toString().getBytes();
        return builder.toString();
    }

    private static String getSpecialString(int byte_) {
        byte b = byte0XFF(byte_);
        byte[] arr_1 = {b};
        String str = new String(arr_1);
        return str;
    }

    private static byte byte0XFF(int b) {
        return (byte) (b & 0xff);
    }

    public static String getSpecialString() {
        byte b = byte0XFF();
        byte[] arr_1 = {b};
        String str = new String(arr_1);
        return str;
    }

    public static byte byte0XFF() {
        return (byte) (255 & 0xff);
    }

    public static void getEscapeSeqCodeExample() {
        String s = "\u001b"; // Esc, Byte = 27
        byte[] arr = s.getBytes();
        System.out.println("" + arr[0]);
    }

    /**
     * SUPER IMPORTANT To send "escape char" -> "\u001b" over TCP can be a
     * problem because when the server recieves it, it appends one more "\" and
     * escape char is not recognized
     *
     * @return
     */
    public static String GET_ESCAPE_CHAR() {
        return codeToString(new Byte("27"));
    }

    /**
     * The char is "\r" The char "\n" has code = 10
     *
     * @return
     */
    public static String GET_ENTER_CHAR() {
        return codeToString(new Byte("13"));
    }

    /**
     * the char is "\b"
     *
     * @return
     */
    public static String GET_BACKSPACE_CHAR() {
        return codeToString(new Byte("8"));
    }

    public static String codeToString(byte code) {
        byte[] arr = {code};
        return new String(arr, charset_g);
    }

    public static int stringToCode(String str) {
        return (int) str.charAt(0);
    }

    /**
     * Same as stringToCode
     *
     * @param str
     * @return
     */
    public static int stringToCode2(String str) {
        byte[] arr = str.getBytes();
        return arr[0];
    }

    
    public static int stringToHashCode(String str) {
        return Math.abs(str.hashCode());
    }

    

    /**
     * USE This class to find out codes and other
     */
    static class KeyCode extends JFrame implements KeyListener {

        public KeyCode() {
            this.addKeyListener(this);
            this.setSize(200, 200);
            this.setVisible(true);
        }

        @Override
        public void keyTyped(KeyEvent ke) {
            //
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            System.out.println("Key code = " + ke.getKeyCode());
            System.out.println("Key char = " + ke.getKeyChar());
            System.out.println("Key location = " + ke.getKeyLocation());
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            //
        }
    }
}
