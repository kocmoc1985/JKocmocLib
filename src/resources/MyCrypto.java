/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOCMOC
 */
public class MyCrypto {

    /**
     * It's always more complicated when converting to string
     *
     * @deprecated
     * @param toHash
     * @return
     */
    public static String sha256_string(String toHash) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MyCrypto.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] hash = digest.digest(toHash.getBytes(StandardCharsets.UTF_8));
        return new String(hash);
    }

    public static byte[] sha256_byte(String toHash) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(toHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MyCrypto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("value: " + sha256_string("passworddddddddddddddddddddddddddddddddddddddddddddaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

    }
}
