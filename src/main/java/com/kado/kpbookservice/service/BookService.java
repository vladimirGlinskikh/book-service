package com.kado.kpbookservice.service;

import com.kado.kpbookservice.dto.BookDto;
import com.kado.kpbookservice.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface BookService {

    BookDto findById(UUID id);

    Page<BookDto> findAll(Pageable pageable, Specification<Book> spec);

    Page<BookDto> findByCategoryId(Long categoryId, Pageable pageable);

    BookDto save(BookDto bookDto);

    BookDto update(BookDto bookDto);

    BookDto partialUpdate(BookDto bookDto);

    Boolean delete(UUID id);

}
