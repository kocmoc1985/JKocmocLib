/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JEditorPane;
import javax.swing.text.rtf.RTFEditorKit;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author Administrator
 */
public class MyString {

    private static Charset charset_g = Charset.forName("ASCII");

    /**
     * SUPER IMPORTANT
     */
    public static void escapeAndUnescapeStringExamples() {
//        StringEscapeUtils.unescapeJava(value);

//    value = StringEscapeUtils.escapeJava(value);
    }

    public static void extractParametersFromUrl(String uri) {
        String[] arr = uri.split("\\?");
        System.out.println("" + arr[1]);
    }

    public static String enloseInMySqlSlashes(String str) {
        return "`" + str + "`";
    }

    public static String cubicMeter() {
        return "kg/m&#179";
    }

    public static String celcius() {
        return "\u00b0C\r";
    }

    /**
     * [2020-08-03] "å" Important! In fact when i am getting a String which
     * looks like "Motors\u00e5g" in output in fact it is like "Motors\\u00e5g"
     * and must be escaped to show the "å" character
     *
     * @return
     */
    public static String swedishO() {
        String x = "Motors\\u00e5g";
        return StringEscapeUtils.unescapeJava(x);
    }

   

    public static void replaceBrackets() {
        String x = "select * from Main where [Name]='Ihti'";
        x = x.replaceAll("\\[", "`");
        x = x.replaceAll("\\]", "`");
        System.out.println("" + x);
    }
    
    public static void replaceNewLine_slash_n__back() {
        String x = "#&@aaaa#&@bbbb#&@ccccc";
        x = x.replaceAll("#&@", "\n");
        System.out.println("" + x);
    }

    public static void replaceNewLine_slash_n() {
        String x = "\naaaa\nbbbb\nccccc";
        x = x.replaceAll("(\r\n|\n)", " ");
        System.out.println("" + x);
    }
    
     public static void main(String[] args) {
//        replaceNewLine_slash_n();
        replaceNewLine_slash_n__back();
        System.out.println("" + celcius());
        System.out.println("" + swedishO());
        System.out.println("" + StringEscapeUtils.unescapeJava(swedishO()));
    }

    /**
     *
     * @param fullTag - ns=2;s=_System.Data_Source.Siemens.ML6_M.Status
     * @return
     */
    public static String extractSignalNameFromTag_WH(String fullTag) {
        String arr[] = fullTag.split("\\.");
        String signal = arr[arr.length - 1];
//        System.out.println("Signal: " + signal);
        return signal;
    }

    public static boolean getIfStringContains(String toBeSearched, String toFind) {
        if (toBeSearched.regionMatches(0, toFind, 0, toFind.length())) {
            return true;
        } else {
            return false;
        }
    }

