package com.example;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;

@QuarkusMain
@Slf4j
public class Main {

    public static void main(String... args) {
        log.info(adjustLog("Running the main()"));
        Quarkus.run(MyApp.class, args);
        log.info(adjustLog("Exiting the main()"));
    }

    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args) {
            log.info(adjustLog("Quarkus app starting..."));
            Quarkus.waitForExit();
            log.info(adjustLog("Quarkus app ended..."));
            return 0;
        }
    }

    private static String adjustLog(String log) {
        return "[ " + Thread.currentThread() + " ]: " + log;
    }
}
