package com.example.demos.highlevel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("***** ScheduledExecutorService demo *****");

        try (ScheduledExecutorService executor = Executors.newScheduledThreadPool(5)) {
            greetInThreeSeconds(executor);
            returnGreetingInThreeSeconds(executor);
            greetEverySecond(executor);
        }
    }

    private static void greetInThreeSeconds(ScheduledExecutorService executor) {
        executor.schedule(() -> System.out.println("Hello there from Runnable!"), 3, TimeUnit.SECONDS);
    }

    private static void returnGreetingInThreeSeconds(ScheduledExecutorService executor) {
        ScheduledFuture<String> greetingResult = executor.schedule(() -> "Hello there from Callable!", 2, TimeUnit.SECONDS);
        try {
            System.out.println(greetingResult.get());
            System.out.println("Periodic greeting is finished: " + greetingResult.isDone());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void greetEverySecond(ScheduledExecutorService executor) {
        executor.scheduleAtFixedRate(() -> System.out.println("Hello there from periodic greet!"), 0, 1, TimeUnit.SECONDS);
    }
}
