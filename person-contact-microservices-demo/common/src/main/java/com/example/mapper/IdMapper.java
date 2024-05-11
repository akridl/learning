package com.example.mapper;

import java.io.Serializable;

public interface IdMapper<ID extends Serializable> {

    ID toEntityId(String dtoId);

    default String toDtoId(ID entityId) {
        return String.valueOf(entityId);
    }
}
