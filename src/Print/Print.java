/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Print;

import Barcode.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

/**
 *
 * @author Administrator
 */
public class Print {

    public static void print(String filename, String printerName) throws FileNotFoundException, PrintException, IOException {
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));
        PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.GIF, pras);
        if (pss.length == 0) {
            throw new RuntimeException("No printer services available.");
        }

        int printerIndex = -1;

        for (int i = 0; i < pss.length; i++) {

            if (check_string_contains(pss[i].getName(), printerName)) {
                printerIndex = i;
                System.out.println("printer found");
            }
//            System.out.println("" + pss[i].getName());
        }

        if (printerIndex != -1) {
            PrintService ps = pss[printerIndex];
            System.out.println("Printing to " + ps);

            DocPrintJob job = ps.createPrintJob();
            FileInputStream fin = new FileInputStream("out.PNG");
            Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.GIF, null);
            job.print(doc, pras);
            fin.close();
        }

    }

    /**
     *Checks wether the string contains the searched string
     * @param searchedString
     * @return
     */
    public static boolean check_string_contains(String str_to_analyze, String searchedString) {
        int index1 = 0;
        index1 = str_to_analyze.indexOf(searchedString);
        if (index1 != -1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            print("", "BRN_005F58");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrintException ex) {
            Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