    public static String extractTableName(String q) {
        Pattern p = Pattern.compile("from\\s+(?:\\w+\\.)*(\\w+)($|\\s+[WHERE,JOIN,START\\s+WITH,ORDER\\s+BY,GROUP\\s+BY])", Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(q);
        while (m.find()) {
            return m.group(1);
        }
        return null;
    }

    /**
     * Good to remember
     *
     * @param strToSplit
     * @return
     */
    public static String[] splitStringWithPoint(String strToSplit) {
        return strToSplit.split("\\.");
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isDouble(Object obj) {
        if (obj instanceof String) {
            String val = (String) obj;
            //
            //
            try {
                Double.parseDouble(val);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    /**
     * Components as "JLabel" do support HTML
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String htmlFormatStringAsTable(String str1, String str2) {
        return "<html><table >"
                + "<tr>"
                + "<td style='width:200px'>" + str1 + "</td>"
                + "<td style='width:100px'>" + str2 + "</td>"
                + "</tr>"
                + "</table>"
                + "</html>";
    }

    /**
     * Format the String as table
     *
     * @tags String.format, format as table, string to table
     * @param file_name
     * @param size
     * @param date
     * @return
     */
    public static String string_to_table_view(String file_name, String size, String date) {
        String format = "%1$-20s %2$-7s %3$-10s \n";
        String arr[] = {file_name, size, date};
        return String.format(format, (Object[]) arr);
    }

    /**
     * Extract values from the result gained with "string_to_table_view"
     * function
     *
     * @param formated_string
     * @param file_attribute
     * @return
     */
    public static String get_attribute_formated_string(String formated_string, int file_attribute) {
        ArrayList<String> list = new ArrayList<String>();
        String[] arr = formated_string.split(" ");
        for (String string : arr) {
            if (string.isEmpty() == false) {
                list.add(string);
            }
        }
        return list.get(file_attribute).trim();
    }

    /**
     * This one is super good
     */
    public static void stringFormat() {
        String x = String.format("select * from %s where %s = %s", "batches", "id", "10");
        System.out.println("" + x);
    }

    public String stringFormat(String company, String ipLocal, String userName,
            String os, String ver, String launchedApp) {
        String format = "%1$-15s %2$-15s %3$-20s %4$-20s %5$-15s %6$-30s";
        String arr[] = {company, ipLocal, userName, os, ver, launchedApp};
        return String.format(format, (Object[]) arr);
    }

    /**
     * #RTF, .rtf , show rtf, show RTF, display RTF, display rtf
     */
    public static void showRTF() {
        //You need following components
        JEditorPane jep = new JEditorPane();
        jep.setEditorKit(new RTFEditorKit());
        //
        String rtf_text = "just..example...";
        //
        //Usefull trick to change text-size
        rtf_text = rtf_text.replace("fs16", "fs35");
    }

    /**
     * OBS! Take a look at MyANSI.java
     *
     * @param code
     * @return
     */
    public static String codeToString(byte code) {
        byte[] arr = {code};
        return new String(arr, charset_g);
    }

    public static String GET_SPACEBAR_CHAR() {
        return codeToString(new Byte("32"));
    }

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

    /**
     * Very important This gets the same as "keycode" so for "1" it returns 49
     * for F1(\u001bOP) returns 28476
     *
     * @param str
     * @return
     */
    public static int get_key_code_of_a_string(String str) {
        return str.hashCode();
    }

    /**
     * Encodes the string to the requested unicode
     *
     * @param str
     */
    public static String setEncoding(String str, String unicode) {
        //ASCII
        //UTF-8
        Charset charset = Charset.forName(unicode);
        return new String(str.getBytes(), charset);
    }

    /**
     *
     * @param find
     * @param string
     * @return
     */
    public static int count_occurrences(String find, String string) {
        int count = 0;
        int indexOf = 0;

        while (indexOf > -1) {
            indexOf = string.indexOf(find, indexOf + 1);
            if (indexOf > -1) {
                count++;
            }
        }
        return count;
    }

    public static boolean lastCharEquals(String str_to_chek, String equals) {
        int a = str_to_chek.length() - 1;
        int b = str_to_chek.length();
        String last_char = str_to_chek.substring(a, b);
        if (last_char.equals(equals)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean firstCharEquals(String str_to_chek, String equals) {
        String first_char = "" + str_to_chek.charAt(0);
        if (first_char.equals(equals)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getLastChar(String str) {
        int a = str.length() - 1;
        int b = str.length();
        return str.substring(a, b);
    }

    public static String getPrevLastChar(String str) {
        int a = str.length() - 2;
        int b = str.length() - 1;
        return str.substring(a, b);
    }

    public static String getStringMinusTwoLastChars(String str) {
        int a = 0;
        int b = str.length() - 2;
        return str.substring(a, b);
    }

    public static String delete_last_letter_in_string(String str) {
        int a = str.length() - 1;
        return str.substring(0, a);
    }

    public static String delete_last_letter_in_string(String str, String letter) {
        int a = str.length() - 1;
        //
        if (getLastChar(str).equals(letter)) {
            return str.substring(0, a);
        } else {
            return str;
        }
        //
    }

    public static String first_letter_to_upper_case(String str) {
        String b = "" + str.charAt(0);
        return str.replaceFirst(b, b.toUpperCase());
    }

    public static String create_string_from_byte_arr(byte[] in_arr) {
        return new String(in_arr);
    }
//

    public static String create_string_with_given_encoding(byte[] arr, String encoding) throws UnsupportedEncodingException {
        return new String(arr, encoding);
    }

    /**
     * This could be useful to achive a same input
     *
     * @param string_to_edit
     * @param new_separator
     * @return
     */
    public static String replace_separator(String string_to_edit, char new_separator) {
        string_to_edit = string_to_edit.replace('-', new_separator);
        string_to_edit = string_to_edit.replace('_', new_separator);
        string_to_edit = string_to_edit.replace(',', new_separator);
        string_to_edit = string_to_edit.replace(';', new_separator);
        string_to_edit = string_to_edit.replace(':', new_separator);
        string_to_edit = string_to_edit.replace('*', new_separator);
        string_to_edit = string_to_edit.replace('¤', new_separator);
        string_to_edit = string_to_edit.replace('&', new_separator);
        string_to_edit = string_to_edit.replace('/', new_separator);
        string_to_edit = string_to_edit.replace('#', new_separator);
        string_to_edit = string_to_edit.replace('@', new_separator);
        string_to_edit = string_to_edit.replace('(', new_separator);
        string_to_edit = string_to_edit.replace(')', new_separator);
        string_to_edit = string_to_edit.replace('=', new_separator);
        string_to_edit = string_to_edit.replace('+', new_separator);
        string_to_edit = string_to_edit.replace('$', new_separator);
        return string_to_edit;
    }

    /**
     * To be able to record a object to DB without getting the exception with
     * "escape"
     *
     * @return
     */
    public static String sql_string_escape(String str) {
        return org.apache.commons.lang.StringEscapeUtils.escapeSql(str);
    }

    public static String encode_into_hexadecimal_string(String in_str) throws UnsupportedEncodingException {
        return URLEncoder.encode(in_str, "UTF-8");
    }

    private static String _get(String act, String all) {
        all = all.replaceAll(act, "");
        String rst = act + "," + all;
        rst = rst.replaceAll(",,", ",");
        rst = delete_last_letter_in_string(rst, ",");
        System.out.println("rst: " + rst);
        return rst;
    }

//
}
