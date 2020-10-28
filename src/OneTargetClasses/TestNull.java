/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OneTargetClasses;

/**
 *
 * @author KOCMOC
 */
public class TestNull {
    
    public static void main(String[] args) {
        String x = defineX();
        
        if(x.equals("a")){
            System.out.println("YEEE");
        }else{
            System.out.println("NEEE");
        }
        
    }
    
    public static String defineX(){
        return null;
    }
    
    
    
}
