/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author KOCMOC
 */
public class MCRemoteExtractFtp {
    
    
    
    public static void main(String[] args) {
        //vxsAZ1RKBqCTea4Bilr7hgJKqNR+NSW4ugR1rDTiGXvQ
//        extractFtp("vxsAZ1RKBqCTea4Bilr7hgJKqNR+NSW4ugR1rDTiGXvQ");
        System.out.println("" + extractFtp_b("vxsAZ1RKBqCTea4Bilr7hgJKqNR+NSW4ugR1rDTiGXvQ"));
    }
    
     public static String extractFtp_b(String encodedPassAndPassKey){
         return decryptAes256(encodedPassAndPassKey.substring(0, 22) + "==", encodedPassAndPassKey.substring(22,44) + "==");
     }
    
    public static String extractFtp(String encodedPassAndPassKey){
        String pass = encodedPassAndPassKey.substring(0, 22) + "==";
        String key = encodedPassAndPassKey.substring(22,44) + "==";
        System.out.println("PASS: "+ pass + " / " + pass.length());
        System.out.println("KEY: "+ key + " / " + key.length());
        System.out.println("RESTORED FTP PASS: " + decryptAes256(pass, key));
        return "";
    }
    
    //==========================================================================
    private static SecretKeySpec secretKey;
    private static byte[] key;

    private static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            Logger.getLogger(MCRemoteExtractFtp.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static String decryptAes256(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
            Logger.getLogger(MCRemoteExtractFtp.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
