package com.kado.kpbookservice.mapper;

import com.kado.kpbookservice.domain.dto.request.AuthorRequestDto;
import com.kado.kpbookservice.domain.dto.response.AuthorResponseDto;
import com.kado.kpbookservice.domain.entity.Author;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorRequestDto authorRequestDto);
    AuthorResponseDto toDto(Author author);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Author entity, AuthorRequestDto authorRequestDto);
}
