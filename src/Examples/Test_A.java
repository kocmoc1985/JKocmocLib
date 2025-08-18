/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author KOCMOC
 */
public class Test_A {

    public static int rn(int h, int l, String msg) {
        Random r = new Random();
        int result = r.nextInt(h - l) + l;
//            System.out.println("rst: " + result + " / " + msg);
        return result;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            // OBS! The first parameter is MAX the second is MIN
            System.out.println("" + rn(1620000, 660000, "A"));
        }

//        System.out.println("" + new String(new byte[]{112,114,111,112,101,114,116,105,101,115,47,112,95,99,95,109,97,105,110,46,112,114,111,112,101,114,116,105,101,115}));
//        System.out.println("" + new String(new byte[]{83,73,71,78,65,76,50,51}));
//        System.out.println("" + new String(new byte[]{50,48,48}));
//        System.out.println(""+new String(new byte[]{108,111,103,46,116,120,116,}));
    }

}
