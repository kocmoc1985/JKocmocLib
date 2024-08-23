/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import java.util.HashMap;

/**
 *
 * @author KOCMOC
 */
public class Test_A {

    public static void main(String[] args) {
//        System.out.println("" + b03(37, 99, 37)); //#TEST-BEST-OF-3#
        hashmap();
    }
    
    private static void hashmap(){
        HashMap<String, String>map = new HashMap<>();
        map.put("-/-123--/", "7");
        map.put("-/-123--/", "7");
        map.put("-/-123--/", "7");
        System.out.println("");
    }

    private static int b03(int a, int b, int c) {
        if (a >= b && a >= c) {
            System.out.println("A");
            return a;
        } else if (b >= a && b >= c) {
            System.out.println("B");
            return b;
        } else if (c >= a && c >= b) {
            System.out.println("C");
            return c;
        }else{
            System.out.println("ELSE");
            return a;
        } 
    }

    public static String r(String s) {
        return new StringBuilder(s).reverse().toString();
    }

}
