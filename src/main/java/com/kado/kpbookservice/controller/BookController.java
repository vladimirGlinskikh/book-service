package com.kado.kpbookservice.controller;

import com.kado.kpbookservice.domain.dto.request.BookRequestDto;
import com.kado.kpbookservice.domain.dto.response.BookResponseDto;
import com.kado.kpbookservice.exception.BadRequestException;
import com.kado.kpbookservice.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@PreAuthorize("authenticated")
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public BookResponseDto findById(@PathVariable("id") UUID id) {
        return bookService.findById(id);
    }

    @GetMapping
    public Page<BookResponseDto> findByCategoryId(@RequestParam(value = "categoryId",required = false) Long categoryId,
                                                  @RequestParam(value = "name", required = false) String name,
                                                  @PageableDefault()
                                                  @SortDefault.SortDefaults({
                                                          @SortDefault(sort = "id", direction = Sort.Direction.DESC)
                                                  }) Pageable pageable) {
        if (name != null) {
            name = "%" + name.trim() + "%";
            return bookService.findAllByName(name, pageable);
        } else if (categoryId != null) {
            return bookService.findByCategoryId(categoryId, pageable);
        } else {
            // method not allowed
            throw new BadRequestException("Please provide categoryId or name");
        }
    }

    @PostMapping
    public BookResponseDto save(@Valid @RequestBody BookRequestDto bookDto, Principal principal) {
        if (principal != null) {
            bookDto.setUserId(UUID.fromString(principal.getName()));
        } else {
            bookDto.setUserId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        }
        return bookService.save(bookDto);
    }

    @PutMapping("/{id}")
    public BookResponseDto update(@Valid @RequestBody BookRequestDto bookDto, @PathVariable("id") UUID id) {
        return bookService.update(bookDto, id);
    }

    @PatchMapping("/{id}")
    public BookResponseDto partialUpdate(@RequestBody BookRequestDto bookDto, @PathVariable("id") UUID id) {
        return bookService.partialUpdate(bookDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        bookService.delete(id);
        return ResponseEntity.ok("Book deleted");
    }
}
