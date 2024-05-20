package com.example.translator;

import com.example.Language;
import com.example.dictionary.Dictionary;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
// @MyPrimary
@Slf4j
public class SuperIntelligentTranslator implements Translator {

    private final Instance<Dictionary> dictionaries;

    @Inject
    public SuperIntelligentTranslator(Instance<Dictionary> dictionaries) {
        log.info("SuperIntelligentTranslator constructor");
        this.dictionaries = dictionaries;
    }

    @Override
    public String translate(String text, Language language) {
        log.info("Super-intelligent translation of {} to {}, {}", text, language, this);

        boolean dictionaryFound = false;
        String translated = null;

        for (Dictionary dictionary : dictionaries) {
            if (language.equals(dictionary.language())) {
                dictionaryFound = true;
                translated = switch (text) {
                    case "Ahoj" -> dictionary.hello();
                    case "Čau" -> dictionary.hi();
                    case "Dovidenia" -> dictionary.goodbye();
                    default -> throw new IllegalArgumentException("Cmooon dude.... I cannot know everything");
                };
            }
        }

        if (!dictionaryFound) {
            throw new IllegalArgumentException("What do u want from me?! U know Swahili?!!");
        }
        return translated;
    }

    @Override
    public void doNothing() {
        log.info("SuperIntelligentTranslator#doNothing()");
    }
}
