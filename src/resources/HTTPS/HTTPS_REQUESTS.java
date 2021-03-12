/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.HTTPS;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author KOCMOC
 */
public class HTTPS_REQUESTS {

    /**
     * [2021-03-12]
     * This one is from BuhInvoice/LAFakturering
     * 100% Verified
     * @param url_
     * @return
     * @throws Exception
     */
    public static synchronized String https_get_content_post(String url_) throws Exception {
        //
        String urlParameters = url_.split("\\?")[1];
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = url_.split("\\?")[0];
        //
        URL url = new URL(request);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        //
        conn.setDoOutput(true);
        //
        ((HttpsURLConnection) conn).setInstanceFollowRedirects(false);
        ((HttpsURLConnection) conn).setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        //
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }
        //
        InputStream ins = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins, "UTF-8");
        BufferedReader in = new BufferedReader(isr);
        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null) {
            result += inputLine;
        }
        //
        System.out.println("BYTES TOTAl: " + result.getBytes().length + "   URL: " + url_.toString());
        //
        String[] arr = result.split("###");
        //
        if (arr.length == 0) {
            System.out.println("HTTP REQ FAILED");
            return "";
        }
        //
        String temp = arr[1];
        String value = temp.split(":")[1];
        //
        System.out.println("HTTP REQ VAL: " + value);
        //
        //
        //OBS! OBS! [2020-08-03] Without "StringEscapeUtils.unescapeJava(value)" i am getting
        // "\u00e5" instead of "å", so what unescaping dose is that it removes one the "\"
        // because an unescaped string which i recieve looks like "\\u00e5" indeed 
        return StringEscapeUtils.unescapeJava(value);//[#ESCAPE#]
        //
    }
    
    public static boolean RDPCOMM_HTTPS = true;
    
    /**
     * [2021-03-12]
     * This one is taken from the RDPCommander / NetProcMonitor project
     * 100% Verified
     * @param url_
     * @return
     * @throws Exception 
     */
     public static synchronized String http_request(String url_) throws Exception {
        //
        if(RDPCOMM_HTTPS){ // GP.RDPCOMM_HTTPS
            return https_request(url_); // HERE IT'S CALLING FOR HTTPS
        }
        //
        System.out.println("url: " + url_);
        //
        URL url = new URL(url_);
        //
        URLConnection conn = url.openConnection();
        ((HttpURLConnection) conn).setRequestMethod("GET");
        //
        conn.setReadTimeout(10000); // [2020-04-24]
        //
        InputStream ins = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in = new BufferedReader(isr);
        String inputLine;
        String result = "";
        //
        while ((inputLine = in.readLine()) != null) {
            result += inputLine;
        }
        //
        String[] arr = result.split("###");
        //
        if (arr.length == 0) {
            System.out.println("HTTP REQ FAILED");
            return "failed";
        }
        //
        String temp = arr[1];
        String value = temp.split(":")[1];
        System.out.println("HTTP REQ VAL: " + value);
        return value;
        //
    }
    
    /**
     * [2021-03-12]
     * [#RDPCOMM-HTTPS#]
     * @param url_
     * @return
     * @throws Exception 
     */
    private static synchronized String https_request(String url_) throws Exception {
        //
        url_ = url_.replaceFirst("http", "https"); // OBS!! PAY ATTENTION to "replaceFirst()"
        //
        System.out.println("url: " + url_);
        //
        URL url = new URL(url_);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        //
        conn.setDoOutput(true);
        //
        ((HttpsURLConnection) conn).setInstanceFollowRedirects(false);
        ((HttpsURLConnection) conn).setRequestMethod("GET");
        //
        conn.setReadTimeout(10000); // [2020-04-24]
        //
        InputStream ins = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in = new BufferedReader(isr);
        String inputLine;
        String result = "";
        //
        while ((inputLine = in.readLine()) != null) {
            result += inputLine;
        }
        //
        String[] arr = result.split("###");
        //
        if (arr.length == 0) {
            System.out.println("HTTP REQ FAILED");
            return "failed";
        }
        //
        String temp = arr[1];
        String value = temp.split(":")[1];
        System.out.println("HTTP REQ VAL: " + value);
        return value;
        //
    }
    

}
