package com.example.withdoubledispatch;

public interface Animal {

    // Note: it would be a mistake to make this method default, since we really want to force this being called via
    // vtable to use dynamic binding
    void apply(Visitor visitor);
}
