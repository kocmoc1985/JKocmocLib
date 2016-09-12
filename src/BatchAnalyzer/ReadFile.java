/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BatchAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ReadFile {

    private static int torq_av_sec_back = 7;

    public static ArrayList<SWOS> readFileToArrayList(String filename) {
        int counter = 0;
        double mSum = 0;
        double eSum = 0;

        ArrayList<SWOS> list = new ArrayList<SWOS>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/" + filename + "." + "skv"));//("C:/"+filename +"."+"skv")

            String[] parts;

            SWOS read = null;

            String rs = br.readLine();

            while (rs != null) {
                double torqx = 0;
                double torqAv = 0;
                if (counter > 1) {
                    mSum += list.get(counter - 1).getTorque();
                    eSum += (list.get(counter - 1).getTorque()) * list.get(counter - 1).getSpeed();
                }

                if (counter > 7) {
                    for (int i = 0; i < torq_av_sec_back; i++) {
                        torqx += list.get(counter - (i + 1)).getTorque();
                    }
                    torqAv = torqx / torq_av_sec_back;
                }

                parts = rs.split(";");

                read = new SWOS(Double.parseDouble(
                        parts[0]), // time
                        Double.parseDouble(parts[1]), // torq
                        Double.parseDouble(parts[2]), // temp
                        Double.parseDouble(parts[4]), // rampos
                        Double.parseDouble(parts[8]), // energy
                        Double.parseDouble(parts[3]), // speed
                        mSum,
                        eSum,
                        Integer.parseInt(parts[9]), //ramup
                        Integer.parseInt(parts[10]),//totAdd
                        Integer.parseInt(parts[11]),
                        torqAv);//disch

                list.add(read);

                rs = br.readLine();

                counter++;
            }

        } catch (IOException ex) {
            System.out.println("" + ex);

        }

        return list;
    }
}
