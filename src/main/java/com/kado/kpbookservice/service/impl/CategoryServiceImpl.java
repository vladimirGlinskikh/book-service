package com.kado.kpbookservice.service.impl;

import com.kado.kpbookservice.domain.dto.request.CategoryRequestDto;
import com.kado.kpbookservice.domain.dto.response.CategoryResponseDto;
import com.kado.kpbookservice.domain.entity.Category;
import com.kado.kpbookservice.mapper.CategoryMapper;
import com.kado.kpbookservice.repository.CategoryRepository;
import com.kado.kpbookservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryRequestDto)));
    }

    @Override
    public CategoryResponseDto update(CategoryRequestDto categoryRequestDto, Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Category with id %s not found", id)));
        Category entity = categoryMapper.toEntity(categoryRequestDto);
        entity.setId(category.getId());
        return categoryMapper.toDto(categoryRepository.save(entity));
    }

    @Override
    public CategoryResponseDto partialUpdate(CategoryRequestDto categoryRequestDto, Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Category with id %s not found", id)));
        categoryMapper.partialUpdate(category, categoryRequestDto);
        category.setId(id);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Category with id %s not found", id))));
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        categoryMapper.toDto(categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return category;
                })
                .orElseThrow(() -> new RuntimeException(String.format("Category with id %s not found", id))));
    }
}
