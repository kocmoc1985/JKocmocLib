package Examples;

import java.util.*;

class MyClass {

    private int count;

    MyClass() {
        count = 0;
    }

    void set(int count, String name) {
        this.count = count;
        System.out.println(name + "У�?тановлено значение пол�? " + count + ": " + new Date());
    }

    int get(String name) {
        System.out.println(name + "Считано значение пол�? " + count + ": " + new Date());
        return count;
    }
}

class MyThread extends Thread {

    private boolean UpDown;
    private String name;
    private final MyClass obj;

    @Override
    public void run() {
        Random rnd = new Random();
        int number;
        for (int i = 1; i <= 3; i++) {
            synchronized (obj) {
                try {
                    number = obj.get(name);
                    if (UpDown) {
                        number++;
                    } else {
                        number--;
                    }
                    sleep(1000 + rnd.nextInt(9000));
                    obj.set(number, name);
                } // Обработка и�?ключени�?:
                catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public MyThread(boolean UpDown, MyClass obj) {
        this.UpDown = UpDown;
        if (UpDown) {
            name = "tttt";
        } else {
            name = "zzzzz";
        }
        this.obj = obj;
        start();
    }
}

public class SynchThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        MyClass obj = new MyClass();
        MyThread thA = new MyThread(true, obj);
        MyThread thB = new MyThread(false, obj);
        thA.join();
        thB.join();
        System.out.print("ggggg");
        obj.get("");
    }
}