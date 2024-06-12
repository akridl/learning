package com.example.repository;

import com.example.model.Greeting;

public interface GreetingRepository {

    Greeting createGreeting(String greeting);

    Greeting getGreeting(Long id);
}
