package com.kado.kpbookservice.domain.dto.response;

import lombok.Data;

@Data
public class CategoryResponseDto {
    Long id;
    String name;
    String description;
    String imageUri;
}
