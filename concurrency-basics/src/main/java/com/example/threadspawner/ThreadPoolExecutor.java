package com.example.threadspawner;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Spawner, which uses underlying thread pool for managing of threads.
 */
public class ThreadPoolExecutor implements AutoCloseable, Executor {
    
    private final static int THREAD_POOL_SIZE = 10;
    private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    @Override
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    @Override
    public void close() {
        executor.shutdown();
    }
}
