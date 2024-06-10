package com.example;

import java.util.Objects;

public class Person {

    public String givenName;

    public String familyName;

    public int age;

    public Person(String givenName, String familyName, int age) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return age == person.age && Objects.equals(givenName, person.givenName) && Objects.equals(familyName, person.familyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(givenName, familyName, age);
    }
}
