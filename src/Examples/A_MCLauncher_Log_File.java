/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOCMOC
 */
public class A_MCLauncher_Log_File {
    
    public static void main(String[] args) {
        extract_ip_from_mclauncher_log__trell("mclauncher.log");
    }

    public static void extract_ip_from_mclauncher_log__trell(String filename) {
//        ArrayList<String> list = new ArrayList<String>();
        HashSet<String> list = new HashSet<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String[] parts;
            String rs = br.readLine();
            while (rs != null) {
                //
                parts = rs.split("     ");
                String recipe = parts[2].substring(0, 16).trim();
                //
                if (recipe.contains("72.29.140.131") || recipe.contains("172.29.140.128") || recipe.contains("172.29.149.38")) {
                    System.out.println("NOTHING");
                } else {
                   list.add(recipe);
                }
                //
                rs = br.readLine();
                //
            }

        } catch (IOException e) {
            Logger.getLogger(A_Check_If_Recipe_changed.class.getName()).log(Level.SEVERE, null, e);
        }
        //
        String[] arr = list.toArray(new String[list.size()]);
        int i = 1;
        //
        for (String string : arr) {
            System.out.println("(" + i + ")" + string);
            i++;
        }
        //
    }
}
