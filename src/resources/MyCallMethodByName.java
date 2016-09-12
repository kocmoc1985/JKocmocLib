/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @tags store method in array, place method in array
 * @author Administrator
 */
public class MyCallMethodByName {
    
   public Object executeMethod(String method_to_execute) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
       Method method = this.getClass().getDeclaredMethod(method_to_execute);
       System.out.println("" +  method.invoke(this));
       return method.invoke(this);
   }
   
   /**
    * Must be public!!
    * @return 
    */
   public String getString(){
       return "Hej på dig";
   }
   
   /**
    * Must be public!!
    * @return 
    */
   public double getDouble(){
       return 2.55;
   }
   
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        MyCallMethodByName cmn = new MyCallMethodByName();
        cmn.executeMethod("getString");
        cmn.executeMethod("getDouble");
        
        String str = (String)cmn.executeMethod("getString");
        Double dbl = (Double)cmn.executeMethod("getDouble");
        
        System.out.println("================================================");
        System.out.println("" + str);
        System.out.println("" + dbl);
    }
    
}
