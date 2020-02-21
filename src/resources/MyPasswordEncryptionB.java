/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * A. Upon registration, the user sends her password to the server. The server
 * generates a random salt, and stores the salted hash and the salt.
 *
 * B. Upon login, the user again sends her password to the server. The server
 * retrieves the stored salt for that user, rehashes the password with it, and
 * compares it to the stored hash.
 *
 * @author KOCMOC
 */
public class MyPasswordEncryptionB {

    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    /**
     * static utility class
     */
    private MyPasswordEncryptionB() {
    }

    public static void main(String[] args) {
        //A. Upon registration, the user sends her password to the server. The server
        // generates a random salt, and stores the salted hash and the salt.
//        byte[] salt = getNextSalt();
//        //
//        try {
//            byteArrayToFile("salt/salt", salt);
//        } catch (IOException ex) {
//            Logger.getLogger(MyPasswordEncryptionB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //
//        char[] pass_ = "mcremote".toCharArray();
//        byte[] pass_salted = hash(pass_, salt);
//        //
//        try {
//            byteArrayToFile("pass_salted/pass_salted", pass_salted);
//        } catch (IOException ex) {
//            Logger.getLogger(MyPasswordEncryptionB.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //
        //
        //
        //
        char[] pass_provided_by_client = "mcremote".toCharArray();
        //
        try {
            byte[]loaded_salted_pass = filetoByteArray("pass_salted/pass_salted");
            byte[]loaded_salt = filetoByteArray("salt/salt");
            System.out.println("ALLOWED:" + isExpectedPassword(pass_provided_by_client, loaded_salt, loaded_salted_pass));
        } catch (Exception ex) {
            Logger.getLogger(MyPasswordEncryptionB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        
    }

    /**
     * Returns a random salt to be used to hash a password.
     *
     * @return a 16 bytes random salt
     */
    public static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     * Returns a salted and hashed password using the provided hash.<br>
     * Note - side effect: the password is destroyed (the char[] is filled with
     * zeros)
     *
     * @param password the password to be hashed
     * @param salt a 16 bytes salt, ideally obtained with the getNextSalt method
     *
     * @return the hashed password with a pinch of salt
     */
    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Returns true if the given password and salt match the hashed value, false
     * otherwise.<br>
     * Note - side effect: the password is destroyed (the char[] is filled with
     * zeros)
     *
     * @param password the password to check
     * @param salt the salt used to hash the password
     * @param expectedHash the expected hashed value of the password
     *
     * @return true if the given password and salt match the hashed value, false
     * otherwise
     */
    public static boolean isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash) {
        byte[] pwdHash = hash(password, salt);
        Arrays.fill(password, Character.MIN_VALUE);
        if (pwdHash.length != expectedHash.length) {
            return false;
        }
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a random password of a given length, using letters and digits.
     *
     * @param length the length of the password
     *
     * @return a random password
     */
    public static String generateRandomPassword(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int c = RANDOM.nextInt(62);
            if (c <= 9) {
                sb.append(String.valueOf(c));
            } else if (c < 36) {
                sb.append((char) ('a' + c - 10));
            } else {
                sb.append((char) ('A' + c - 36));
            }
        }
        return sb.toString();
    }

    //==========================================================================
    private static void byteArrayToFile(String path, byte[] arr) throws FileNotFoundException, IOException {
        File f2 = new File(path);
        OutputStream out;
        out = new FileOutputStream(f2);
        out.write(arr);
    }

    private static byte[] filetoByteArray(String path) throws FileNotFoundException, IOException {
        byte[] content;
        FileInputStream p = new FileInputStream(path);
        content = new byte[p.available()];
        p.read(content);
        p.close();
        return content;
    }
    
}
