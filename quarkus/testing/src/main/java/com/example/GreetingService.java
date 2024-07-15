package com.example;

import io.smallrye.common.constraint.NotNull;

public interface GreetingService {

    String greeting(@NotNull String name);

    String internalResult();

    String externalResult();

    String getValue1();

    String getValue2();
}
