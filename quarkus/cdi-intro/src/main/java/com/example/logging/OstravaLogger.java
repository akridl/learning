package com.example.logging;

import com.example.dictionary.OstravaTranslationCompleted;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class OstravaLogger {

    public void ostravaTranslationListener(@Observes OstravaTranslationCompleted task) {
        log.info("Some serious Ostrava dictionaries were used!");
    }
}
