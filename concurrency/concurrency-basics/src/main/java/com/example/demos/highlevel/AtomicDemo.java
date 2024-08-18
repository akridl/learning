package com.example.demos.highlevel;

import com.example.threadspawner.ThreadPoolExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("***** Atomic demo *****");

        // Counter is shared among both threads
        AtomicCounter counter = new AtomicCounter();

        CompletableFuture<Void> incrementer = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
        });
        CompletableFuture<Void> decrementer = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10_000; i++) {
                counter.decrement();
            }
        });

        try (ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor()) {
            CompletableFuture.allOf(incrementer, decrementer).thenRunAsync(() -> System.out.println("Counter value is: " + counter.value()), threadPoolExecutor).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static class AtomicCounter {
        private final AtomicInteger counter = new AtomicInteger();

        public void increment() {
            counter.incrementAndGet();
        }

        public void decrement() {
            counter.decrementAndGet();
        }

        public int value() {
            return counter.get();
        }
    }
}
