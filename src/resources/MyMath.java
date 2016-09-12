/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class MyMath {

    public static void count_with_big_values_examle() {
        double x = 7098576 / 1048576;
        System.out.println("" + x);
        double y = (double) 7098576 / 1048576;
        System.out.println("" + y);
    }

    /**
     * For translates double into %. Example: 0.12 will return 12%
     *
     * @param decimal
     * @return
     */
    public static String decimal_to_percent(double decimal) {
        DecimalFormat df = new DecimalFormat("#%");
        return df.format(decimal);
    }

    /**
     * This function is optimal for rounding of a double
     *
     * @param number
     * @return
     */
    public static double roundingOfDoubleProperFunction(double number) {
        DecimalFormat twoDForm = new DecimalFormat("#.###");
        DecimalFormatSymbols s = DecimalFormatSymbols.getInstance();
        s.setDecimalSeparator('.');
        twoDForm.setDecimalFormatSymbols(s);
        return Double.valueOf(twoDForm.format(number));
    }

    /**
     * This one is almost the same as above but the DecimalFormatSymbol is
     * defined once in an other class and passed to this method as parameter
     *
     * @param rst
     * @param s
     * @return
     */
    public static double roundingOfDoubleProperFunctionShort(double rst, DecimalFormatSymbols s) {
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        twoDForm.setDecimalFormatSymbols(s);
        return Double.valueOf(twoDForm.format(rst));
    }

    /**
     * @Tags: round, rounding, avrundning,avrunda,okruglenije
     * @param rst
     * @return
     */
    public static double rounding_of_double(double rst) {
        return Double.parseDouble(String.format("%2.2f", rst).replace(",", "."));
    }

   

    /**
     * Rounding of a Double to get a Shorter String for printing
     *
     * @param rst
     * @return String
     */
    public static String rounding_of_double_to_4_signs_after_comma(double rst) {
        return String.format("%2.4f", rst);
    }

    /**
     * Adjustable rounding of Double
     *
     * @param rst = A value to be rounded
     * @param nr_signs_after_comma = How many signs it should be after comma
     * @return String
     */
    public static String rounding_adjustable_of_double(double rst, int nr_signs_after_comma) {
        String c = "" + nr_signs_after_comma;
        return String.format("%2." + c + "f", rst);
    }

    /**
     * Letar upp det minsta värdet
     *
     * @param array tar imot en doule[]array
     * @return det minsta värdet i arrayen
     */
    public static double findSmallestValue(double[] array) {
        double minst = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < minst) {
                minst = array[i];
            }
        }
        return minst;
    }

    /**
     * Skapar en double[]array med n antal positioner i intervalet min max
     *
     * @param n antalet positioner
     * @param min minsta slup värdet
     * @param max största slump värdet
     * @return den ifyllda arrayen
     */
    public static double[] randomDoubleArray(int n, double min, double max) {
        double[] array = new double[n];
        for (int i = 0; i < n; i++) {
            array[i] = (double) (Math.random() * max + min);
        }
        return array;
    }

    public static ArrayList<Double> randomDoubleArrayList(int n, double min, double max) {
        ArrayList<Double> list = new ArrayList<Double>();
        for (int i = 0; i < n; i++) {
            list.add((double) (Math.random() * max + min));
        }
        return list;
    }

    public static int[] randomIntArray(int n, double min, double max) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * max + min);
        }
        return array;
    }

    /**
     * Random value between 1 and 99999999
     *
     * @return String random value
     */
    public static String random() {
        int x = (int) ((Math.random() * 100) + 1);
        return "" + x;
    }
    
    

    /**
     * it should pick the number 1 with a probability of 75%
     *
     * @param probability
     * @return
     */
    public static int random_integer_with_given_probability() {
        Random rand = new Random();
        return (rand.nextInt(100) < 75) ? 1 : 0;
    }

    /**
     * Creates n number of random values within a given range (1 - max_value).
     * Array of random values *never* contains duplicate entries
     *
     * @param nr_of_rundom_values - the required number of rundom values within
     * the range
     * @param max_value - the max value constraints the range (from 1 to
     * max_value)
     * @return
     */
    public static int[] create_n_random_values_in_given_range(int nr_of_rundom_values, int max_value) {
        int[] arr = new int[nr_of_rundom_values];
        int last_inserted = 0;
        boolean fault = false;
        while (arr[nr_of_rundom_values - 1] == 0) {
            int x = (int) ((Math.random() * max_value) + 1);
            for (int i = 0; i < arr.length; i++) {

                if (x == arr[i] || x > max_value || x == 0) {
                    fault = true;
                    break;
                }
            }
            if (fault == false) {
                arr[last_inserted] = x;
                last_inserted++;
            }
            fault = false;
        }
        return arr;
    }

    /**
     * Example: parameter value = 0.144 the method will return 1000 Example2:
     * paramter value = 1.4 the method will return 10
     *
     * @param value
     * @return
     */
    public static int findMultiplyCoeff(double value) {
        int temp = 1;
        for (int i = 0; i < 10; i++) {
            if ((Integer.MAX_VALUE - value) % 1 != 0) {
                value *= 10;
                temp *= 10;
            } else {
                break;
            }
        }
        return temp;
    }

    /**
     * This method gears up a double value to integer Example = 0.07001 will
     * become 7001; 0.145 -> 145
     *
     * @param value
     * @return
     */
    public static int upgradeToWholeNumber(double value) {
        while (value % 1 != 0) {
            value *= 10;
        }
        return (int) value;
    }

    /**
     * This will return (2) if the param = (2.594646)
     *
     * @param value_to_convert
     * @return
     */
    public static int takeValueBeforeComma(double value_to_convert) {
        return (int) Math.abs(value_to_convert);
    }

    /**
     * If the value is 2.5789 it will return 5789
     *
     * @param value_to_convert
     * @return
     */
    public static int takeValueAfterComma(double value_to_convert) {
        String x = "" + value_to_convert;
        x = x.replace(".", ";");
        String[] c = x.split(";");
        int val = Integer.parseInt(c[1]);
        return val;
    }
}
