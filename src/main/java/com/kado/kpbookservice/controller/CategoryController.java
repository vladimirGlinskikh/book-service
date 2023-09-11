package com.kado.kpbookservice.controller;

import com.kado.kpbookservice.domain.dto.request.CategoryRequestDto;
import com.kado.kpbookservice.domain.dto.response.CategoryResponseDto;
import com.kado.kpbookservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book-categories")
@RequiredArgsConstructor
@PreAuthorize("authenticated")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAll();
    }

    @PostMapping
    public CategoryResponseDto save(@RequestBody @Valid CategoryRequestDto categoryResponseDto) {
        return categoryService.save(categoryResponseDto);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto update(@RequestBody @Valid CategoryRequestDto categoryResponseDto, @PathVariable Long id) {
        return categoryService.update(categoryResponseDto, id);
    }

    @PatchMapping("/{id}")
    public CategoryResponseDto partialUpdate(@RequestBody CategoryRequestDto categoryResponseDto, @PathVariable Long id) {
        return categoryService.partialUpdate(categoryResponseDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
