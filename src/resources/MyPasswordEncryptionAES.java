/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.print.Win32PrintService;

/**
 * Java AES 256 Encryption Decryption Example Encryption with possibility do
 * decrypt
 *
 * @author KOCMOC
 */
public class MyPasswordEncryptionAES {

    private static SecretKeySpec secretKeySpec;
    private static byte[] key;
    //
    public static String MC_REMOTE__CLIENT_ID__SK = "qazedcxswrfvbgtyhn/qazedcxswrfvbgtyhn";
    public static String MC_REMOTE__CLIENT_CERT__SK;
    //

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1"); // SHA-1
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKeySpec = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
    private static final Random RANDOM = new SecureRandom();
    
    public static String randomSecretKey() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String mcRemoteClientIdEncryption(String clientId){
        return encrypt(clientId, MC_REMOTE__CLIENT_ID__SK);
    }
    
    public static String mcRemoteClientCertPwEncryption(String certPw){
        return encrypt(certPw, MC_REMOTE__CLIENT_CERT__SK);
    }
    
    /**
     * Client id 901 (mixcont): x81rz17fTdPUOGUsTtwIKw==
     * Client id 10001(mixcont): mNRdeMGw8lnTOc9urpIvaQ==
     * Client id 10102 (lina): 6ng8kYyZx9KdK7XK4yARkg==
     *
     * @param args
     */
    public static void main(String[] args) {
        //
//        String secretKey_ = MC_REMOTE__CLIENT_ID__SK;
//        //
//        String originalString = "10102";
//        String encryptedString = MyPasswordEncryptionAES.encrypt(originalString, secretKey_);
//        String decryptedString = MyPasswordEncryptionAES.decrypt(encryptedString, secretKey_);
//
//        System.out.println(originalString);
//        System.out.println(encryptedString);
//        System.out.println(decryptedString);
        //
        //
//        System.out.println("" + mcRemoteClientIdEncryption("901"));
//
        System.out.println("" + randomSecretKey());
    }
}
