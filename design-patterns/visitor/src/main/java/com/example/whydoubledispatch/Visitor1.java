package com.example.whydoubledispatch;

/**
 * The main problem with this implementation of the visitor is that it uses method overloading, which uses early binding.
 * Hence, when doing <br/>
 * {@code Visitor1 visitor1 = new Visitor1();}
 * <br/>
 * {@code animals.forEach(visitor1::printSound);}
 * every animal is of type Animal, and since we're using an early binding,
 * {@code printSound(Animal)} is called. So in particular, this version of the visitor is not even correctly working.
 */
public class Visitor1 {

    public void printSound(Animal animal) {
        System.out.println("WTF! IDK how general animal does...");
    }

    public void printSound(Dog dog) {
        System.out.println(dog.makeSound());
    }

    public void printSound(Cat cat) {
        System.out.println(cat.makeSound());
    }
}
