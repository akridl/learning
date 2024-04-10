package com.example.jpasandbox.api;

import com.example.jpasandbox.api.dto.PersonDto;
import org.springframework.data.domain.Page;

public class SwaggerConstants {

    public interface PersonPage extends Page<PersonDto> {}
}
