package com.kado.kpbookservice.mapper;

import com.kado.kpbookservice.domain.dto.request.AuthorRequestDto;
import com.kado.kpbookservice.domain.dto.response.AuthorResponseDto;
import com.kado.kpbookservice.domain.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorRequestDto authorRequestDto);
    AuthorResponseDto toDto(Author author);
}
