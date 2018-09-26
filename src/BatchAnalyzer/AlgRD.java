/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BatchAnalyzer;

/**
 *
 * @author Administrator
 */
public class AlgRD {

    private  double K4;
    private  int RamUpPoint;
    private int Rd = 0;
    private int ramUp = 0;
    private MainSource ms;

    public AlgRD(MainSource ms, double k4, int ramUpPoint) {
        super();
        this.ms = ms;
        this.K4 = k4;
        this.RamUpPoint = ramUpPoint;
    }

    public void calc(){
        q1();
    }

    public int getRD(){
        return this.Rd;
    }

    public int getRamUp(){
        return this.ramUp;
    }

    private void q1() {
        if (ms.ram_act >= K4 * RamUpPoint || ms.ram_act - ms.mainList.get(ms.time_act - 1).getRampos() >= 0.07 * RamUpPoint) {
            s1();
        } else {
            s2();
        }
    }

    private void s1() {
        Rd = 0;
        ramUp++;
    }

    private void s2() {
        Rd++;
        ramUp = 0;
    }
}
