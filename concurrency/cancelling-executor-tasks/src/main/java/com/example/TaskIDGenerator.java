package com.example;

import java.util.Random;

public class TaskIDGenerator {

    private static final Random random = new Random();

    public static Integer generateTaskID() {
        return random.nextInt(Integer.MAX_VALUE);
    }
}
