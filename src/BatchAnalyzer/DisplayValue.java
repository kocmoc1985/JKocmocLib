/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BatchAnalyzer;

/**
 *
 * @author Administrator
 */
public class DisplayValue {
private double value;
private String value_name;

public DisplayValue(double value,String valueName){
    super();
    this.value = value;
    this.value_name = valueName;
}

public double getValue(){
    return this.value;

}

public String getName(){
    return this.value_name;
}

}
