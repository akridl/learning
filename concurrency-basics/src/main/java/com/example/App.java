package com.example;

public class App {

    private final static SpawningThreadDemo spawningThreadDemo = new SpawningThreadDemo();
    private final static SleepingDemo sleepingDemo = new SleepingDemo();
    private final static JoinDemo joinDemo = new JoinDemo();
    private final static RaceConditionDemo raceConditionDemo = new RaceConditionDemo();
    private final static SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
    private final static SynchronizedDemo2 synchronizedDemo2 = new SynchronizedDemo2();
    private final static ImmutableObjectsDemo immutableObjectsDemo = new ImmutableObjectsDemo();

    public static void main(String[] args) {
        // spawningThreadDemo.run();
        // sleepingDemo.run();
        // joinDemo.run();

        // raceConditionDemo.run();
        // synchronizedDemo.run();
        // synchronizedDemo2.run();

        immutableObjectsDemo.run();
    }
}
