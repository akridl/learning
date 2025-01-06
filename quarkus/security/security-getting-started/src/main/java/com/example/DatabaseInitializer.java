package com.example;

import com.example.model.User;
import io.quarkus.elytron.security.common.BcryptUtil;
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

        createUser("Admin", "admin", List.of("admin,user"));
        createUser("Alice", "qwerty123", List.of("user"));
    }

    private static void createUser(String username, String password, List<String> roles) {
        User user = new User();
        user.username = username;
        user.password = BcryptUtil.bcryptHash(password);
        user.roles = roles;
        user.persist();
    }
}
