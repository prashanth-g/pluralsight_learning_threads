package com.prashanth.pluralsight.learning.threads.unsynchronized;

public class Consumer implements Runnable {

    private final Producer producer;

    public Consumer(Producer producer) {
        this.producer = producer;
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

            Produce produce = producer.getLatestProduce();

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
