/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.Native;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * Simple example of native library declaration and usage.
 */
public class JNA {

    /**
     * OBS! uses msvcrt.dll
     */
    public interface CLibrary extends Library {

        void printf(String format, Object... args);
    }

    public static void run_clibrary(String[] args) {
        CLibrary INSTANCE = (CLibrary) Native.loadLibrary(
                (Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);

        INSTANCE.printf("Hello, World\n");
        for (int i = 0; i < args.length; i++) {
            INSTANCE.printf("Argument %d: %s\n", i, args[i]);
        }
    }

    //================
    public interface Kernel32 extends Library {
        // FREQUENCY is expressed in hertz and ranges from 37 to 32767
        // DURATION is expressed in milliseconds

        public boolean Beep(int FREQUENCY, int DURATION);

        public void Sleep(int DURATION);
    }

    public static void run_kernel_32__1() {
        Kernel32 lib = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
//        lib.Beep(698, 500);
//        lib.Sleep(500);
//        lib.Beep(698, 500);
    }

    //================
    public static void main(String[] args) {
        run_clibrary(args);
    }
}
