/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOCMOC
 */
public class Testt {

    public static void main(String[] args) throws IOException {
//        run_with_cmd("mstsc", "");

        Collection c = new LinkedList();
        
        HashMap map = new HashMap();
       
        
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "@echo off\n"
                + "SETLOCAL ENABLEDELAYEDEXPANSION\n"
                + "SET LinkName=LAFakturering\n"
                + "SET Esc_LinkDest=%%HOMEDRIVE%%%%HOMEPATH%%\\Desktop\\!LinkName!.lnk\n"
                + "SET Esc_LinkTarget=%CD%\\la.jar\n"
                + "SET cSctVBS=CreateShortcut.vbs\n"
                + "SET LOG=\".\\%~N0_runtime.log\"\n"
                + "((\n"
                + "  echo Set oWS = WScript.CreateObject^(\"WScript.Shell\"^) \n"
                + "  echo sLinkFile = oWS.ExpandEnvironmentStrings^(\"!Esc_LinkDest!\"^)\n"
                + "  echo Set oLink = oWS.CreateShortcut^(sLinkFile^) \n"
                + "  echo oLink.TargetPath = oWS.ExpandEnvironmentStrings^(\"!Esc_LinkTarget!\"^)\n"
                + "  echo oLink.WorkingDirectory = \"%CD%\"\n"
                + "  echo oLink.Save\n"
                + ")1>!cSctVBS!\n"
                + "cscript //nologo .\\!cSctVBS!\n"
                + "DEL !cSctVBS! /f /q\n"
                + ")1>>!LOG! 2>>&1");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }

    public static void run_with_cmd(String cmd_application, String arg) {
        String[] commands = {"cmd", "/c", "start", "\"" + cmd_application + "\"", cmd_application, arg};
        ProcessBuilder builder = new ProcessBuilder(commands);
        try {
            builder.start();
        } catch (IOException ex) {
            Logger.getLogger(Testt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
