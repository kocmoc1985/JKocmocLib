/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

/**
 *
 * @author KOCMOC
 */
public class CMD_UDP {
//    public static final String SALT = "#####_";

    public static final String SHUT_DOWN_PC = "8372285849568798778958067";

    public static void string_to_decimal(String str) {
        int i = 0;
        while (i <= str.length() - 1) {
            System.out.println(str.charAt(i) + "  " + (int) str.charAt(i));
            i++;
        }
    }

    public static void main(String[] args) {
        string_to_decimal("SHUT_DOWN_PC");
    }

}
