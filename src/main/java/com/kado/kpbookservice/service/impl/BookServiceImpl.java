package com.kado.kpbookservice.service.impl;

import com.kado.kpbookservice.domain.dto.request.AuthorBookRequestDto;
import com.kado.kpbookservice.domain.dto.request.BookRequestDto;
import com.kado.kpbookservice.domain.dto.response.BookResponseDto;
import com.kado.kpbookservice.domain.entity.Author;
import com.kado.kpbookservice.domain.entity.Book;
import com.kado.kpbookservice.domain.entity.Category;
import com.kado.kpbookservice.exception.BadRequestException;
import com.kado.kpbookservice.exception.NotFoundException;
import com.kado.kpbookservice.mapper.BookMapper;
import com.kado.kpbookservice.repository.AuthorRepository;
import com.kado.kpbookservice.repository.BookRepository;
import com.kado.kpbookservice.repository.CategoryRepository;
import com.kado.kpbookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
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
    public Page<BookResponseDto> findAllByName(String name, Pageable pageable) {
        return bookRepository.findAllByTitleLikeIgnoreCase(name, pageable)
                .map(bookMapper::toDto);
    }

    @Override
    @Transactional
    public BookResponseDto save(BookRequestDto bookDto) {
        Book entity = bookMapper.toEntity(bookDto);
        return getBookResponseDto(bookDto, entity);
    }

    @Override
    @Transactional
    public BookResponseDto update(BookRequestDto bookDto, UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
        Book entity = bookMapper.toEntity(bookDto);
        entity.setId(book.getId());
        return getBookResponseDto(bookDto, entity);
    }

    private BookResponseDto getBookResponseDto(BookRequestDto bookDto, Book entity) {
        extractCategory(bookDto, entity);
        extractAuthor(bookDto, entity);
        return bookMapper.toDto(bookRepository.save(entity));
    }

    @Override
    @Transactional
    public BookResponseDto partialUpdate(BookRequestDto bookDto, UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
        bookMapper.partialUpdate(book, bookDto);
        book.setId(id);
        if (bookDto.getCategory() != null) {
            extractCategory(bookDto, book);
        }
        if (bookDto.getAuthors() != null) {
            extractAuthor(bookDto, book);
        }
        return bookMapper.toDto(bookRepository.save(book));
    }

    private void extractAuthor(BookRequestDto bookDto, Book book) {
        List<Author> authors = new ArrayList<>();
        for (AuthorBookRequestDto authorBookRequestDto : bookDto.getAuthors()) {
            Author author = authorRepository.findById(authorBookRequestDto.getId())
                    .orElseThrow(() -> new BadRequestException(String.format("Author with id %s not found", authorBookRequestDto.getId())));
            authors.add(author);
        }
        book.setAuthors(authors);
    }

    private void extractCategory(BookRequestDto bookDto, Book book) {
        Category category = categoryRepository.findById(bookDto.getCategory())
                .orElseThrow(() -> new BadRequestException(String.format("Category with id %s not found", bookDto.getCategory())));
        book.setCategory(category);
    }

    @Override
    @Transactional
    public Boolean delete(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s not found", id)));
        bookRepository.delete(book);
        return true;
    }
}
