package com.kado.kpbookservice.service;

import com.kado.kpbookservice.domain.dto.request.CategoryRequestDto;
import com.kado.kpbookservice.domain.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto save(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto update(CategoryRequestDto categoryRequestDto, Long id);

    CategoryResponseDto partialUpdate(CategoryRequestDto categoryRequestDto, Long id);

    CategoryResponseDto findById(Long id);

    List<CategoryResponseDto> findAll();

    void delete(Long id);
}
