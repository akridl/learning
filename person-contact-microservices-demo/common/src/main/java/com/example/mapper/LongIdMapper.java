package com.example.mapper;

import com.example.exception.InvalidIdException;

public class LongIdMapper implements IdMapper<Long> {

    @Override
    public Long toEntityId(String dtoId) {
        try {
            return Long.valueOf(dtoId);
        } catch (NumberFormatException ex) {
            throw new InvalidIdException("Cannot map id=" + dtoId + " into string");
        }
    }
}
