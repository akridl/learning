package com.example;

import com.example.whydoubledispatch.Animal;
import com.example.whydoubledispatch.Cat;
import com.example.whydoubledispatch.Dog;
import com.example.whydoubledispatch.Visitor1;
import com.example.whydoubledispatch.Visitor2;
import com.example.withdoubledispatch.LegCounterVisitor;
import com.example.withdoubledispatch.SoundVisitor;
import com.example.withdoubledispatch.Visitor;

import java.util.List;

/**
 * Hello world!
 */
public class App {

    private static final Animal rex = new Dog();
    private static final Animal kitty = new Cat();
    private static final Animal max = new Dog();
    private static final List<Animal> animals = List.of(rex, kitty, max);

    public static void main(String[] args) {
        doubleDispatchMotivation();
        withDoubleDispatch();
    }

    static void withDoubleDispatch() {
        System.out.println();
        System.out.println("=== Double-dispatch example ===");

        List<com.example.withdoubledispatch.Animal> animals = List.of(
                new com.example.withdoubledispatch.Dog(),
                new com.example.withdoubledispatch.Cat(),
                new com.example.withdoubledispatch.Dog()
        );
        // Visitor soundVisitor = new SoundVisitor();
        Visitor legCounterVisitor = new LegCounterVisitor();

        animals.forEach(a -> a.apply(legCounterVisitor));
    }

    static void doubleDispatchMotivation() {
        methodOverriding();

        System.out.println();

        methodOverloading1();

        System.out.println();

        methodOverloading2();
    }

    private static void methodOverriding() {
        System.out.println("=== Method overriding -- late binding ===");
        animals.forEach(a -> System.out.println(a.makeSound()));
    }

    private static void methodOverloading1() {
        System.out.println("=== Method overloading -- early binding ===");
        Visitor1 visitor1 = new Visitor1();
        animals.forEach(visitor1::printSound);
    }

    private static void methodOverloading2() {
        System.out.println("=== Method overloading -- early binding ===");
        Visitor2 visitor2 = new Visitor2();
        for (var animal : animals) {
            if (animal instanceof Dog) {
                visitor2.printSoundForDog((Dog) animal);
            } else if (animal instanceof Cat) {
                visitor2.printSoundForCat((Cat) animal);
            } else {
                throw new IllegalArgumentException("Unknown implementation");
            }
        }
    }
}
