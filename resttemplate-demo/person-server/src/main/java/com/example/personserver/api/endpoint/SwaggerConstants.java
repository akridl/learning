package com.example.personserver.api.endpoint;

import com.example.personserver.api.dto.PersonDto;
import org.springframework.data.domain.Page;

public class SwaggerConstants {

    interface PersonPage extends Page<PersonDto> {}
}
