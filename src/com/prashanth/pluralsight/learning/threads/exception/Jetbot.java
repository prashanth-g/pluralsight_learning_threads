package com.prashanth.pluralsight.learning.threads.exception;

import java.util.concurrent.TimeUnit;

public class Jetbot implements Runnable {
    @Override
    public void run() {
        System.out.println("-- I have started --");

        Thread currentThread = Thread.currentThread();

        while(!currentThread.isInterrupted()) {
            System.out.println("-- Still I am running --");

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                currentThread.interrupt();
            }
        }

        throw new RuntimeException("-- I am stopped! --");
    }
}
