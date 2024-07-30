package com.example.demos.lowlevel;

import com.example.threadspawner.DirectThreadSpawner;

public class JoinDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("***** Join demo *****");

        Thread dependentThread = new Thread(getDependentRunnable());
        Thread dependantThread = new Thread(getDependantRunnable(dependentThread));

        DirectThreadSpawner.spawnThread(dependentThread);
        DirectThreadSpawner.spawnThread(dependantThread);
    }

    private static Runnable getDependentRunnable() {
        return () -> {
            try {
                System.out.println("Dependent running at: " + Thread.currentThread().getName());
                Thread.sleep(2_000);
                System.out.print("there");
                Thread.sleep(4_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static Runnable getDependantRunnable(Thread dependent) {
        return () -> {
            try {
                System.out.println("Dependant running at: " + Thread.currentThread().getName());
                Thread.sleep(500); // Just make sure that 'Hello ' is written after info where dependent is running
                System.out.print("Hello ");
                dependent.join();
                System.out.println("!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
