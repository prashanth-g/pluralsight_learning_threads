//package com.prashanth.pluralsight.learning.threads.examine;
//
//public class Main {
//    public static void main(String[] args) {
//        Q q = new Q();
//        new Producer(q);
//        new Consumer(q);
//    }
//}
//
//class Q {
//
//    int num;
//
//    boolean valueSet = false;
//
//    public synchronized void put(int num) {
//        while(valueSet) {
//            System.out.println("Inside Producer Wait" + Thread.currentThread().getName());
//            try { wait(); } catch(Exception e) {}
//        }
//        System.out.println(Thread.currentThread().getName());
//        System.out.println("Put : " + num);
//        this.num = num;
//        valueSet = true;
//        notify();
//    }
//
//    public synchronized void get() {
//        while(!valueSet) {
//            System.out.println("Inside Consumer Wait" + Thread.currentThread().getName());
//            try { wait(); } catch(Exception e) {}
//        }
//        System.out.println(Thread.currentThread().getName());
//        System.out.println("Get : " + num);
//        valueSet = false;
//        notify();
//    }
//}
//
//class Consumer implements Runnable {
//
//    Q q;
//
//    public Consumer(Q q) {
//        this.q = q;
//        Thread t = new Thread(this, "Consumer");
//        t.start();
//    }
//
//    public void run() {
//
//        while(true) {
//            q.get();
//            try { Thread.sleep(2000);} catch(Exception e) {}
//        }
//    }
//}
//
//class Producer implements Runnable {
//
//    Q q;
//
//    public Producer(Q q) {
//        this.q = q;
//        Thread t = new Thread(this, "Producer");
//        t.start();
//    }
//
//    public void run() {
//
//        int i = 0;
//
//        while(true) {
//            q.put(i++);
//            try { Thread.sleep(1000);} catch(Exception e) {}
//        }
//    }
//}
