package com.example.dictionary;

import com.example.Language;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class LatvianDictionary implements Dictionary {

    @Override
    public String hi() {
        log.info("Latvian hi() called, {}", this);
        return "Sveiki";
    }

    @Override
    public String hello() {
        log.info("Latvian hello() called, {}", this);
        return "Sveiki";
    }

    @Override
    public String goodbye() {
        log.info("Latvian goodbye() called, {}", this);
        return "Ardievu";
    }

    @Override
    public Language language() {
        return Language.LAT;
    }
}
