package com.example.demos.highlevel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FuturesDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("***** Futures demo *****");

        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
            Future<String> helloFuture = executor.submit(() -> {
                Thread.sleep(2_000);
                return "Hello there from Runnable run by executor!";
            });
            System.out.println("state of the future: " + helloFuture.state());
/*
            boolean isDone = helloFuture.isDone();
            System.out.println("future is done: " + isDone);
            if (!isDone) {
                helloFuture.cancel(true);
            }
*/
            System.out.println(helloFuture.get());
            System.out.println("state of the future: " + helloFuture.state());
            System.out.println("future is already finished: " + helloFuture.isDone());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
