package com.example.bean;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"name", "map", "id"})
public class BeanWithMap {

    @JsonProperty
    private final Long id;

    private final String name;

    @JsonAnyGetter
    public final Map<String, String> map;

    public BeanWithMap(Long id, String name) {
        this.id = id;
        this.name = name;
        this.map = new HashMap<>();
    }

    @JsonGetter("beanName")
    public String getName() {
        return name;
    }
}
