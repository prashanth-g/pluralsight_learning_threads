package com.prashanth.pluralsight.learning.threads.unsynchronized;

public class Producer implements Runnable {

    private Produce latestProduce = null;

    public Produce getLatestProduce() {
        return latestProduce;
    }

    @Override
    public void run() {

        System.out.println("Producer Starting");

        Produce produce = new Produce();

        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //
            }

            produce.setInstance(i);
            produce.setColor(Produce.Color.values()[i % Produce.Color.values().length]);
            latestProduce = produce;
        }

        System.out.println("Producer Ending");

    }
}
