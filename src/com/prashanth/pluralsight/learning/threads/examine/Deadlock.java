package com.prashanth.pluralsight.learning.threads.examine;

public class Deadlock extends Thread {

    public String name;
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public Deadlock(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Deadlock dl;
        dl = new Deadlock("Orange");
        dl.start();
        dl = new Deadlock("apple");
        dl.start();
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (lock1) {
                System.out.println(name + " holding 1 while going for 2");
                yield();
                synchronized (lock2) {
                    System.out.println(name + " holding 1 and 2");
                }
            }

            System.out.println("Releases both");

            synchronized (lock2) {
                System.out.println(name + " holding 2 while going for 1");
                yield();
                synchronized (lock1) {
                    System.out.println(name + " holding 2 and 1");
                }
            }

            System.out.println("Releases both");

        }
    }

}
