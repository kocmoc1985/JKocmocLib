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
public class A_Check_If_Recipe_changed {

    public static void main(String[] args) {
        String recipe_act = "ACT_RECIPE";
        String recipe_prev = "PREV_RECIPE";
        int batch_act = 7;
        int batch_prev = 6;
        System.out.println("" + check_if_recipe_really_changed(false, recipe_act, recipe_prev, batch_act, batch_prev));
    }

    private static String check_if_recipe_really_changed(boolean recipe_changed, String recipe_act, String recipe_prev, int batch_act, int batch_prev) {
        if (recipe_changed && batch_act == (batch_prev + 1)) {
            return recipe_prev;
        } else {
            return recipe_act;
        }
    }

}
