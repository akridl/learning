package com.example;

import com.example.model.User;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Singleton
@Slf4j
public class DatabaseInitializer {

    @Transactional
    public void createUsers(@Observes StartupEvent startupEvent) {
        log.info("Adding users into DB");

        var admin = new User("Admin", "admin", List.of("admin", "user"));
        var alice = new User("Alice", "qwerty123", List.of("user"));

        admin.add();
        alice.add();
    }
}
