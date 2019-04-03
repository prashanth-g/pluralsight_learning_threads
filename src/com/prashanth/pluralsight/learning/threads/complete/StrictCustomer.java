package com.prashanth.pluralsight.learning.threads.complete;

import java.util.concurrent.TimeUnit;

public class StrictCustomer implements Runnable{

    private Thread barternderThread;
    private String customerName;
    private int waitTime;

    public StrictCustomer(Thread barternderThread, String customerName, int waitTime) {
        this.barternderThread = barternderThread;
        this.customerName = customerName;
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        System.out.println(customerName + ": Doesn't seem to be anyone around. Lets wait for a moment");

        try {
            TimeUnit.SECONDS.sleep(waitTime);
        } catch (InterruptedException e) {
            System.out.println("Where is the bartender man? How long do I want to wait?");
            return;
        }

        System.out.println(customerName + ": Oh there is a bell! Can I order");

        barternderThread.interrupt();
    }
}
