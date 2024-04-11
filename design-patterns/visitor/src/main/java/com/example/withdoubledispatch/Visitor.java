package com.example.withdoubledispatch;

/** Note: Methods have to really be overloaded RN, since in some concrete implementation of the element,
 *        since in all concrete element implementations we do {@code visitor.visit(this);}. That's why the method
 *        name has to be the same.
 */
public interface Visitor {

    default void visit(Animal animal) {
        throw new IllegalArgumentException("Not supported");
    }

    void visit(Cat node);

    void visit(Dog node);
}
