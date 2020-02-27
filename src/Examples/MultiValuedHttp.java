/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

/**
 *
 * @author MCREMOTE
 */
public class MultiValuedHttp {

    public static void main(String[] args) {
        String x = "###val_a:100;val_b:200###";
        String[] arr = x.split("###");
        System.out.println("");
        String[] arr_b = arr[1].split(";");
        String[]arr_rst_a = arr_b[0].split(":");
        String[]arr_rst_b = arr_b[1].split(":");
        String val_A = arr_rst_a[1];
        String val_B = arr_rst_b[1];
        DoubleVal doubleVal = new DoubleVal(val_A, val_B);
        System.out.println("" + doubleVal);
    }
    

    public static  class DoubleVal {

        private final String val_a;
        private final String val_b;

        public DoubleVal() {
            this.val_a = null;
            this.val_b = null;
        }
        
        

        public DoubleVal(String val_a, String val_b) {
            this.val_a = val_a;
            this.val_b = val_b;
        }

        public String getVal_a() {
            return val_a;
        }

        public String getVal_b() {
            return val_b;
        }

        @Override
        public String toString() {
            return val_a + " / " + val_b;
        }
        
        

    }
}
