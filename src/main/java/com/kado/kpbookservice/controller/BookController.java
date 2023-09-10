package com.kado.kpbookservice.controller;

import com.kado.kpbookservice.dto.BookDto;
import com.kado.kpbookservice.entity.Book;
import com.kado.kpbookservice.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
//@PreAuthorize("authenticated")
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public BookDto findById(@PathVariable("id") UUID id) {
        return bookService.findById(id);
    }

    @GetMapping
    public Page<BookDto> findAll(
            @PageableDefault(size = 4) Pageable pageable,
            @RequestParam Map<String, String> allParams,
            @SortDefault(sort = "title", direction = Sort.Direction.DESC) Sort sort) {
        Specification<Book> spec = Specification.where(null);
        if (allParams.containsKey("title")) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + allParams.get("title").toLowerCase() + "%"));
        }
        if (allParams.containsKey("author")) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + allParams.get("author").toLowerCase() + "%"));
        }

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return bookService.findAll(sortedPageable, spec);
    }

    @PostMapping
    public BookDto save(@Valid @RequestBody BookDto bookDto, Principal principal) {
        if (principal != null) {
            bookDto.setUserId(UUID.fromString(principal.getName()));
        } else {
            bookDto.setUserId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        }
        return bookService.save(bookDto);
    }

    @PutMapping("/{id}")
    public BookDto update(@Valid @RequestBody BookDto bookDto, @PathVariable("id") UUID id) {
        bookDto.setId(id);
        return bookService.update(bookDto);
    }

    @PatchMapping("/{id}")
    public BookDto partialUpdate(@RequestBody BookDto bookDto, @PathVariable("id") UUID id) {
        bookDto.setId(id);
        return bookService.partialUpdate(bookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        bookService.delete(id);
        return ResponseEntity.ok("Book deleted");
    }
}
