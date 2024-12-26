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

    public static final String RECIPE = "23-?-42-1 - 3";

    public static void main(String[] args) {
        int a = 1;
        double b = (Double)a;
    }

    private static String extractRecipeRaw(String recipeID) {
        String[] arr = recipeID.split(" ");
        System.out.println("recipe raw: " + arr[0]);
        return arr[0];
    }

    private static String extractRelease(String recipeID) {
        String temp = recipeID.replaceAll(" ", "");
        String[] arr = temp.split("-");
        String release = arr[arr.length - 1];
        System.out.println("release: " + release);
        return release;
    }

}
