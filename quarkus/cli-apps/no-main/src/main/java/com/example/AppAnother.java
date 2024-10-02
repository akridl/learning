package com.example;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;

@QuarkusMain(name = "entrypoint2")
@Slf4j
public class AppAnother implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        log.info("Chaa, I'm not lazy, I don't even need to delegate the work for another bean!");

        return 0;
    }
}
