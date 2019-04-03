package com.prashanth.pluralsight.learning.threads.complete;

import java.util.concurrent.TimeUnit;

public class ActiveBartender implements Runnable {

    @Override
    public void run() {
        int numberOfTimesWoken = 0;

        System.out.println("Bartender: Boss is not available so it is time for a quick break");

        while(true) {
            if (Thread.interrupted()) {
                System.out.println("Oh Oh! Someone is waiting!");
            }

            if(numberOfTimesWoken == 2) {
                break;
            }

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                numberOfTimesWoken++;
                Thread.currentThread().interrupt();
            }
        }
    }

}
