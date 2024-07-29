package com.example.threadspawner;

/**
 * Spawner, which spawns new threads directly using {@link Thread}.
 */
public class DirectThreadSpawner {

    public static void spawnThread(Thread thread) {
        thread.start();
    }
}
