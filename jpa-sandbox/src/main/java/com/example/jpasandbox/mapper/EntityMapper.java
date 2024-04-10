package com.example.jpasandbox.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.Serializable;

public interface EntityMapper<T_DTO, T extends Serializable> {

    T toEntity(T_DTO editDto);

    T_DTO fromEntity(T entity);

    default Page<T_DTO> toDtoPage(Page<T> entityPage) {
        return new PageImpl<>(entityPage.getContent().stream().map(this::fromEntity).toList());
    }
}
