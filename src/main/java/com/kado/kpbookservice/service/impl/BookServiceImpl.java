package com.kado.kpbookservice.service.impl;

import com.kado.kpbookservice.domain.dto.request.BookRequestDto;
import com.kado.kpbookservice.domain.dto.response.BookResponseDto;
import com.kado.kpbookservice.domain.entity.Book;
import com.kado.kpbookservice.exception.NotFoundException;
import com.kado.kpbookservice.mapper.BookMapper;
import com.kado.kpbookservice.repository.BookRepository;
import com.kado.kpbookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto findById(UUID id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id))));
    }


    @Override
    public Page<BookResponseDto> findByCategoryId(Long categoryId, Pageable pageable) {
        return bookRepository.findByCategoryId(categoryId, pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookResponseDto save(BookRequestDto bookDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookDto)));
    }

    @Override
    public BookResponseDto update(BookRequestDto bookDto, UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
        Book entity = bookMapper.toEntity(bookDto);
        entity.setId(book.getId());
        return bookMapper.toDto(bookRepository.save(entity));
    }

    @Override
    public BookResponseDto partialUpdate(BookRequestDto bookDto, UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
        bookMapper.partialUpdate(book, bookDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public Boolean delete(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
        bookRepository.delete(book);
        return true;
    }
}
