package com.prashanth.pluralsight.learning.threads.unsynchronized;

public class ThreadBasic03 {

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer(producer);

        Thread producerThread = new Thread(producer, "producer");
        Thread consumerThread = new Thread(consumer, "consumer");

        producerThread.start();
        consumerThread.start();
    }

}
