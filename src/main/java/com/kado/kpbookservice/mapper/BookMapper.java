package com.kado.kpbookservice.mapper;

import com.kado.kpbookservice.dto.BookDto;
import com.kado.kpbookservice.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class})
public interface BookMapper extends AbstractMapper<Book, BookDto> {
}
