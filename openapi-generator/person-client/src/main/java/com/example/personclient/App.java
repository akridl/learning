package com.example.personclient;

import com.example.generated.client.api.PersonApi;
import com.example.generated.client.model.PersonDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<PersonDto> persons = new PersonApi().getAllPersons();
        persons.forEach(System.out::println);
    }
}
