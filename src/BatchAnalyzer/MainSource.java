/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BatchAnalyzer;

import java.util.*;

/**
 *
 * @author KOCMOC
 */
public class MainSource {
    /////////////////////

    ArrayList<SWOS> mainList;
    private final int timeFrom = 1;//*** Duration of the last step
    private final int timeTo = 552;//**** set 1sec longer
    int time_act;
    double torque_act;
    double temp_act;
    double ram_act;
    double energy_act;
    double speed_act;
    double m_sum_act;
    double e_sum_act;
    int ramUp_dig_signal;
    int totAdd_dig_signal;
    int disch_dig_signal;
    double torqAv;
    //////////////////
    ///////////////////////////// Values belonging to AlgRD \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\+
    private int rd;
    private int ramUp;
    private final double k4 = 0.11;
    private final int ramUpPoint = 84;
    private final double ramB = 0.4;
    private final double ramA = 5;
    //////////////////////////////////////////////////////////////////////////////////////////////////
    AlgRD algRD = new AlgRD(this, k4, ramUpPoint);
    /////////////////////////////Temp Values\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private double DeltaTorq;
    private int timeMconstant;
    private double DeltaRamPos;
    private int timeRamStuck;
    private double DeltaRamPosSumRamStuck;
    private int z;
    private int EndRamStuck_z;
    private int StartRamStuck_z;
    private int d;
    private int timeRamStuckTot;

    //////////////////////////////////////////////////////////////////////////////////////////////////
    public MainSource() {
        super();
    }

    /**
     * 
     * @param sec_back
     * @return
     */
    public double torqueAvXsecBack(int sec_back) {
        return AlgDiff.torque_av_x_sec_back(mainList, sec_back, time_act);
    }

    public void readLines(String filename) {
        this.mainList = ReadFile.readFileToArrayList(filename);
    }

    public ArrayList<SWOS> getList() {
        return this.mainList;
    }

    private void assignValues(int sec) {
        this.time_act = (int) this.mainList.get(sec).getTime();
        this.e_sum_act = this.mainList.get(sec).geteSum();
        this.energy_act = this.mainList.get(sec).getEnergy();
        this.m_sum_act = this.mainList.get(sec).getmSum();
        this.ram_act = this.mainList.get(sec).getRampos();
        this.speed_act = this.mainList.get(sec).getSpeed();
        this.temp_act = this.mainList.get(sec).getTemp();
        this.torque_act = this.mainList.get(sec).getTorque();
        this.disch_dig_signal = this.mainList.get(sec).getDisch();
        this.ramUp_dig_signal = this.mainList.get(sec).getRamUp();
        this.totAdd_dig_signal = this.mainList.get(sec).gettotAdd();
        this.torqAv = this.mainList.get(sec).getTorqAv();
        algRD.calc();
        this.rd = algRD.getRD();
        this.ramUp = algRD.getRamUp();
    }

    /**
     * @param a
     * @param b
     * @param c
     * @param d
     */
    private void display(DisplayValue val1, DisplayValue val2, DisplayValue val3, DisplayValue val4, DisplayValue val5,
            DisplayValue val6,
            DisplayValue val7,
            DisplayValue val8,
            DisplayValue val9,
            Integer second) {
        DisplayPanel.list01.add("\n" + "sek " + second + "   " + val1.getName() + " = " + val1.getValue());
        DisplayPanel.list02.add("\n" + "sek " + second + "   " + val2.getName() + " = " + val2.getValue());
        DisplayPanel.list03.add("\n" + "sek " + second + "   " + val3.getName() + " = " + val3.getValue());
        DisplayPanel.list04.add("\n" + "sek " + second + "   " + val4.getName() + " = " + val4.getValue());
        DisplayPanel.list05.add("\n" + "sek " + second + "   " + val5.getName() + " = " + val5.getValue());
        DisplayPanel.list06.add("\n" + "sek " + second + "   " + val6.getName() + " = " + val6.getValue());
        DisplayPanel.list07.add("\n" + "sek " + second + "   " + val7.getName() + " = " + val7.getValue());
        DisplayPanel.list08.add("\n" + "sek " + second + "   " + val8.getName() + " = " + val8.getValue());
        DisplayPanel.list09.add("\n" + "sek " + second + "   " + val9.getName() + " = " + val9.getValue());
    }

    public void mainLoop() { // THIS is the main LOOP

        for (int i = timeFrom; i < mainList.size(); i++) {//Enter hear second from & second to !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
            assignValues(i);

            algor();


            //Values to display***********************************!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            display(new DisplayValue(15, "timeRamStuckTot"), // window 1
                    new DisplayValue(timeRamStuck, "timeRamStuck"), // w2
                    new DisplayValue(DeltaRamPosSumRamStuck, "DeltaRamPosSumRamStuck"),//w3
                    new DisplayValue(DeltaRamPos, "DeltaRamPos"),//w4
                    new DisplayValue(DeltaRamPos, "DeltaRamPos"),//w5
                    new DisplayValue(DeltaRamPos, "DeltaRamPos"),//w6
                    new DisplayValue(DeltaRamPos, "DeltaRamPos"),//w7
                    new DisplayValue(DeltaRamPos, "DeltaRamPos"),//w8
                    new DisplayValue(DeltaRamPos, "DeltaRamPos"),//w9
                    i);
        }//Main loop

    }

    /**
     * Write the first question or statement to enter
     */
    private void algor() {
        q01();
    }

    private void q01() {
        if (totAdd_dig_signal == 0) {
            s1();
        } else {
        }
    }

    private void s1() {
        DeltaTorq = torque_act - mainList.get(time_act - 1).getTorque();
        DeltaRamPos = ram_act - mainList.get(time_act - 1).getRampos();
        q1();
    }

    private void q1() {
        if (Math.abs(DeltaTorq) > 0.001) {
            s3();
        } else {
            s2();
        }
    }

    private void s2() {
        timeMconstant++;
        q2();
    }

    private void s3() {
        timeMconstant = 0;
        q2();
    }

    private void q2() {
        if (Math.abs(DeltaRamPos) < k4
                && Math.abs(DeltaRamPosSumRamStuck) <= k4
                && 0.7 * ramUpPoint > ram_act && ram_act > ramA
                && timeMconstant < 3) {
            s5();
        } else {
            s4();
        }
    }

    private void s4() {
        timeRamStuck = 0;
        DeltaRamPosSumRamStuck = 0;

    }

    private void s5() {
        timeRamStuck++;
        q3();
    }

    private void q3() {
        if (timeRamStuck == 12) {
            q4();
        } else {
            q5();
        }
    }

    private void q4() {
        if (z > 0 && (time_act - 12) - EndRamStuck_z < 10) {
            s7();
        } else {
            s6();
        }
    }

    private void s6() {
        z++;
        StartRamStuck_z = time_act - 12;
        d = 12;
        s8();
    }

    private void s7() {
        d = EndRamStuck_z - time_act;
        s8();
    }

    private void s8() {
        timeRamStuckTot = +d;
        q5();
    }

    private void q5() {
        if (StartRamStuck_z > 0 && timeRamStuck >= 10) {
            q6();
        } else {
        }
    }

    private void q6() {
        if (EndRamStuck_z == 0 || time_act - EndRamStuck_z <= 10) {
            s9();
        } else {
        }
    }

    private void s9() {
        EndRamStuck_z = time_act;
        timeRamStuckTot++;

    }
}
