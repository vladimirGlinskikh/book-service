package com.kado.kpbookservice.mapper;

import com.kado.kpbookservice.dto.CategoryDto;
import com.kado.kpbookservice.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends AbstractMapper<CategoryDto, Category> {
}
