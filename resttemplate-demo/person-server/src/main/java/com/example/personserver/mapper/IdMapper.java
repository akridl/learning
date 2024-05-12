package com.example.personserver.mapper;

import java.io.Serializable;

public interface IdMapper<ID extends Serializable> {

    ID toEntityId(String dtoId);
}
