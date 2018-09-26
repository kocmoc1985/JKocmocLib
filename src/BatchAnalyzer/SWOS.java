    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BatchAnalyzer;

/**
 *
 * @author KOCMOC
 */
public class SWOS {

    private double time;
    private double torque;
    private double rampos;
    private double temp;
    private double energy;
    private double speed;
    private double mSUm;
    private double eSUm;
    private int ramUp;
    private int totAdd;
    private int disch;
    private double torqAv;

// torqdev is not read from the skv, it is calculated and then iserts in the array list
    public SWOS(double time,
            double torque,
            double temp,
            double rampos,
            double energy,
            double speed,
            double mSum,
            double eSum,
            int ramUp,
            int totAdd,
            int disch,
            double torqAv) {
        super();
        this.time = time;
        this.torque = torque;
        this.rampos = rampos;
        this.temp = temp;
        this.energy = energy;
        this.speed = speed;
        this.mSUm = mSum;
        this.eSUm = eSum;
        this.ramUp = ramUp;
        this.totAdd = totAdd;
        this.disch = disch;
        this.torqAv = torqAv;
    }

    public double getTorqAv() {
        return this.torqAv;
    }

    public int getRamUp() {
        return this.ramUp;
    }

    public int getDisch() {
        return this.disch;
    }

    public int gettotAdd() {
        return this.totAdd;
    }

    public double getTime() {
        return this.time;
    }

    public double getTorque() {
        return this.torque;
    }

    public double getRampos() {
        return this.rampos;
    }

    public double getTemp() {
        return this.temp;
    }

    public double getEnergy() {
        return this.energy;
    }

    public double getSpeed() {
        return this.speed;
    }

    public double getmSum() {
        return this.mSUm;
    }

    public double geteSum() {
        return this.eSUm;
    }
}
