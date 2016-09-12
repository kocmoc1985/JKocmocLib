/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BatchAnalyzer;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class AlgDiff {

    /**
     * 
     * @param list
     * @param sec_back
     * @param time
     * @return
     */
    public static double torque_av_x_sec_back(ArrayList<SWOS> list, int sec_back, int time) {
        double torqx = 0;
        double torqAv = 0;

        if (time > sec_back + 2) {
            for (int i = 0; i < sec_back; i++) {
                torqx += list.get(time - (i + 1)).getTorque();
            }
            torqAv = torqx / sec_back;
        }
        return torqAv;
    }
}
