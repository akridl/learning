package com.example.endpoint;

import com.example.dto.PersonDTO;

import java.util.List;

public class OpenApiConstants {

    public static final String SUCCESS_DESCRIPTION = "Success with results";
    public static final String SUCCESS_CODE = "200";
    public static final String ENTITY_CREATED_DESCRIPTION = "Entity successfully created";
    public static final String ENTITY_CREATED_CODE = "201";

    public interface PersonList extends List<PersonDTO> {}
}
