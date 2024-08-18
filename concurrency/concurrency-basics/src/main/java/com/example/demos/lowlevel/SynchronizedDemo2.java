package com.example.demos.lowlevel;

import com.example.threadspawner.DirectThreadSpawner;

/**
 * Keyword 'synchronized' doesn't have to be used only in method declaration, but also as part of the method body. In
 * this case, it acts as a monitor over some {@link Object}.
 */
public class SynchronizedDemo2 implements Runnable {

    @Override
    public void run() {
        System.out.println("***** Synchronized demo (used in body) *****");

        // Counter is shared among both threads
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                synchronizedCounter.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                synchronizedCounter.decrement();
            }
        });

        DirectThreadSpawner.spawnThread(t1);
        DirectThreadSpawner.spawnThread(t2);

        try {
            t1.join();
            t2.join();

            // Counter value should be 0
            // Since we 10 thousand times increment one to the initial value (=0), and then same number of times decrement by one
            System.out.println("Counter value is: " + synchronizedCounter.value());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class SynchronizedCounter {
        private volatile int value = 0;

        public void increment() {
            changeValueBy(1);
        }

        public void decrement() {
            changeValueBy(-1);
        }

        private void changeValueBy(int offset) {
            synchronized (this) {
                value += offset;
            }
        }

        public synchronized int value() {
            return value;
        }
    }
}
