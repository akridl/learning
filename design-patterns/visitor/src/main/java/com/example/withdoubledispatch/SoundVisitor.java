package com.example.withdoubledispatch;

public class SoundVisitor implements Visitor {

    @Override
    public void visit(Cat node) {
        System.out.println("Meow meow");
    }

    @Override
    public void visit(Dog node) {
        System.out.println("Bark bark!");
    }
}
