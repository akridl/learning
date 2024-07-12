package com.example;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.Dependent;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class LazilyCreatedBean {

    @PreDestroy
    void onDestroy() {
        log.info("[LazilyCreatedBean#onDestroy]: yeaah, seems like the container wanna kill me -_-");
    }
}
