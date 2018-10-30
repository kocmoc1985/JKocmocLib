/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author KOCMOC
 */
public class test_ {

    public static void main(String[] args) {
        String txt = "154:";

        String re1 = "(\\d)";	// Any Single Digit 1
        String re2 = "(\\d)";	// Any Single Digit 2
        String re3 = "(\\d)";	// Any Single Digit 3
        String re4 = "(:)";	// Any Single Character 1

        Pattern p = Pattern.compile(re1 + re2 + re3 + re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(txt);
        if (m.find()) {
            String d1 = m.group(1);
            String d2 = m.group(2);
            String d3 = m.group(3);
            String c1 = m.group(4);
            System.out.print("(" + d1.toString() + ")" + "(" + d2.toString() + ")" + "(" + d3.toString() + ")" + "(" + c1.toString() + ")" + "\n");
        }
    }
}
