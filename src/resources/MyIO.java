/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.*;
import java.io.*;

/**
 *
 * @author Administrator
 */
public class MyIO {

    /**
     *
     * @param fileName
     * @param textToWrite
     * @throws IOException
     */
     public static void writeToFile(String fileName, String textToWrite,boolean apend) throws IOException {
        FileWriter fstream = new FileWriter(fileName, apend);
        BufferedWriter out = new BufferedWriter(fstream);
        //
        out.write(textToWrite);
        out.newLine();
        //Very Important this makes that file is not in use after this operation
        out.flush();
        out.close();
    }


    /**
     * Läser av text filen och lagrar informationen i en
     * LinkedList<String>(Array)
     *
     * @param regex "," or ";"
     * @param filename den filen som skall avläsas
     * @return en fylld LinkedList
     */
    public static LinkedList<String> readTxtToLinkedList(String filename, String regex) {
        LinkedList<String> list = new LinkedList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String[] parts;
            String rs = br.readLine();
            while (rs != null) {
                parts = rs.split(regex);
                for (int i = 0; i < parts.length; i++) {
                    list.add(parts[i]);
                }
                rs = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("" + e);
        }
        System.out.println("LinkedList.toString() =  " + list.toString());
        return list;
    }

    /**
     * Läser av text filen och lagrar informationen i en
     * LinkedList<String>(Array)
     *
     * @param regex "," or ";"
     * @param filename den filen som skall avläsas
     * @return en fylld LinkedList
     */
    public static ArrayList<String> read_Txt_To_ArrayList(String filename, String regex) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String[] parts;
            String rs = br.readLine();
            while (rs != null) {
                parts = rs.split(regex);
                for (int i = 0; i < parts.length; i++) {
                    list.add(parts[i]);
                }
                rs = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("" + e);
        }
        //
        System.out.println("ArrayList.toString() =  " + list.toString());
        return list;
    }

    public static ArrayList<String> read_Txt_To_ArrayList(String filename) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String rs = br.readLine();
            while (rs != null) {
                list.add(rs);
                rs = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("" + e);
        }
        //
        System.out.println("ArrayList.toString() =  " + list.toString());
        //
        return list;
    }

    /**
     *
     * @param fileToWriteTO
     * @param signalArr
     */
    public static void write(String fileToWriteTO, String[] signalArr) {
        try {
            // Create file
            FileWriter fstream = new FileWriter(fileToWriteTO, false);
            BufferedWriter out = new BufferedWriter(fstream);
            for (int i = 0; i < signalArr.length; i++) {
                if (signalArr[i] != null) {
                    out.write(signalArr[i]);
                } else {
                    out.write("EMPTY");
                }
                out.newLine();
                out.flush();
                //Close the output stream
            }
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Transforms a file into byte[]
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private byte[] FiletoByteArray(String path) throws FileNotFoundException, IOException {
        byte[] content;
        FileInputStream p = new FileInputStream(path);
        content = new byte[p.available()];
        p.read(content);
        p.close();
        return content;
    }

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!! Verified! Works good with
     * 'filetoByteArray(String path)'
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

    /**
     * ######################################################################
     * Copies the content from one file to another
     *
     * @param file_from
     * @param file_to
     * @param append if the data from source file should be added to dist file
     */
    public static void copy_content_from_one_file_to_another(String file_from, String file_to, boolean append) {
        try {
            File f1 = new File(file_from);
            File f2 = new File(file_to);
            InputStream in = new FileInputStream(f1);
            OutputStream out;
            if (append) {
                //For Append the file.
                out = new FileOutputStream(f2, true);
            } else {
                //For Overwrite the file.
                out = new FileOutputStream(f2);
            }

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " in the specified directory.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes the properties to file for example after changing something
     *
     * @param props
     * @param path
     * @param properties_title
     */
    public static void save_Properties_to_file(Properties props, String path, String properties_title) {
        try {
            props.store(new FileOutputStream(path), properties_title);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }

    /**
     *
     * @param path_andOr_fileName "onoff.properties" or
     * "c:/src/onoff.properties"
     * @param list_properties specifies if the properties should be listed
     * @return loaded properties
     */
    public static Properties properties_load_properties(String path_andOr_fileName, boolean list_properties) {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(path_andOr_fileName));
            if (list_properties == true) {
                p.list(System.out);
            }
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
        return p;
    }

    /**
     *
     * @param props_to_store the properties to be saved
     * @param fileName the name of file in which the properties gonna be saved
     * @param title_inside_file title inside the props file
     */
    public static void properties_save_properties(Properties props_to_store, String fileName, String title_inside_file) {
        try {
            props_to_store.store(new FileOutputStream(fileName), title_inside_file);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
    }

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!! SAVES THE FILE WITH GIVEN ENCODING
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! UTF-8 for öÅ and so on
     *
     * @param props_to_store the properties to be saved
     * @param fileName the name of file in which the properties gonna be saved
     * @param title_inside_file title inside the props file
     */
    public static void properties_save_properties_with_required_encoding(Properties props_to_store, String fileName_path, String title_inside_file, String encoding) {
        try {
            Writer out = new OutputStreamWriter(new FileOutputStream(fileName_path), encoding);
            props_to_store.store(out, title_inside_file);
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
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
     *
     * @param file_name
     */
    public static void rename_file(String old_file_name, String new_file_name) {
        File f = new File(old_file_name);
        f.renameTo(new File(new_file_name));
    }

    /**
     * This is a very important Class !!!
     */
    public class saveObjectAsFile implements Serializable {

        Object obj;

        public saveObjectAsFile(Object obj, String path_and_name, int operation) throws FileNotFoundException, IOException, ClassNotFoundException {
            this.obj = obj;
            if (operation == 1) {
                save_object_as_file(obj, path_and_name);
            } else if (operation == 2) {
                load_object_from_file(obj, path_and_name);
            }

        }

        public void save_object_as_file(Object obj, String path_and_name) throws FileNotFoundException, IOException {
            FileOutputStream fos = new FileOutputStream(path_and_name);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.obj);
        }

        public Object load_object_from_file(Object obj, String path_and_name) throws FileNotFoundException, IOException, ClassNotFoundException {
            FileInputStream fas = new FileInputStream("File");
            ObjectInputStream ois = new ObjectInputStream(fas);
            return ois.readObject();
        }
    }
    /**
     *
     * @param username
     * @param pass
     * @param host
     * @param file_name_with_extension
     * @throws SmbException
     * @throws MalformedURLException
     * @throws UnknownHostException
     * @throws IOException
     */
}
