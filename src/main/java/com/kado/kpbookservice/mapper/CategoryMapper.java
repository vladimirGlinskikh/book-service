package com.kado.kpbookservice.mapper;

import com.kado.kpbookservice.domain.dto.request.CategoryRequestDto;
import com.kado.kpbookservice.domain.dto.response.CategoryResponseDto;
import com.kado.kpbookservice.domain.entity.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Category entity, CategoryRequestDto categoryRequestDto);
}
