package com.kado.kpbookservice.service;

import com.kado.kpbookservice.domain.dto.request.BookRequestDto;
import com.kado.kpbookservice.domain.dto.response.BookResponseDto;
import com.kado.kpbookservice.domain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface BookService {

    BookResponseDto findById(UUID id);

    Page<BookResponseDto> findByCategoryId(Long categoryId, Pageable pageable);

    BookResponseDto save(BookRequestDto bookDto);

    BookResponseDto update(BookRequestDto bookDto, UUID id);

    BookResponseDto partialUpdate(BookRequestDto bookDto, UUID id);

    Boolean delete(UUID id);

}
