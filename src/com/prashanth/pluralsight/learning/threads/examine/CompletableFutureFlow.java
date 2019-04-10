package com.prashanth.pluralsight.learning.threads.examine;

import java.util.concurrent.*;

public class CompletableFutureFlow {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture<String> orderFlow =
                CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Ordered";
        }).thenApply(order -> {
            return order + " -> enriched";
        }).thenApply(order -> {
            return order + " -> paid";
        }).thenApply(order -> {
            return order + " -> dispatched";
        }).thenApply(order -> {
            return order + " -> sent email";
        });

        System.out.println(orderFlow.get());
    }
}
