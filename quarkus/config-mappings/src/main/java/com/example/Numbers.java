package com.example;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Numbers {

    int intNumber;

    long longNumber;

    float floatNumber;
}
