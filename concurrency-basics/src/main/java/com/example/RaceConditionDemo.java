package com.example;

import com.example.threadspawner.DirectThreadSpawner;

/**
 * How to make this correctly, see {@link SynchronizedDemo}.
 */
public class RaceConditionDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("***** Race condition demo *****");

        // Counter is shared among both threads
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                counter.decrement();
            }
        });

        DirectThreadSpawner.spawnThread(t1);
        DirectThreadSpawner.spawnThread(t2);

        try {
            t1.join();
            t2.join();
            // Counter value should be 0
            // Since we 10 thousand times increment one to the initial value (=0), and then same number of times decrement by one
            System.out.println("Counter value is: " + counter.value());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Dummy version of the counter, which is **not** thread-safe.
     */
    private static class Counter {
        private volatile int value = 0;

        public void increment() {
            value++;
        }

        public void decrement() {
            value--;
        }

        public int value() {
            return value;
        }
    }
}
