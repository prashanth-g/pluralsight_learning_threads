package com.prashanth.pluralsight.learning.threads.race;

import java.util.concurrent.CountDownLatch;

public class BarSimulationRaceCondition {

    private static final int numberOfThreads = 2;

    public static void main(String[] args) {
        for (int i = 1; i <= numberOfThreads; i++) {
            String name = "Thread"+i;
            new Thread(new BarPatron(name), name).start();
        }
    }

    private static class BarPatron implements Runnable {

        private final String name;

        private static volatile boolean roundBeingBought = false;

        private static final CountDownLatch cl = new CountDownLatch(numberOfThreads);

        private BarPatron(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            enterBarOrderDrinks();
        }

        private void enterBarOrderDrinks() {
            if(!roundBeingBought) {

                try {
                    cl.countDown();
                    cl.await();
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                }

                roundBeingBought = true;
                buyDrinks();
            } else {
                waitForDrink();
            }
        }

        private void waitForDrink() {
            System.out.println(name +" is waiting for the drink");
        }

        private void buyDrinks() {
            System.out.println(name +" is buying the drink");
        }
    }

}
