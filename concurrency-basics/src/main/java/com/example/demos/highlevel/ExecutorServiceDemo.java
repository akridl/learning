package com.example.demos.highlevel;

import com.example.threadspawner.ThreadPoolExecutor;

public class ExecutorServiceDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("***** ExecutorService demo *****");

        // ThreadPoolSpawner is AutoCloseable, hence will be closed within try-with-resources automatically
        try (ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor()){
            threadPoolExecutor.execute(helloRunnable());
        }
    }

    private static Runnable helloRunnable() {
        return () -> {
            try {
                System.out.print("Hello ");
                Thread.sleep(2_000);
                System.out.println("there!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
