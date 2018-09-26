package resources;

public class MyException extends Exception {

//    public BadBoundsException() {
//    }
    public MyException(String message) {
        super(message);
    }

    public static void main(String[] args) {

        try {
            double x = Double.parseDouble("");
        } catch (Throwable ex) {
            System.out.println("YES  " + ex);
        }
    }
}
