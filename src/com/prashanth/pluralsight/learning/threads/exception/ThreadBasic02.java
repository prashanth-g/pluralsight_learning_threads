package com.prashanth.pluralsight.learning.threads.exception;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ThreadBasic02 {

    public static class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {

        private ExecutionException exception;

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            exception = new ExecutionException(e);
        }

        public ExecutionException getException() {
            return exception;
        }
    }

    public static void main(String[] args) {
        Jetbot jetbot = new Jetbot();

        System.out.println("-- Starting Jetbot --");

        Thread thread = new Thread(jetbot, "Jetbot");

        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        thread.setUncaughtExceptionHandler(customExceptionHandler);

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }

        thread.interrupt();

        try {
            thread.join();
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }

        ExecutionException  executionException = customExceptionHandler.getException();

        if(executionException != null) {
            executionException.printStackTrace();
        }
    }
}
