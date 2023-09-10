package com.kado.kpbookservice.mapper;

import com.kado.kpbookservice.domain.dto.request.BookRequestDto;
import com.kado.kpbookservice.domain.dto.response.BookResponseDto;
import com.kado.kpbookservice.domain.entity.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class})
public interface BookMapper {

    @Mapping(source = "category", target = "category.id")
    Book toEntity(BookRequestDto bookRequestDto);

    BookResponseDto toDto(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "category", target = "category.id")
    void partialUpdate(@MappingTarget Book entity, BookRequestDto bookRequestDto);
}
