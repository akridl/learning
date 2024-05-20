package com.example.dictionary;

import com.example.Language;
import com.example.logging.Logged;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;

// @Singleton
// @ApplicationScoped
@RequestScoped
// @Dependent
@Slf4j
@Logged
public class CzechDictionary implements Dictionary {

    @PostConstruct
    void init() {
        log.info("Wohoooo, CzechDictionary will be injected into some other bean!" +
                " But there's some serious initialization to be done at first!!");
    }

    @PreDestroy
    void destroy() {
        log.info("Oh no, container is removing CzechDictionary from its context -_-" +
                " OK, let's get rid of all the resources");
    }

    @Override
    public String hi() {
        // log.info("Czech hi() called, {}", this);
        return "Čau";
    }

    @Override
    public String hello() {
        // log.info("Czech hello() called, {}", this);
        return "Ahoj";
    }

    @Override
    public String goodbye() {
        // log.info("Czech goodbye() called, {}", this);
        return "Naschledanou";
    }

    @Override
    public Language language() {
        return Language.CZE;
    }
}
