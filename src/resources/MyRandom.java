/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.Random;

/**
 *
 * @author KOCMOC
 */
public class MyRandom {
    
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            rnd_d2(4, 1);
        }
        String x = "yyyy-MM-dd";
        byte arr[] = x.getBytes();
        System.out.println("" + new String(new byte[]{121,121,121,121,45,77,77,45,100,100}));

//        hours_to_milliseconds_converter(2);
//         hours_to_milliseconds_converter(4);
    }
    
    private static long hours_to_milliseconds_converter(int hours) {
        long x = hours * 3600000;
        System.out.println("x: " + x);
        return x;
    }
    
    private static int rnd_d2(int h, int l) {
        Random r = new Random();
        int result = r.nextInt(h - l) + l;
        System.out.println("rst: " + result);
        return result;
    }
    
    private static int rnd_demo() {
        Random r = new Random();
        int low = 10;
        int high = 100;
        int result = r.nextInt(high - low) + low;
        return result;
    }
    
}
