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
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author KOCMOC
 */
public class HTTP_REQUESTS {
    
    /**
      * FULLY WORKING FROM RDPCOMMANDER PROJ
      * [2021-03-12]
      * @param url_
      * @return
      * @throws Exception 
      */
     public static synchronized String http_request(String url_) throws Exception {
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
     * FULLY WORKING FROM THE BUHINVOICE [2021-03-12]
     * @param url_
     * @return
     * @throws Exception 
     */
     public static synchronized String http_get_content_post(String url_) throws Exception {
        //
        String urlParameters = url_.split("\\?")[1];
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = url_.split("\\?")[0];
        //
        URL url = new URL(request);
        URLConnection conn = url.openConnection();
        //
        conn.setDoOutput(true);
        ((HttpURLConnection) conn).setInstanceFollowRedirects(false);
        ((HttpURLConnection) conn).setRequestMethod("POST");
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
     
    
     

    /**
     * Implemented [2020-06-02] Yes this one is working
     * FROM THE RDPCOMMANDER. FULLY WORKING.[2021-03-12]
     * 
     * @param url_
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String http_get_content_post_(String url_) throws Exception {
        //
        String urlParameters = url_.split("\\?")[1];
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String request = url_.split("\\?")[0];
        //
        URL url = new URL(request);
        URLConnection conn = url.openConnection();
        //
        conn.setDoOutput(true);
        ((HttpURLConnection) conn).setInstanceFollowRedirects(false);
        ((HttpURLConnection) conn).setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }
        //
        InputStream ins = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in = new BufferedReader(isr);
        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null) {
            result += inputLine;
        }

        String[] arr = result.split("###");
        //
        if (arr.length == 0) {
            System.out.println("HTTP REQ FAILED");
            return "";
        }
        //
        String temp = arr[1];
//        String param = temp.split(":")[0];
        String value = temp.split(":")[1];
        System.out.println("HTTP REQ VAL: " + value);
        return value;
    }
     
    
}
