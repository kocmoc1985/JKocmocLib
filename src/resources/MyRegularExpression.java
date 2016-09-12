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
     * This one is very good but requires high capacity
     * when used in a loop
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
     * This one is not using "Reg Expressions"
     * but might be rather effective
     * @param strToTest
     * @return 
     */
    public static boolean isIp(String strToTest){
        String[] arr = strToTest.split("\\.");
        return arr.length == 4;
    }
    
     public static boolean checkIfDate(String value_yyyy_MM_dd){
        if(value_yyyy_MM_dd.matches("\\d{4}-\\d{2}-\\d{2}")){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("" + isIpAdress("10.87.0.256"));
    }
}
