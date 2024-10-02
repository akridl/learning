package com.example;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain(name = "entrypoint1") // denotes *an* entrypoint of the application, see application.properties
public class App implements QuarkusApplication {

    @Inject
    Bean bean;

    @Override
    public int run(String... args) throws Exception {
        bean.doSomething();

        return 0;
    }
}
