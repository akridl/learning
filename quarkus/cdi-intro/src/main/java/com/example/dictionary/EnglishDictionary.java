package com.example.dictionary;

import com.example.Language;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class EnglishDictionary implements Dictionary {

    @Override
    public String hi() {
        log.info("English hi() called, {}", this);
        return "Hi";
    }

    @Override
    public String hello() {
        log.info("English hello() called, {}", this);
        return "Hello";
    }

    @Override
    public String goodbye() {
        log.info("English goodbye() called, {}", this);
        return "Goodbye";
    }

    @Override
    public Language language() {
        return Language.ENG;
    }
}
