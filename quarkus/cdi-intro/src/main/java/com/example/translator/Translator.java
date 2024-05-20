package com.example.translator;

import com.example.Language;

/**
 * Translator from Slovak language.
 */
public interface Translator {

    String translate(String text, Language language);

    // Just for the purpose of playing with @Singleton
    void doNothing();
}
