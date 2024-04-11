package com.example.withdoubledispatch;

public class Dog implements Animal {

    @Override
    public void apply(Visitor visitor) {
        visitor.visit(this);
    }
}
