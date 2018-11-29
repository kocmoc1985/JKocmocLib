/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MyFile {

    public static String[] splitStringWithPoint(String strToSplit) {
        return strToSplit.split("\\.");
    }

    /**
     * THIS IS EXTREAMLY IMPORTANT WHEN WORKING WITH FILES
     */
    public static void replaceSlashesExample() {
        String path = "C:/src/JkocmocLib/test";
        System.out.println("path_before_replace = " + path);
        path = path.replace("/", "\\");
        System.out.println("path_after_replace = " + path);
    }

    private static void get_path_examples() {
        File f = new File("xml/testDir/testDir2/dd.xml"); //xml/testDir/testDir2/dd.xml
        System.out.println("getName() = " + f.getName());
        System.out.println("getPath() = " + f.getPath());
        System.out.println("getAbsolutePath() = " + f.getAbsolutePath());
        System.out.println("getParent() = " + f.getParent());
        System.out.println("getParentFile().getName() = " + f.getParentFile().getName());
        System.out.println("f.getAbsoluteFile().getName() = " + f.getAbsoluteFile().getName());
        try {
            System.out.println("getCanonicalPath() = " + f.getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(MyFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("");
    }

    public static void main(String[] args) {
//        replaceSlashesExample();
        get_path_examples();
//        find_file("c:/", "AbsoluteLayout.jar");
//        System.out.println("file = " + searched_file);
    }

    public static String getFileExtension(String fileName) {
        //
        int i = fileName.lastIndexOf('.');
        //
        if (i > 0) {
            return fileName.substring(i + 1);
        }
        //
        return null;
    }

    public static boolean get_if_file_exist(String path) {
        File f = new File(path);
        return f.exists();
    }

    public static boolean file_exists(File f) {
        if (f == null) {
            return false;
        } else if (f.exists() == false) {
            return false;
        } else {
            return true;
        }
    }

    public static File searched_file;
    public static boolean file_found = false;

    /**
     * This method recursively finds a file, you should call this method with
     * specifying the initial folder to look in like: find_file(String path,
     * String application_to_run_name)
     *
     * @param path
     * @param application_to_run_name
     * @return
     */
    public static void find_file(String path, String application_to_run_name) {
        if (file_found) {
            return; // this to break out from stack quickly
        }
        File[] f = new File(path).listFiles();

        for (File file : f) {
            if (file.isDirectory()) {
                find_file(file.getPath(), application_to_run_name);
            } else if (file.getName().toLowerCase().contains(application_to_run_name.toLowerCase())) {
                searched_file = file;
                file_found = true;
            }
        }
    }

    /**
     * VERY GOOD List files in current dir
     *
     * @tags: current dir, curr_dir, currentDir,list_files,list files
     */
    public static void listFilesCurrentDir() {
        File[] f = new File(".").listFiles();
        for (File file : f) {
            System.out.println("file = " + file.getName());
        }
    }

    /**
     * Lists files in the dir
     *
     * @param pathToDir - path to file or dir
     */
    public static void listFiles(String pathToDir) {
        //!!!
        // consider this path example : "lib/.." to look in the program folder!!!!
        File f = new File(pathToDir);//c:/tmp 
        File[] flist = f.listFiles();
        for (File file : flist) {
            System.out.println("" + file.getName());
        }
    }

    /**
     *
     * @return
     */
    public static String getParentDir() {
        File f = new File("xml/dd.xml");
        return f.getParentFile().getName();
    }

    public static void create_dir_if_missing(String path_and_folder_name) {
        File f = new File(path_and_folder_name);
        if (f.exists() == false) {
            f.mkdir();
        }
    }

    /**
     * @tags - createDir, create dir, create directory
     */
    public static void createDirectory() {
        File dir = new File("C:/FileIO/DemoDirectory");
        boolean isDirectoryCreated = dir.mkdir();
        if (isDirectoryCreated) {
            System.out.println("successfully");
        } else {
            System.out.println("not");
        }
    }

    public static String file_get_date_created(String path) throws IOException {
        Path p1 = Paths.get(path);
        BasicFileAttributes attr = Files.readAttributes(p1, BasicFileAttributes.class);
        return "" + attr.creationTime();
    }

    /**
     * Uses recursion
     *
     * @param path
     */
    public static void delete_files_and_folders(String path) {
        File f = new File(path);
        File[] f_arr = f.listFiles();

        for (File file : f_arr) {
            if (file.isDirectory()) {
                delete_files_and_folders(file.getPath());
                file.delete();//delete folder
            } else {
                file.delete();
            }
        }
    }

    /**
     * deletes all files in a folder
     *
     * @param pathToDir - path to file or dir
     */
    public static void deleteAllFilesInFolder(String pathToDir) {
        // consider this path example : "lib/.." to look in the program folder!!!!
        File f = new File(pathToDir);//c:/tmp 
        File[] flist = f.listFiles();
        for (File file : flist) {
//            System.out.println("delete: " + file.getName());
            file.delete();
        }
    }

    public static void rename_file(String old_file_name, String new_file_name) {
        File f = new File(old_file_name);
        f.renameTo(new File(new_file_name));
    }

    /**
     *
     * @param file_name
     */
    public static void delete_file(String file_name) {
        File f = new File(file_name);
        f.delete();
    }

    /**
     * Very Effective!! But! This method cannot copy a file when it's beeing
     * used by another program
     *
     * @param file_to_copy
     * @param duplicate_file_name
     * @throws IOException
     */
    public static void copyFile(String file_to_copy, String duplicate_file_name) throws IOException {
        byte[] b_arr = filetoByteArray(file_to_copy);
        byteArrayToFile(duplicate_file_name, b_arr);
    }

    /**
     * The best method for copying files, it is now very fast to! But!!! This
     * method can copy a file which is beeing used by another program. Copy
     * files
     *
     * @param file_to_copy
     * @param name_of_duplicate
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copy_file(String file_to_copy, String name_of_duplicate) throws FileNotFoundException, IOException {
        File inputFile = new File(file_to_copy);
        File outputFile = new File(name_of_duplicate);

        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }

    public static void move_file(String file_to_copy, String name_of_duplicate) throws FileNotFoundException, IOException {
        File inputFile = new File(file_to_copy);
        File outputFile = new File(name_of_duplicate);

        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();

        inputFile.delete();
    }

    /**
     * Bits = 1 Byte 1024 Bytes = 1 KiloByte 1024 KiloBytes = 1 MegaByte 1048576
     * bytes = 1 MegaByte 1024 MegaBytes = 1 GigaByte 1024 GigaBytes = 1
     * TeraByte.
     *
     * @param file_path
     */
    public static double get_file_size_kb(String file_path) {
        File f = new File(file_path);
        double rst_unrounded = (double) f.length() / 1024;
        return Math.round(rst_unrounded);
    }

    /**
     *
     * @param file_path
     */
    public static double get_file_size_bytes(String file_path) {
        File f = new File(file_path);
        return f.length();
    }

    /**
     *
     * @param file_path
     */
    public static double get_file_size_mb(String file_path) {
        File f = new File(file_path);
        double rst_unrounded = (double) f.length() / 1048576;
        double rst_rounded = Double.parseDouble(String.format("%2.2f", rst_unrounded).replace(",", "."));
        return rst_rounded;
    }

    /**
     * Works very good now!
     *
     * @param file_path
     * @return
     */
    public static String get_file_size_auto(String file_path) {
        File f = new File(file_path);
        if (f.length() > 1048576) {
            double rst_unrounded = (double) f.length() / 1048576;
            double rst_rounded = Double.parseDouble(String.format("%2.2f", rst_unrounded).replace(",", "."));
            return "" + rst_rounded + " mb";
        } else if (f.length() < 1000) {
            return "" + f.length() + " b";
        } else if (f.length() > 1000) {
            double rst_unrounded = (double) f.length() / 1024;
            return "" + Math.round(rst_unrounded) + " kb";
        } else {
            return null;
        }
    }

    //===========================================================================
    /**
     * VERY
     * IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static byte[] filetoByteArray(String path) throws FileNotFoundException, IOException {
        byte[] content;
        FileInputStream p = new FileInputStream(path);
        content = new byte[p.available()];
        p.read(content);
        p.close();
        return content;
    }

    /**
     * Verified! Works good with 'filetoByteArray(String path)'
     *
     * @param path
     * @param arr
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void byteArrayToFile(String path, byte[] arr) throws FileNotFoundException, IOException {
        File f2 = new File(path);
        OutputStream out;
        out = new FileOutputStream(f2);
        out.write(arr);
    }

    public static void objectToFile(String path, Object obj) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        } catch (Exception ex) {
            Logger.getLogger(MyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Object fileToObject(String path) throws IOException, ClassNotFoundException {
        FileInputStream fas = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fas);
        Object obj = ois.readObject();
        return obj;
    }

    //=================================Copy directory section====================
    public static void copy(File source, File destination) throws IOException {
        if (source == null) {
            throw new NullPointerException("NullSource");
        }

        if (destination == null) {
            throw new NullPointerException("NullDestination");
        }

        if (source.isDirectory()) {
            copyDirectory(source, destination);
        } else {
            copyFile(source, destination);
        }
    }

    public static void copyDirectory(File source, File destination) throws IOException {
        copyDirectory(source, destination, null);
    }

    private static void copyDirectory(File source, File destination, FileFilter filter)
            throws IOException {
        File nextDirectory = new File(destination, source.getName());

        //
        // create the directory if necessary...
        //
        if (!nextDirectory.exists() && !nextDirectory.mkdirs()) {
            Object[] filler = {nextDirectory.getAbsolutePath()};
            String message = "DirCopyFailed";
            throw new IOException(message);
        }

        File[] files = source.listFiles();

        //
        // and then all the items below the directory...
        //
        for (int n = 0; n < files.length; ++n) {
            if (filter == null || filter.accept(files[n])) {
                if (files[n].isDirectory()) {
                    copyDirectory(files[n], nextDirectory, filter);
                } else {
                    copyFile(files[n], nextDirectory);
                }
            }
        }
    }

    public static void copyFile(File source, File destination) throws IOException {
        //
        // if the destination is a dir, what we really want to do is create
        // a file with the same name in that dir
        //
        if (destination.isDirectory()) {
            destination = new File(destination, source.getName());
        }

        FileInputStream input = new FileInputStream(source);

        if (source_file_newer_then_destination_file(source, destination)) {
            copyFile(input, destination);
        }
    }

    private static boolean source_file_newer_then_destination_file(File source, File destination) {
        if (source.lastModified() > destination.lastModified()) {
            return true;
        } else {
            return false;
        }
    }

    private static void copyFile(InputStream input, File destination) throws IOException {

        OutputStream output = null;

        output = new FileOutputStream(destination);

        byte[] buffer = new byte[1024];

        int bytesRead = input.read(buffer);

        while (bytesRead >= 0) {
            output.write(buffer, 0, bytesRead);
            bytesRead = input.read(buffer);
        }

        input.close();

        output.close();
    }
    //===========================================================================
}
