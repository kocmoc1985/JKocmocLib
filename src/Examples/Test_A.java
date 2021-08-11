/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

/**
 *
 * @author KOCMOC
 */
public class Test_A {

    public static void main(String[] args) {
        System.out.println("" + r("lafakturering.se"));
    }

    public static String r(String s) {
        return new StringBuilder(s).reverse().toString();
    }

}
