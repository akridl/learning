package com.example.withdoubledispatch;

public class LegCounterVisitor implements Visitor {

    @Override
    public void visit(Cat node) {
        System.out.println("Cat has got 4 legs");
    }

    @Override
    public void visit(Dog node) {
        System.out.println("Dog has got 4 legs");
    }
}
