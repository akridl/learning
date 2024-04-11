package com.example.whydoubledispatch;

/**
 * This visitor is already better than {@link Visitor1} (at least it's working!). However, what is not nice is that the
 * client code itself has to decide the concrete method to call. This has several downsides; for instance, it does not
 * meet Open-Closed principle. In order to remove these problems, we'll use so called double-dispatch method.
 */
public class Visitor2 {

    public void printSoundForDog(Dog dog) {
        System.out.println(dog.makeSound());
    }

    public void printSoundForCat(Cat cat) {
        System.out.println(cat.makeSound());
    }
}
