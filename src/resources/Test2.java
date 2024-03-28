/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.File;

/**
 *
 * @author KOCMOC
 */
public class Test2 {

    public static void main(String[] args) {
        String fileName = "mcbrowser.setting.properties";
        
        check_if_specs_file_exist("mcbrowser.setting.properties");
    }
    
    
    public static Object check_if_specs_file_exist(String path) {
        //
        File f = new File(path);
        //
        String fileNameWithExt = f.getAbsoluteFile().getName();
        //
        String[] arr = fileNameWithExt.split("\\.");
        //
        String specFile = "";
        //
        if(arr.length == 2){
            specFile = f.getParent() + "\\" + arr[0] + "$." + arr[1];
        }else if(arr.length == 3){
            specFile = f.getParent() + "\\" + arr[0] + "." + arr[1] + "$." + arr[2];
        }
        //
        if (fileExist(specFile)) {
            return null;
        } else {
            return null;
        }
    }
    
     public static boolean fileExist(String path) {
        File f = new File(path);
        return f.exists();
    }
}
