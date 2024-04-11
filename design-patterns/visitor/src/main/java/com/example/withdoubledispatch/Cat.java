package com.example.withdoubledispatch;

public class Cat implements Animal {

    @Override
    public void apply(Visitor visitor) {
        visitor.visit(this);
    }
}
