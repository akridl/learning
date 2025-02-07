package com.example;

public interface Bean {

    Integer doSomething();

    default String pick() {
        return "I'm ok";
    }
}
