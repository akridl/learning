package com.example;

import com.example.demos.highlevel.AtomicDemo;
import com.example.demos.highlevel.CompletionStageDemo;
import com.example.demos.highlevel.ExecutorServiceDemo;
import com.example.demos.highlevel.FuturesDemo;
import com.example.demos.highlevel.ScheduledExecutorServiceDemo;
import com.example.demos.lowlevel.ImmutableObjectsDemo;
import com.example.demos.lowlevel.JoinDemo;
import com.example.demos.lowlevel.RaceConditionDemo;
import com.example.demos.lowlevel.SleepingDemo;
import com.example.demos.lowlevel.SpawningThreadDemo;
import com.example.demos.lowlevel.SynchronizedDemo;
import com.example.demos.lowlevel.SynchronizedDemo2;

public class App {

    public static void main(String[] args) {
        // *********************
        // ** Low-level demos **
        // *********************

        // LowLevelDemos.spawningThreadDemo.run();
        // LowLevelDemos.sleepingDemo.run();
        // LowLevelDemos.joinDemo.run();

        // LowLevelDemos.raceConditionDemo.run();
        // LowLevelDemos.synchronizedDemo.run();
        // LowLevelDemos.synchronizedDemo2.run();

        // LowLevelDemos.immutableObjectsDemo.run();

        // **********************
        // ** High-level demos **
        // **********************
        // HighLevelDemos.executorServiceDemo.run();
        // HighLevelDemos.atomicDemo.run();
        // HighLevelDemos.scheduledExecutorServiceDemo.run();
        // HighLevelDemos.futuresDemo.run();
        HighLevelDemos.completionStageDemo.run();
    }

    private static class LowLevelDemos {

        private static final SpawningThreadDemo spawningThreadDemo = new SpawningThreadDemo();
        private static final SleepingDemo sleepingDemo = new SleepingDemo();
        private static final JoinDemo joinDemo = new JoinDemo();
        private static final RaceConditionDemo raceConditionDemo = new RaceConditionDemo();
        private static final SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        private static final SynchronizedDemo2 synchronizedDemo2 = new SynchronizedDemo2();
        private static final ImmutableObjectsDemo immutableObjectsDemo = new ImmutableObjectsDemo();
    }

    private static final class HighLevelDemos {
        private static final ExecutorServiceDemo executorServiceDemo = new ExecutorServiceDemo();
        private static final AtomicDemo atomicDemo = new AtomicDemo();
        private static final ScheduledExecutorServiceDemo scheduledExecutorServiceDemo = new ScheduledExecutorServiceDemo();
        private static final FuturesDemo futuresDemo = new FuturesDemo();
        private static final CompletionStageDemo completionStageDemo = new CompletionStageDemo();
    }
}
