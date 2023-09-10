package com.kado.kpbookservice.mapper;

import com.kado.kpbookservice.dto.AuthorDto;
import com.kado.kpbookservice.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends AbstractMapper<AuthorDto, Author> {

}
