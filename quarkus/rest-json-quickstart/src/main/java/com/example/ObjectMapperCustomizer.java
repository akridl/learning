package com.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Singleton;

@Singleton
public class ObjectMapperCustomizer implements io.quarkus.jackson.ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        /*
        Thanks to this flag, Jackson will throw an exception in case any unknown property is given, e.g.:

        curl -X 'POST' \
          'http://localhost:8080/persons' \
          -H 'accept: application/json' \
          -H 'Content-Type: application/json' \
          -d '{
          "givenName": "Jane",
          "familyName": "Doe",
          "age": 42,
          "car": "Mercedes"
        }'

        Since 'car' property is not defined, it will result in 400 bad request.
         */
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }
}
