package com.kado.kpbookservice.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;

public interface AbstractMapper<F, T> {
    T toDto(F f);

    F toEntity(T t);
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget F f, T t);

}
