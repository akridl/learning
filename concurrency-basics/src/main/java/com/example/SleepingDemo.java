package com.example;

import com.example.threadspawner.DirectThreadSpawner;

public class SleepingDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("***** Sleeping demo *****");
        DirectThreadSpawner.spawnThread(new Thread(getSleepingRunnable()));
    }

    private static Runnable getSleepingRunnable() {
        return () -> {
            try {
                System.out.println("Sleeping thread (" + Thread.currentThread().getName() + ") started");
                System.out.println("Gonna sleep...");
                Thread.sleep(3_000);
                System.out.println("Continue after sleep at: " + Thread.currentThread().getName()); // Should be the same
                System.out.println("Lemme take a nap once more...");
                Thread.sleep(2_000);
                System.out.println("Continue after second sleep at: " + Thread.currentThread().getName()); // Should be the same
                System.out.println("Ending...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
