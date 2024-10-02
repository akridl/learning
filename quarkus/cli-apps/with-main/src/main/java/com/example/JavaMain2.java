package com.example;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain(name = "main2")
public class JavaMain2 {

    public static void main(String... args) {
        Quarkus.run(App2.class, args);
    }
}
