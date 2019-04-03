package com.prashanth.pluralsight.learning.threads;

import java.util.concurrent.TimeUnit;

public class Bartender implements Runnable {

    @Override
    public void run() {
        System.out.println("Bartender: Not busy today; just quick break");

        while (true) {
            if(Thread.interrupted()) {
                System.out.println("Bartender: Is someone waiting?");
            }

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
