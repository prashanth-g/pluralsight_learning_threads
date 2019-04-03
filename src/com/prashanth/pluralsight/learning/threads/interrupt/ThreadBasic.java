package com.prashanth.pluralsight.learning.threads.interrupt;

import java.util.concurrent.TimeUnit;

public class ThreadBasic {

    public static void main(String[] args) {

        Bartender bartender = new Bartender();
        Thread barternderThread = new Thread(bartender, "Bartender");

        barternderThread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int numCustomers = 5;
        for (int i = 0; i < numCustomers; i++) {
            String customerName = "Customer " + i;
            Customer customer = new Customer(barternderThread, customerName, (int) (Math.random() * 10));
            new Thread(customer, customerName).start();
        }
    }
}
