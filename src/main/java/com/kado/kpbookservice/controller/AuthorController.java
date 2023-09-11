package com.kado.kpbookservice.controller;

import com.kado.kpbookservice.domain.dto.request.AuthorRequestDto;
import com.kado.kpbookservice.domain.dto.response.AuthorResponseDto;
import com.kado.kpbookservice.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book-authors")
@RequiredArgsConstructor
@Slf4j
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public AuthorResponseDto findById(@PathVariable UUID id) {
        return authorService.findById(id);
    }

    @GetMapping
    public List<AuthorResponseDto> findAll(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            name = "%" + name.trim() + "%";
            return authorService.findAllByName(name);
        }
        return authorService.findAll();
    }

    @PostMapping
    public AuthorResponseDto save(@RequestBody @Valid AuthorRequestDto authorResponseDto) {
        return authorService.save(authorResponseDto);
    }

    @PutMapping("/{id}")
    public AuthorResponseDto update(@RequestBody @Valid AuthorRequestDto authorResponseDto, @PathVariable UUID id) {
        return authorService.update(authorResponseDto, id);
    }

    @PatchMapping("/{id}")
    public AuthorResponseDto partialUpdate(@RequestBody AuthorRequestDto authorResponseDto, @PathVariable UUID id) {
        return authorService.partialUpdate(authorResponseDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        authorService.delete(id);
    }
}
