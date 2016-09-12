/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BatchAnalyzer;

/**
 *
 * @author KOCMOC
 */
public class Run {
    
    public Run(){
        super();
    }

    public static void main(String[] args) {
        MainSource ms = new MainSource();
        new DisplayPanel().setVisible(true);
        new Gui(ms);
    }
}
// 0.5,141,160,20,9632452,390801,1980.5,140,82,42,10,471,532,60 ------------- 57697 b 245   --PTE  --- soft
// 0.5,141,141,20,7114616,296597.0,2466.4,121,56,20,10,316,352,60 -------------58324 b 272 -- PTE  --- very hard
