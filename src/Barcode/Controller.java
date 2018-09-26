/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Barcode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;

/**
 *
 * @author Administrator
 */
public class Controller {
    
    
    public void go(String codeType,String code,String printer){
        if(codeType.toLowerCase().equals("code39")){
            try {
                Code39C.go(code); // create image
                Print.print("out.png", printer);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PrintException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(codeType.toLowerCase().equals("pdf417")){
             try {
                Pdf417C.go(code); // create image
                Print.print("out.png", printer);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PrintException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        Controller cont = new  Controller();
        cont.go("pdf417","45643123146","BRN_005F58");
    }
    
}
