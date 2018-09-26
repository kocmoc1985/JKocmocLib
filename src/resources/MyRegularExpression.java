/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author KOCMOC
 */
public class MyRegularExpression {

    /**
     * Checks between 1 - 65535
     */
    public static void portPattern(){
        String x = "([1-9]|[1-8][0-9]|9[0-9]|[1-8][0-9]{2}|9[0-8][0-9]|99[0-9]|[1-8][0-9]{3}|9[0-8][0-9]{2}|99[0-8][0-9]|999[0-9]|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])";
    }
    /**
     * This one is very good but requires high capacity when used in a loop
     *
     * @param strToTest
     * @return
     */
    public static boolean isIpAdress(String strToTest) {
        String IPADDRESS_PATTERN =
                "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(strToTest);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * OBS! Case insensitive -> (?i)
     * @param strToTest 
     */
    public static void isOpcUaUrl(String strToTest) {
        String regex = "(?i)(opc\\.tcp)(:)(\\/)(\\/)((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])(:)(\\d+)";

    }

    public static void main(String[] args) {
        isOpcUaUrl(null);
    }

    /**
     * This one is not using "Reg Expressions" but might be rather effective
     *
     * @param strToTest
     * @return
     */
    public static boolean isIp(String strToTest) {
        String[] arr = strToTest.split("\\.");
        return arr.length == 4;
    }

    public static boolean checkIfDate(String value_yyyy_MM_dd) {
        if (value_yyyy_MM_dd.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return true;
        }
        return false;
    }

    /**
     * https://txt2re.com/ Checks against format 100.0 (5 chars)
     *
     * @return
     */
    public static boolean checkIfTemperature(String temp) {
        if (temp.matches("\\d{3}.\\d{1}")) {
            return true;
        }
        return false;
    }

    public static boolean checkIfbetween_0_to_100(String temp) {
        if (temp.matches("^[1-9][0-9]?$|^100$")) {
            return true;
        }
        return false;
    }

    /**
     * https://txt2re.com/
     *
     * @param temp
     * @return
     */
    public static boolean checkIfIngredient(String temp) {
        if (temp.matches("\\d{5}")) {
            return true;
        }
        return false;
    }

    /**
     * https://txt2re.com/
     *
     * Checks for 00-8-N752 two digits - one digit - digit or letter - 3 digits
     *
     * @param temp
     * @return
     */
    public static boolean checkIfRecipe(String temp) {
        if (temp.matches("(\\d{2})(-)(\\d{1})(-)(\\w{1})(\\d+)")) {
            return true;
        }
        return false;
    }
}
