package com.prashanth.pluralsight.learning.threads.unsynchronized;

public class ThreadBasic04 {

    static boolean S1 = false;

    public static void main(String[] args) {
        new Thread(new Thread1(), "Thread1").start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //
        }

        new Thread(new Thread2(), "Thread2").start();
    }

    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            System.out.println("Inside thread 1");
            while(!S1) {}
            System.out.println("HELLO ");
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            ThreadBasic04.S1 = true;
            System.out.println("HI ");
        }
    }
}
