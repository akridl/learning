package com.example;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class BeanImpl2 implements Bean {

    @PostConstruct
    public void init() {
        log.info("[BeanImpl2#init] was just initialized");
    }

    @Override
    public Integer doSomething() {
        log.info("Yeah, I can do this");

        return 13;
    }
}
