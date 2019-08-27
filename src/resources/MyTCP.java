/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MyTCP {

    private static Socket socket;

    public static boolean portOccupied(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            ss.close();
            return false;
        } catch (IOException ex) {
            return true;
        }
    }
    
    /**
     * Find this in ServerAdmin project
     * ping portPing pingPort port_ping
     * @return 
     */
    public static boolean pingPort(String host, String port) {
        try {
            //
            //
            if (host.isEmpty() == false && port.isEmpty() == false) {
                InetAddress adress = InetAddress.getByName(host);
                Socket sockett = new Socket(adress, Integer.parseInt(port));
            }
            //
        } catch (IOException ex) {
            Logger.getLogger(MyTCP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean portOpen(int port) {
        try {
            Socket s = new Socket("localhost", port);
            s.close();
            return true;
        } catch (UnknownHostException ex) {
            Logger.getLogger(MyRegistry.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(MyRegistry.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static ArrayList getCurrentEnvironmentNetworkIp() {
        Enumeration<NetworkInterface> netInterfaces;
        ArrayList<String> network_interface_list = new ArrayList();
        String currentHostIpAddress = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();

            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    InetAddress addr = address.nextElement();

                    if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress()
                            && !(addr.getHostAddress().indexOf(":") > -1)) {
                        currentHostIpAddress = addr.getHostAddress();
                        network_interface_list.add(currentHostIpAddress);
                    }
                }
            }
            if (currentHostIpAddress == null) {
                currentHostIpAddress = "127.0.0.1";
            }

        } catch (SocketException e) {
            currentHostIpAddress = "127.0.0.1";
        }
        return network_interface_list;
    }

    public static String chooseNetworkInterFace() {
        ArrayList<String> interface_list = getCurrentEnvironmentNetworkIp();
        //
        JComboBox box = new JComboBox();
        //
        for (int i = 0; i < interface_list.size(); i++) {
            box.addItem(interface_list.get(i));
        }
        //
        JOptionPane.showMessageDialog(null, box, "Choose Interface", JOptionPane.QUESTION_MESSAGE);
        //
        //
        return extract_first_3_parts_of_ip((String) box.getSelectedItem());
    }

    public static String extract_first_3_parts_of_ip(String ip) {
        String[] arr = ip.split("\\.");
        //
        if (arr.length == 4) {
            return arr[0] + "." + arr[1] + "." + arr[2];
        } else {
            return "";
        }
    }

    public static boolean ping_example() throws UnknownHostException, IOException {
        InetAddress address = InetAddress.getByName("192.168.0.1");
        boolean reacheble = address.isReachable(10000);
        return reacheble;
    }

    public static String getLocalHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    public static String getLocalHostIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static void getMacAddress() {
        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(sb.toString());

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (SocketException e) {

            e.printStackTrace();

        }
    }

    /**
     * Ok but ping 3 is better
     *
     * @deprecated
     * @param ip
     * @return
     * @throws UnknownHostException
     * @throws IOException
     */
    public static boolean ping(String ip) throws UnknownHostException, IOException {
        InetAddress address = InetAddress.getByName(ip);
        boolean reacheble = address.isReachable(5000);
        return reacheble;
    }

    /**
     * Not good
     *
     * @deprecated
     * @param ip
     * @return
     */
    public static boolean ping2(String ip) {
        try {
            Socket sock = new Socket(ip, 7);
            return true;
        } catch (UnknownHostException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * The best
     *
     * @param host
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean ping3(String host) throws IOException, InterruptedException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows ? "-n" : "-c", "1", host);
        Process proc = processBuilder.start();

        int returnVal = proc.waitFor();
        return returnVal == 0;
    }

    private static String getMacAddrHost_run_with_output(String param) throws IOException {
        Process p = Runtime.getRuntime().exec(param);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = input.readLine()) != null) {
            if (!line.trim().equals("")) {
                // keep only the process name
                line = line.substring(1);
                String mac = getMacAddrHost_extractmac(line);
                if (mac.isEmpty() == false) {
                    return mac;
                }
            }

        }
        return null;
    }

    private static String getMacAddrHost_extractmac(String str) {
        String arr[] = str.split("   ");
        for (String string : arr) {
            if (string.trim().length() == 17) {
                return string.trim().toUpperCase();
            }
        }
        return "";
    }

    /**
     * THis is the main one
     * @param host
     * @return
     * @throws IOException
     * @throws InterruptedException 
     */
    public static String getMacAddrHost(String host) throws IOException, InterruptedException {
        //
        boolean ok = ping3(host);
        //
        if (ok) {
            InetAddress address = InetAddress.getByName(host);
            String ip = address.getHostAddress();
            return getMacAddrHost_run_with_output("arp -a " + ip);
        }
        //
        return null;
        //
    }

    public static void main(String[] args) {
        try {
            System.out.println("" + getMacAddrHost("MIXCONT-VS2008"));
        } catch (IOException ex) {
            Logger.getLogger(MyTCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * SUPER IMPORTANT To send "escape char" -> "\u001b" over TCP can be a
     * problem because when the server recieves it, it appends one more "\" and
     * escape char is not recognized
     *
     * @return
     */
    public static String GET_ESCAPE_CHAR() {
        byte[] arr = {new Byte("27")};
        return new String(arr);
    }

    private void send(String msg) throws IOException {
        //Best to use with print & flush
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.print(msg);
        out.flush();
        synchronized (this) { // it's sometimes very useful to have a pause between sendings aspecially if the server "recieve" modul is "loop" based
            try {
                wait(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyTCP.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        ///=====OR=====================================================
//        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//        out.println(msg);//in this case it appends \r\n to each message
    }

    /**
     * SUPER IMPORTANT SUPER IMPORTANT SUPER IMPORTANT SUPER IMPORTANT SUPER
     * IMPORTANT Consider this example this have many useful things and
     * experiences inside. This one was intended to use in connection with file
     * sending, but is a great experience for other type of sending to.
     *
     * @param file_path_and_name
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void recieve_file(String file_path_and_name) throws FileNotFoundException, IOException {
        if (check_if_available() == false) {
            return;
        }

        InputStream is = getSocket().getInputStream();
        FileOutputStream fos = new FileOutputStream(file_path_and_name);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //
        final byte buf[] = new byte[4096];
        //
        int bytesRead;
        while ((bytesRead = is.read(buf)) != -1) {
            //
            try {
                bos.write(buf, 0, bytesRead);
                //
                //Important
                if (is.available() == 0) {
                    break;

                }
                //
                //
            } catch (IOException ioe) {
                Logger.getLogger(MyTCP.class
                        .getName()).log(Level.SEVERE, null, ioe);
            } catch (NullPointerException npe) {
                Logger.getLogger(MyTCP.class
                        .getName()).log(Level.SEVERE, null, npe);
            }
        }
        //

        //This one is super important, empties the rest of bytes so they become written
        bos.flush();
        //When this one is closed the file is not "in use" any more
        fos.close();
        //
        //
    }

    /**
     *
     * SUPER IMPORTANT SUPER IMPORTANT SUPER IMPORTANT SUPER IMPORTANT SUPER
     * IMPORTANT This method filters "wrong" inputs which prevents that
     * ObjectInputStream fails when reading the input
     *
     * @return
     * @throws IOException
     */
    private boolean filter_stream() throws IOException {
        wait_(10);
        //
        //OBS! OBS! OBS! OBS!
        socket.setReceiveBufferSize(819200);
        //
        InputStream input = socket.getInputStream();
        //
        //
        //This means "unordinary" input
        if (input.available() == 0 || input.available() < 200) {
            return true;
        }
        //

        return false;
    }

    /**
     * Checks if input stream is empty
     *
     * @return
     * @throws IOException
     */
    private boolean check_if_available() throws IOException {
        wait_(10);
        InputStream input = getSocket().getInputStream();
        byte[] buffer = new byte[input.available()];
        if (buffer.length > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method empties the input stream, you can also present this input as
     * String with this method
     */
    private void flush_input_stream() {
        InputStream input;
        try {
            input = socket.getInputStream();
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            String s = new String(buffer);
//            show_message("flush = " + s);

        } catch (IOException ex) {
            Logger.getLogger(MyTCP.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *
     * This is a great method for recieving messages from a non java interface
     * but not as rational as the recieve_2, but the "recieve_2" cannot be used
     * in some context, ex. when you can't make so the client appends "####" to
     * the beginning of message.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void recieve() throws IOException, ClassNotFoundException {
//        wait_(10); // The intervall does metter, 10ms is a good rate with no big influence on CPU
//        
//        InputStream input = socket.getInputStream();
//        
//        byte[] buffer = new byte[input.available()];
//        
//        if (buffer.length == 0) {
//            return;
//        }
//        
//        input.read(buffer);
//        String s = new String(buffer);
//        System.out.println("" + s);
        //
//       controller.addToMessageReciever(str);
        //
        //
        //OBS! Make sure when passing the String to a "MessageRecieverThread", you 
        //should NEVER make some operations like extracting and so on in Order to prevent
        //delays and as the result getting several messages in one "recieve".
        //The "add(Object msg)" method in "MessageRecieverThread" should not be synchronized
        //and only add the msg to the buffer, in this case you will almost have no delays
    }

    /**
     * This method is very good and rational fits extreamly well when
     * communicating with clients other than java. Note: due to it uses
     * "ObjectInputStream input = new
     * ObjectInputStream(socket.getInputStream())" it cuts first 4 bytes, so
     * when the client sends you something it must send it like ####message
     * otherwise you will recieve = "age" insted of message
     *
     * @throws IOException
     */
    private void recive_2() throws IOException {
//        InputStream input_2 = null;
//        try {
//            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
//        } catch (IOException ex) {
//            try {
//                input_2 = socket.getInputStream();
//                byte[] buffer = new byte[input_2.available()];
//                input_2.read(buffer);
//                String s = new String(buffer);
//                msgReciever.add(s);
////                send("recieved - ok");
//            } catch (SocketException ex2) {
//                RECIEVE = false;
//                System.out.println("Client disconnected");
//                return;
//            }
//        }
    }

    /**
     * This method is very rational, but it won't work if the sent message
     * doesn't end with "\n" or "\r" it will only hang up
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void recive_3() throws IOException, ClassNotFoundException {
//
//        if (socket == null || socket.isClosed()) {
//            return;
//        }
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//        String fromServer;
//        while ((fromServer = in.readLine()) != null) {
//            System.out.println("" + fromServer);
//        }
    }
}
