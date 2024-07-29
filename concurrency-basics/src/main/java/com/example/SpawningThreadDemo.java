package com.example;

import com.example.threadspawner.DirectThreadSpawner;

public class SpawningThreadDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("***** Spawning threads directly demo *****");
        spawnThreadDirectly1();
        System.out.println("===");
        spawnThreadDirectly2();
        System.out.println();
    }

    private static void spawnThreadDirectly1() {
        System.out.println("Hello from the main thread: " + Thread.currentThread().getName());
        System.out.println("Spawning new thread from main thread...");
        DirectThreadSpawner.spawnThread(new Thread(() -> System.out.println("[1]: Hello from spawned thread: " + Thread.currentThread().getName())));
        System.out.println("Terminating main thread...");
    }

    private static void spawnThreadDirectly2() {
        System.out.println("Hello from the main thread: " + Thread.currentThread().getName());
        System.out.println("Spawning new thread from main thread...");
        DirectThreadSpawner.spawnThread(new HelloThread());
        System.out.println("Terminating main thread...");
    }

    private static class HelloThread extends Thread {

        @Override
        public void run() {
            System.out.println("[2]: Hello from spawned thread: " + Thread.currentThread().getName());
        }
    }
}
