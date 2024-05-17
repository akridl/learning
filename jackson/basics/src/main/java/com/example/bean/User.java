package com.example.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("user")
@JsonIgnoreProperties({"givenName", "familyName", "password"})
public class User {

    @JsonProperty
    private final Long id;

    private final String givenName;

    private final String familyName;

    @JsonProperty("name")
    private final String fullName;

    private String password;

    public User(
            Long id,
            String givenName,
            String familyName,
            String password
    ) {
        this.id = id;
        this.givenName = givenName;
        this.familyName = familyName;
        this.fullName = givenName + " " + familyName;
        this.password = password;
    }
}
