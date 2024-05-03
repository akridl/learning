package com.example.validationdemo.mapper;

public class LongMapper implements IdMapper<Long> {

    @Override
    public Long toDomainId(String id) {
        return Long.valueOf(id);
    }
}
