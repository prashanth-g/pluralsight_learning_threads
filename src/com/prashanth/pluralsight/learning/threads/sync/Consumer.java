package com.prashanth.pluralsight.learning.threads.sync;

public class Consumer implements Runnable, ProduceObserver {

    private volatile Produce produce;

    @Override
    public void onProduction(Produce produce) {
        this.produce = produce;
    }

    @Override
    public void run() {
        System.out.println("Consumer starting");

        while(true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //
            }

            if(produce != null) {
                int produceInstance = produce.getInstance();
                Produce.Color color = produce.getColor();

                System.out.println("Last Produce Instance :" + produceInstance);
                System.out.println("Last Produce Color :" + color.name());

                if(produceInstance == 10) {
                    break;
                }
            }
        }

        System.out.println("Consumer ending");
    }

}
