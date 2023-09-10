package com.kado.kpbookservice.service;

import com.kado.kpbookservice.dto.BookDto;
import com.kado.kpbookservice.entity.Book;
import com.kado.kpbookservice.exception.NotFoundException;
import com.kado.kpbookservice.mapper.BookMapper;
import com.kado.kpbookservice.repository.BookRepository;
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
    public BookDto findById(UUID id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable, Specification<Book> spec) {
        return bookRepository.findAll(spec, pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public Page<BookDto> findByCategoryId(Long categoryId, Pageable pageable) {
        return bookRepository.findByCategoryId(categoryId, pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto save(BookDto bookDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookDto)));
    }

    @Override
    public BookDto update(BookDto bookDto) {
        if (!bookRepository.existsById(bookDto.getId())) {
            throw new NotFoundException(String.format("Book with id %s not found", bookDto.getId()));
        }
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookDto)));
    }

    @Override
    public BookDto partialUpdate(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", bookDto.getId())));
        bookMapper.partialUpdate(book, bookDto);
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
