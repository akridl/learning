package com.example.translator;


import com.example.Language;
import com.example.annotations.MyPrimary;
import com.example.dictionary.CzechDictionary;
import com.example.dictionary.EnglishDictionary;
import com.example.dictionary.LatvianDictionary;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
// @Alternative
@MyPrimary
@Slf4j
public class DummyTranslator implements Translator {

    private final CzechDictionary czechDictionary;

    private final EnglishDictionary englishDictionary;

    private final LatvianDictionary latvianDictionary;

    @Inject
    public DummyTranslator(
            @MyPrimary CzechDictionary czechDictionary,
            EnglishDictionary englishDictionary,
            LatvianDictionary latvianDictionary
    ) {
        this.czechDictionary = czechDictionary;
        this.englishDictionary = englishDictionary;
        this.latvianDictionary = latvianDictionary;
    }

    @Override
    public String translate(String text, Language language) {
        log.info("Dummy translation of {} to {}, {}", text, language, this);

        return switch (language) {
            case CZE -> switch (text) {
                case "Ahoj" -> czechDictionary.hello();
                case "Čau" -> czechDictionary.hi();
                case "Dovidenia" -> czechDictionary.goodbye();
                default -> throw new IllegalArgumentException("Cannot translate " + text + " to Czech");
            };
            case ENG -> switch (text) {
                case "Ahoj" -> englishDictionary.hello();
                case "Čau" -> englishDictionary.hi();
                case "Dovidenia" -> englishDictionary.goodbye();
                default -> throw new IllegalArgumentException("Cannot translate " + text + " to English");
            };
            case LAT -> switch (text) {
                case "Ahoj" -> latvianDictionary.hello();
                case "Čau" -> latvianDictionary.hi();
                case "Dovidenia" -> latvianDictionary.goodbye();
                default -> throw new IllegalArgumentException("Cannot translate " + text + " to Latvian");
            };
        };
    }

    @Override
    public void doNothing() {
        log.info("DummyTranslator#doNothing()");
    }
}
