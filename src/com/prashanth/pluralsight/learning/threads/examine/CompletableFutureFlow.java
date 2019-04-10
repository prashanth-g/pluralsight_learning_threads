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

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                //
            }
            return "CF1 Completed";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                //
            }
            return "CF2 Completed";
        });

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                //
            }
            return "CF3 Completed";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(cf1, cf2, cf3);

        System.out.println(anyOfFuture.get());
    }
}
