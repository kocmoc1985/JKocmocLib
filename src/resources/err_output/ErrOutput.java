/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.err_output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOCMOC
 */
public class ErrOutput {

    public static void main(String[] args) {
        //This part of code is always placed in very begining of the  "main" method
        //======================================================================
        create_dir_if_missing("err_output");
        //======================
        String path = "err_output/err_" + get_date_time_for_err_output() + ".txt";
        boolean ERR_OUT_TO_FILE = true;
        if (ERR_OUT_TO_FILE) {
            try {
                PrintStream out = new PrintStream(new FileOutputStream(path));
                System.setErr(out);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ErrOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
//        ErrorOutPutShow eops = new ErrorOutPutShow(jTextArea1, path);

        //======================================================================
    }
    
    public static void err_output_to_file() {
        //Write error stream to a file
        create_dir_if_missing("err_output");
        try {
            String err_file = "err_" + get_date_time_for_err_output() + ".txt";
            String output_path = "err_output/" + err_file;

            PrintStream out = new PrintStream(new FileOutputStream(output_path));
            System.setErr(out);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ErrOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void create_dir_if_missing(String path_and_folder_name) {
        File f = new File(path_and_folder_name);
        if (f.exists() == false) {
            f.mkdir();
        }
    }

    public static String get_date_time_for_err_output() {
        DateFormat formatter = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
        Calendar calendar = Calendar.getInstance();
        return formatter.format(calendar.getTime());
    }
}
