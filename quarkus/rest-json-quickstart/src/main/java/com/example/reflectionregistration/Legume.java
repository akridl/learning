package com.example.reflectionregistration;

import io.quarkus.runtime.annotations.RegisterForReflection;

/*
    In case we want to make native build, Jackson will be unable to serialize this class, since it is not registered
    for a reflection, and will result with the error message:

    ERROR [io.qua.res.rea.jac.run.map.NativeInvalidDefinitionExceptionMapper] (executor-thread-1) Jackson was unable to
        serialize type 'com.example.reflectionregistration.Legume'. Consider annotating the class with
        '@RegisterForReflection' or using 'org.jboss.resteasy.reactive.RestResponse' as a response type of
        'com.example.reflectionregistration.LegumeResource#list
                            ...

    As it suggests, after we register it, the problem is resolved.
 */
@RegisterForReflection
public class Legume {

    public String name;
    public String description;

    public Legume() {
    }

    public Legume(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
