package com.example.personserver.mapper;

import com.example.personserver.exception.InvalidIdFormatException;

public class LongIdMapper implements IdMapper<Long> {

    @Override
    public Long toEntityId(String dtoId) {
        try {
            return Long.valueOf(dtoId);
        } catch (NumberFormatException ex) {
            throw new InvalidIdFormatException("Cannot convert id=" + dtoId + " into Long.");
        }
    }
}
