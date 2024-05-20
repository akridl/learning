package com.example.dictionary;

import com.example.annotations.MyPrimary;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
@MyPrimary
public class OstravaDictionary extends CzechDictionary implements Dictionary {

    @Inject
    Event<OstravaTranslationCompleted> event;

    @Override
    public String hi() {
        event.fire(new OstravaTranslationCompleted());
        return "Čááu";
    }
}
