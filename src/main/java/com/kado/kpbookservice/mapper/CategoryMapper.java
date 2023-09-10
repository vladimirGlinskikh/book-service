package com.kado.kpbookservice.mapper;

import com.kado.kpbookservice.domain.dto.request.CategoryRequestDto;
import com.kado.kpbookservice.domain.dto.response.CategoryResponseDto;
import com.kado.kpbookservice.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto toDto(Category category);
}
