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
    public static String MC_REMOTE__CLIENT_ID__SK__32__BIT = "5kgkD9PEUO7VAtmf0SCcHaC6IxKlqeJuMqDfdD5iyCk=";//qazedcxswrfvbgtyhn/qazedcxswrfvbgtyhn
    public static String MC_REMOTE__CLIENT_CERT__SK__32__BIT = "8GrDcxM+2c4XwG2wUvLTl4ang4uNDcLJd1LkvZT4B4Q=";
    public static String MC_REMOTE__FTP__16__BIT = "JKqNR+NSW4ugR1rDTiGXvQ==";
    //

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1"); // SHA-1 // OBS! THINK TWICE BEFORE CHANGING IT
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
        byte[] salt = new byte[32];//16
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String mcRemoteClientIdEncryption(String clientId) {
        return encrypt(clientId, MC_REMOTE__CLIENT_ID__SK__32__BIT);
    }

    public static String mcRemoteClientCertPwEncryption(String certPw) {
        return encrypt(certPw, MC_REMOTE__CLIENT_CERT__SK__32__BIT);
    }

    public static String mcRemoteClientFTPEncryption(String certPw) {
        return encrypt(certPw, MC_REMOTE__FTP__16__BIT);
    }

    /**
     * Client id 901 (mixcont): boAub/3qEhun2jS3f3Zytw== Client id
     * 10001(mixcont): KhB+90KIw6ta79iz4HkZtA== Client id 10102 (lina):
     * kwPYVSck+YmDEvzZIT8WsA==
     *
     * @param args
     */
    public static void main(String[] args) {
        //
        //A.How to do reminder, generate random "PASS SECRET" with : randomSecretKey() -> set 16bit or 32bit in the method
        //B.Generate password with the new "PASS SECRET"
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
        //
        //
        //
//        System.out.println("" + mcRemoteClientIdEncryption("901"));
//        System.out.println("" + mcRemoteClientIdEncryption("10001"));
//        System.out.println("" + mcRemoteClientIdEncryption("10102"));
        //
        // FOR FTP: [vxsAZ1RKBqCTea4Bilr7hg==][JKqNR+NSW4ugR1rDTiGXvQ==] -> first pass, second passkey
        // For obsfuscation reason and to keep it 44char long skip sending "==" append them afterwards
        // System.out.println("" + mcRemoteClientFTPEncryption("MCpc1Service"));
        //
        //
//        System.out.println("" + randomSecretKey());
        //
        //
        System.out.println("" + mcRemoteClientCertPwEncryption("kocmoc"));
    }
}
