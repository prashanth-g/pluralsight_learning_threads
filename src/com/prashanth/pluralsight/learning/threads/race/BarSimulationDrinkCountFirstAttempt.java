package com.prashanth.pluralsight.learning.threads.race;

import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class BarSimulationDrinkCountFirstAttempt {

    private static final int numberOfThreads = 2;

    public static void main(String[] args) {
        for (int i = 1; i <= numberOfThreads; i++) {
            String name = "Thread"+i;
            new Thread(new BarPatron(name), name).start();
        }
    }

    private static class BarPatron implements Runnable {

        private static final int numBarVisits = 50;

        private final String name;

        private static final AtomicBoolean roundBeingBought = new AtomicBoolean();

        private final AtomicInteger numRoundsBought = new AtomicInteger();

        private static final Phaser phaser = new Phaser(numberOfThreads);

        private BarPatron(String name) {
            this.name = name;
        }

        @Override
        public void run() {

            for (int visit = 1; visit <= numBarVisits ; visit++) {
                enterBarOrderDrinks(visit);
            }

            int percentage = numRoundsBought.get() * 100 / numBarVisits;

            System.out.println(name + ": bought " + numRoundsBought+" rounds ("+ percentage + "%).");
        }

        private void enterBarOrderDrinks(int visit) {

            roundBeingBought.set(false);

            phaser.arriveAndAwaitAdvance();

            if(roundBeingBought.compareAndSet(false, true)) {
                buyDrinks(visit);
                numRoundsBought.getAndIncrement();
            } else {
                waitForDrink(visit);
            }

            phaser.arriveAndAwaitAdvance();

        }

        private void waitForDrink(int visit) {
            System.out.println("Bar visit :"+ visit+  " " +name +" is waiting for the drink");
        }

        private void buyDrinks(int visit) {
            System.out.println("Bar visit :"+ visit+  " " +name +" is buying the drink");
        }
    }
}
