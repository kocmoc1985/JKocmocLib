/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
/**
 *
 * @author Administrator
 */
public class MyZip {
     /**
     *
     * @param from
     * @param to
     * @throws IOException
     */
    public static void zip_file(String from, String to) throws IOException {
        FileInputStream in = new FileInputStream(from);
        GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(to));
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        in.close();
        out.close();
        // Compress.gzipFile(from, from + ".gz");
    }

    public static void zip_file_works(String pathtxt, String pathgz) throws IOException {
        FileInputStream in = null;
        GZIPOutputStream out = null;

        try {
            in = new FileInputStream(pathtxt);
        } catch (Throwable ex) {
//            Logger.getLogger(HelpMODR.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out = new GZIPOutputStream(new FileOutputStream(pathgz));
        } catch (Throwable ex) {
//            Logger.getLogger(HelpMODR.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        in.close();
        out.close();
    }

    /**
     *
     * @param dir
     * @param zipfile
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public static void zipDirectory(String dir, String zipfile)
            throws IOException, IllegalArgumentException {
        // Check that the directory is a directory, and get its contents
        File d = new File(dir);
        if (!d.isDirectory()) {
            throw new IllegalArgumentException("Not a directory:  "
                    + dir);
        }
        String[] entries = d.list();
        byte[] buffer = new byte[4096]; // Create a buffer for copying
        int bytesRead;

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));

        for (int i = 0; i < entries.length; i++) {
            File f = new File(d, entries[i]);
            if (f.isDirectory()) {
                continue;//Ignore directory
            }
            FileInputStream in = new FileInputStream(f); // Stream to read file
            ZipEntry entry = new ZipEntry(f.getPath()); // Make a ZipEntry
            out.putNextEntry(entry); // Store entry
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
        }
        out.close();
        //Compress.zipDirectory(from, from + ".zip");
    }

    /**
     * Unzip a zip file, dont work with gzip !!!
     * @return
     */
    public static void unzip(String path) throws IOException {
        ZipFile zf = new ZipFile(path);
        Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) e.nextElement();
            System.out.println("Unzipping " + ze.getName());
            FileOutputStream fout = new FileOutputStream(ze.getName());
            InputStream in = zf.getInputStream(ze);
            for (int c = in.read(); c != -1; c = in.read()) {
                fout.write(c);
            }
            in.close();
            fout.close();
        }
    }

    /**
     * Added: 22-nov-2011
     * This function works fast!
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void unzip_gzip() throws FileNotFoundException, IOException {
        String source = "cc.gz";
        GZIPInputStream in = new GZIPInputStream(new FileInputStream(source));
        String target = "tt.txt";
        OutputStream out = new FileOutputStream(target);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}
