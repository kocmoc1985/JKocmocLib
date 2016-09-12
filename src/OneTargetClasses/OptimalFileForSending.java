/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OneTargetClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is done to be used in connection
 * with file sending over the network, it must 
 * be noticed that the File must be turned into byte[]
 * in order to be sent over the network. 
 * It doesn't absolutely work with sending an instanse of 'File' Object or
 * it does but the only thing which you recive is the path, lastmodified and so
 */
public class OptimalFileForSending implements Serializable {

    private byte[] file_byte_arr;
    private String extension;
    private String path;
    private String file_name_with_extension;
    private long last_modified;

    /**
     * 
     * @param file
     * @param path_on_cl_side - the path where the file should be stored on the client side
     */
    public OptimalFileForSending(File file) {
        create(file);
        extractExtension(file);
    }

    /**
     * 
     * @param path_where_to_create 
     */
    public void createFileOnLocalMachine(String path_where_to_create) {
        try {
            byteArrToFile(path_where_to_create + "" + file_name_with_extension, file_byte_arr);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OptimalFileForSending.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OptimalFileForSending.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPath() {
        return this.path;
    }

    public String getExtension() {
        return this.extension;
    }

    public long getLastModified() {
        return this.last_modified;
    }
    
    public String getFileNameWithExtension(){
        return this.file_name_with_extension;
    }

    /**
     * Turns the file into byte[]array
     * to be able to send it over the network
     * @param f 
     */
    private void create(File f) {
        try {
            System.out.println("create_file" + f.getAbsolutePath());
            file_byte_arr = filetoByteArray(f.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OptimalFileForSending.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OptimalFileForSending.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Extracts the files extension and some other 
     * things.
     * @param file 
     */
    private void extractExtension(File file) {
        char[] arr = file.getPath().toCharArray();
        file.mkdirs();
        last_modified = file.lastModified();
        path = file.getPath();
        file_name_with_extension = file.getName();
//        System.out.println(""+ path);
        extension = "." + arr[arr.length - 3] + "" + arr[arr.length - 2] + "" + arr[arr.length - 1];
    }

    /**
     * Is used when sending the file, the file is then turned to 
     * a byte[]
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private byte[] filetoByteArray(String path) throws FileNotFoundException, IOException {
        byte[] content;
        FileInputStream p = new FileInputStream(path);
        content = new byte[p.available()];
        p.read(content);
        p.close();
        return content;
    }

    /**
     * Turns the byte array into file.
     * Is used when the file was recieved and 
     * must be extracted to the HD from this object
     * @param path
     * @param arr
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void byteArrToFile(String path, byte[] arr) throws FileNotFoundException, IOException {
        File f2 = new File(path);
        OutputStream out;
        out = new FileOutputStream(f2);
        out.write(arr);
    }
}
