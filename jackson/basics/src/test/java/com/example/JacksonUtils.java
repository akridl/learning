package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonUtils {

    public static final ObjectMapper commonMapper = new ObjectMapper();

    public static final ObjectMapper wrappingMapper = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, true);
}
