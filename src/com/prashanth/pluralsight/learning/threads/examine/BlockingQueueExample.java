package com.prashanth.pluralsight.learning.threads.examine;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        Thread producerThread = new Thread(new Producer(blockingQueue));
        Thread consumerThread = new Thread(new Consumer(blockingQueue));

        producerThread.start();
        consumerThread.start();
    }
}

class Producer implements Runnable {

    BlockingQueue<Integer> blockingQueue = null;
    int i = 0;

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                produce(i++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void produce(int i) throws InterruptedException {
        System.out.println("Producer element produced element : " + i);
        blockingQueue.put(i);
        Thread.sleep(1000);
    }
}

class Consumer implements Runnable {

    BlockingQueue<Integer> blockingQueue = null;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume() throws InterruptedException {
        System.out.println("Consumer element consumed element : " + blockingQueue.take());
        Thread.sleep(1000);
    }
}
