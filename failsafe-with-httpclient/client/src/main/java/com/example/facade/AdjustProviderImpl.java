package com.example.facade;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdjustProviderImpl implements AdjustProvider {

    @Override
    public String adjust(String text) {
        return text + "!";
    }
}
