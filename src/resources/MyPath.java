/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MyPath {

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

    /**
     * SUPER IMPORTANT
     * URL url = MyPath.class.getResource("nonJavaFiles/example.xml");
     * @param url
     * @return
     */
    public static String getFileNameFromURL(URL url) {
        //
        try {
            File f = new File(url.toURI());
            return f.getName();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MyPath.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void main(String[] args) {
        URL url = MyPath.class.getResource("nonJavaFiles/example.xml");
        System.out.println(""+ getFileNameFromURL(url));
    }

    public static String get_desktop_path_more_reliable() {
        return javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString();
    }

    public static String get_desktop_path() {
        return System.getProperty("user.home") + "\\" + "Desktop";
    }

    public static String[] splitStringWithPoint(String strToSplit) {
        return strToSplit.split("\\.");
    }

    public static void previousPathExample() {
        try {
            MyProcess.run_application_exe_or_jar("pixie.exe", "../"); //step out one directory
        } catch (IOException ex) {
            Logger.getLogger(MyPath.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void currentPathExample() {
        File[] f = new File(".").listFiles();// "." = current path
        for (File file : f) {
            System.out.println("file = " + file.getName());
        }
    }

    /**
     * THIS IS MEGA-IMPORTANT! Example it "optimises" the link like:
     * _files/_exchange/fedmog/test/.. -> _files/_exchange/fedmog
     *
     * @tags optimize, optimise, normalise, optimise_link, normalise link
     * @param link
     * @return
     */
    public static String optimize_link(String link) {
        try {
            String link_temp = check_link_end(link);
            URI uri = new URI(link_temp);
            return uri.normalize().getPath();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MyPath.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public static boolean compare_links(String link_1, String link_2) {
        String l_1 = optimize_link(link_1);
        String l_2 = optimize_link(link_2);
        System.out.println("l_1 = " + l_1);
        System.out.println("l_2 = " + l_2);
        if (l_1.equals(l_2)) {
            return true;
        }
        return false;
    }

    public static String check_link_end(String path) {
        String last;
        try {
            last = "" + path.charAt(path.length() - 1);
        } catch (Exception ex) {
            Logger.getLogger(MyPath.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }

        switch (last) {
            case "//":
                return path.replace("//", "/");
            case "/":
                return path;
            default:
                path += "/";
                return path;
        }
    }

    /**
     * This method is very useful, it checks that the path ends with "/".
     *
     * @param path
     * @return
     */
    public static String verify_path(String path) {
        StringBuilder builder = new StringBuilder(path);
        char last_char = path.charAt(path.length() - 1);
        if (last_char == '/') {
            return path;
        } else {
            builder.append("/");
            return builder.toString();
        }
    }

    /**
     *
     * @return
     */
    public static String getDirectoryFromWhichTheJavaAppRuns() {
        return System.getProperties().getProperty("user.dir");
    }

    /**
     * This example is extrmely important as you can have nonJava files inside
     * the "src" folder. Add after compiling the all the files except libraries
     * will be inside the ".jar" file.
     *
     * @Tags [path to src, path src, ]
     * @return
     */
    public static URL get_Url_of_file_placed_in_src_folder() {
//        return MyPath.class.getResource("example.xml"); // if placed in same folder as the MyPath class
        return MyPath.class.getResource("nonJavaFiles/example.xml"); // if placed in a folder which is inside the folder where MyPath class is
    }

    public static void test() {
        //
        URL url = MyPath.class.getResource("nonJavaFiles/example.xml");
        //
        try {
            File f = new File(url.toURI());
            System.out.println("" + f.getName());
        } catch (URISyntaxException ex) {
            Logger.getLogger(MyPath.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        System.out.println("" + url.getFile());
        System.out.println("" + url.getPath());
    }

    

    /**
     * Very useful, when using JavaNative. This method adjusts where the Java
     * looks for "dll" files.
     *
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setJavaLibraryPath(String path, String dllLibraryNameWithoutExtension) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        // Reset the "sys_paths" field of the ClassLoader to null.
        Class clazz = ClassLoader.class;
        Field field = clazz.getDeclaredField("sys_paths");
        boolean accessible = field.isAccessible();
        if (!accessible) {
            field.setAccessible(true);
        }
        Object original = field.get(clazz);
        // Reset it to null so that whenever "System.loadLibrary" is called, it will be reconstructed with the changed value.
        field.set(clazz, null);
        try {
            // Change the value and load the library.
            System.setProperty("java.library.path", path);//"c:\\tmp"
            if (dllLibraryNameWithoutExtension.length() > 0) {
                System.loadLibrary(dllLibraryNameWithoutExtension);
            }
        } finally {
            //Revert back the changes.
            field.set(clazz, original);
            field.setAccessible(accessible);
        }
    }

}
