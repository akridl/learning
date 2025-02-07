package com.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class BeanImpl implements Bean {

    @PostConstruct
    public void init() {
        log.info("[BeanImpl#init] was just initialized");
    }

    @PreDestroy
    public void destroy() {
        log.info("[BeanImpl#destroy] will be destroyed soon");
    }

    @Override
    @Logged
    public Integer doSomething() {
        log.info("[BeanImpl#doSomething]: trying really hard");
        return 42;
    }

    @Override
    public String pick() {
        return "I'm the best!";
    }
}
