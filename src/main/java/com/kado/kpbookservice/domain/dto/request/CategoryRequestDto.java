package com.kado.kpbookservice.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CategoryRequestDto {
    @NotEmpty(message = "Name is required")
    String name;
    @NotEmpty(message = "Description is required")
    String description;
    @NotEmpty(message = "ImageUri is required")
    String imageUri;
}
