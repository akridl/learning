package com.example.api.endpoint.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jboss.resteasy.reactive.RestCookie;
import org.jboss.resteasy.reactive.RestHeader;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreatePersonParams {

    @RestPath
    String givenName;

    @RestQuery
    String familyName;

    @RestHeader("X-person-birthday")
    LocalDate birthday;

    @RestCookie
    String key1;

    @RestCookie
    String key2;
}
