package com.example;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * It seems quite counter-intuitive that this is actually something we would want, it's only a boilerplate code, isn't it?!
 * Yes, it is! But it allows us to run the app from IDE.
 */
@QuarkusMain(name = "main1")
public class JavaMain1 {

    public static void main(String... args) {
        Quarkus.run(App1.class, args);
    }
}
