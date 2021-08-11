/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MyBinary {

    
    public static String reverseString(String toReverse){
        return new StringBuilder(toReverse).reverse().toString();
    }
    
    //s%=so&s%=rev&eurt=wpi&s%=tneilc&moc_ptth_=knil?php.xedni/moc.tnocxim.www//:ptth
   public static void main(String[] args) {
//       String reversed = reverseString("http://www.mixcont.com/index.php?link=_http_com&client=%s&ipw=true&ver=%s&os=%s");
//       String reversed = reverseString("mixcont.com");
//        System.out.println("" + reversed);
//        System.out.println("" + reverseString(reversed));
          System.out.println("" + string_to_binary("rada"));
    }
    
    /**
     * Omvandlar ett tal till Binär form
     *
     * @param decimal talet som ska omvandlas
     * @param value antal bitar
     * @return Sträng med binär värde av "decimal"
     */
    public static String decimal_to_binary(int decimal, int value) {
//        int value = 128;
        String res = "";
        if (decimal < 0 || decimal > 255) {
            return "--------";
        } else if (decimal > 0 && decimal < 255) {
            while (value > 0) {
                if (decimal >= value) {
                    res += "1";
                    decimal = decimal - value;
                } else {
                    res += "0";
                }
                value = value / 2;
            }
        }
        return res;

    }

    /**
     *
     * @param nr
     * @return
     */
    public static String decimal_to_binary(int nr) {
        return "" + Integer.toBinaryString(nr);
    }
    
   

    /**
     * Omvandlar Sträng i binär form till tal
     *
     * @param binary binary talet i Sträng format t.ex. "00011100"
     * @return returns integer value av den inmatede strängen
     */
    public static int binary_to_decimal(String binary) {
        int res2 = 0;
        int value = 1;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                res2 = res2 + value;
            }
            value = value * 2;
        }

        return res2;
    }

    /**
     * A 'a' char should have returned 65 a summ of values 01000001 (or 64 + 1)
     *
     * @param str String-objekt som innehåller de tecken som ska skrivas ut
     */
    public static void string_to_decimal(String str) {
        int i = 0;
        while (i <= str.length() - 1) {
            System.out.println(str.charAt(i) + "  " + (int) str.charAt(i));
            i++;
        }
    }

    public static int string_to_decimal_(String str) {
        return (int) str.charAt(0);
    }
    
   

    /**
     * String into binary
     *
     * @param s
     * @return
     */
    public static String string_to_binary(String s) {
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        System.out.println("'" + s + "' to binary: " + binary);
        return binary.toString();
    }

    /**
     * This one works, but only with medium large files around max 10mb
     *
     * @tags get object size, object size, getObjectSize()
     * @param o
     * @return
     * @deprecated
     */
    public static String object_size_auto(Object o) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(o);
        } catch (IOException ex) {
            Logger.getLogger(MyBinary.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte[] object_bytes = bos.toByteArray();

        return define_size(object_bytes.length);
    }

    private static String define_size(long speed_in_b) {
        if (speed_in_b > 1048576) {
            double rst_unrounded = (double) speed_in_b / 1048576;
            double rst_rounded = Double.parseDouble(String.format("%2.2f", rst_unrounded).replace(",", "."));
            return "" + rst_rounded + " mb";
        } else if (speed_in_b < 1000) {
            return +speed_in_b + " b";
        } else if (speed_in_b > 1000) {
            double rst_unrounded = (double) speed_in_b / 1024;
            return +Math.round(rst_unrounded) + " kb";
        } else {
            return "";
        }
    }

    public static int check_sum_byte_array(byte[] barr) {
        int summ = 0;
        for (int i = 0; i < barr.length; i++) {
            summ += barr[i];
        }
        return summ;
    }

    public static String convert_byteArray_to_string() {
        byte[] byteArray = new byte[]{87, 79, 87, 46, 46, 46};
        String value = new String(byteArray);
        return value;
    }

    /**
     * @tags object to input stream
     * @param buf
     * @return
     */
    public static InputStream get_input_stream_from_object(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    /**
     * The best one! VERY IMPORTANT!!!!!!!!!!!!!!!
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static byte[] file_to_byte_array(String path) throws FileNotFoundException, IOException {
        byte[] content;
        FileInputStream p = new FileInputStream(path);
        content = new byte[p.available()];
        p.read(content);
        p.close();
        return content;
    }

    /**
     *
     * @param byte_array
     * @param date_or_path
     * @param extension t.ex ".jar"
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static File byte_array_to_file(byte[] byte_array, String date_or_path, String extension) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(date_or_path + extension);
        fos.write(byte_array);
        return new File(date_or_path + extension);
    }

    public static byte[] object_to_byte_array(Object o) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(o);
        byte[] object_bytes = bos.toByteArray();
        return object_bytes;
    }

    public static Object byte_array_to_object(byte[] barr) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(barr);
        ObjectInput in = new ObjectInputStream(bis);
        Object o = in.readObject();
        return o;
    }

   
//==============================================================================
    public static Object fileToObjectSecured(String fileName) {
        try {
            Object obj = fileToObject(fileName);
            return obj;
        } catch (Exception ex) {
            return null;
        }
    }

    private static void objectToFile(String path, Object obj) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        } catch (Exception ex) {
            Logger.getLogger(MyBinary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private static Object fileToObject(String path) throws IOException, ClassNotFoundException {
        FileInputStream fas = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fas);
        Object obj = ois.readObject();
        return obj;
    }
    //==============================================================================
}
