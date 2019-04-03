package com.prashanth.pluralsight.learning.threads.complete;

import java.util.concurrent.TimeUnit;

public class ThreadBasic01 {

    public static void main(String[] args) {

        ActiveBartender bartender = new ActiveBartender();
        Thread barternderThread = new Thread(bartender, "Bartender");

        barternderThread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {

        }

        int numCustomers = 5;

        Thread customerThreads[] = new Thread[numCustomers];
        for (int i = 0; i < numCustomers; i++) {
            String customerName = "Customer " + i;
            StrictCustomer customer = new StrictCustomer(barternderThread, customerName, (int) (Math.random() * 10));

            customerThreads[i] = new Thread(customer, customerName);
            customerThreads[i].start();
        }

        try {
            barternderThread.join();
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }

        System.out.println("Voice: Isn't the bartender sneaking out the door?");

        for (int i = 0; i < numCustomers; i++) {
            customerThreads[i].interrupt();
        }
    }
}
