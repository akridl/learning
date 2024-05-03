package com.example.validationdemo.mapper;

import java.io.Serializable;

public interface IdMapper<T extends Serializable> {

    default String toRestId(T id) {
        return String.valueOf(id);
    }

    T toDomainId(String id);
}
